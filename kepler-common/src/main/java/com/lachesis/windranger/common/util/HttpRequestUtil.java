package com.lachesis.windranger.common.util;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.*;

public class HttpRequestUtil {
	private static Logger LOG = LoggerFactory.getLogger(HttpRequestUtil.class);
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param, String token) {
		StringBuffer result = new StringBuffer("");
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			// String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.addRequestProperty("Authorization", token);
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param, String token) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			if (token != null && !"".equals(token)) {
				conn.addRequestProperty("Authorization", token);
			}
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			System.out.println(result);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	public static String sendPostMnis(String params,Map uriMap, String requestUrl,
								  String authorization, int timeout) throws ConnectTimeoutException, HttpException {
		try {
			HttpClient httpClient = new HttpClient();// 客户端实例化
			if(timeout > 0 ){
				httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			}
			DefaultHttpClient client = new DefaultHttpClient();
			List<BasicNameValuePair> paramsURI = new ArrayList<>();
			Iterator it =  uriMap.keySet().iterator();
			while(it.hasNext()){
				String key = it.next().toString();
				paramsURI.add( new BasicNameValuePair( key, uriMap.get(key).toString() ) );
			}

			URI uri = null;
			try {
				uri = new URI( requestUrl + "?" + URLEncodedUtils.format( paramsURI, "utf-8" ));
				PostMethod postMethod = new PostMethod(uri.toString());
				//设置请求头Authorization
				postMethod.setRequestHeader("Authorization", "Basic " + authorization);
				// 设置请求头  Content-Type
				postMethod.setRequestHeader("Content-Type", "application/json");
				byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流

				InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
						requestBytes.length);
				RequestEntity requestEntity = new InputStreamRequestEntity(inputStream,
						requestBytes.length, "application/json; charset=utf-8"); // 请求体
				postMethod.setRequestEntity(requestEntity);

				httpClient.executeMethod(postMethod);// 执行请求
				InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// 获取返回的流
				byte[] datas = null;
				try {
					datas = readInputStream(soapResponseStream);// 从输入流中读取数据
				} catch (Exception e) {
					e.printStackTrace();
				}
				String result = new String(datas, "UTF-8");// 将二进制流转为String
				// 打印返回结果
				System.out.println(result);
				return result;
			} 
            catch (ConnectTimeoutException | HttpException e) {
            	throw e;
            }
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static String sendGet(String params, String requestUrl, String user, String password, int timeout){
		try {
			final HttpClient httpClient = new HttpClient();// 客户端实例化
			if(timeout > 0 ){
				httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			}
			httpClient.getParams().setAuthenticationPreemptive(true);
			httpClient.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
			List<BasicNameValuePair> paramsURI = new ArrayList<>();
			URI uri = null;
			try {
				uri = new URI( requestUrl + "?" + URLEncodedUtils.format( paramsURI, "utf-8" ));
				final GetMethod method = new GetMethod(uri.toString());
				// 设置请求头  Content-Type
				method.setRequestHeader("Content-Type", "application/json");
				byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流

				httpClient.executeMethod(method);// 执行请求
				InputStream soapResponseStream = method.getResponseBodyAsStream();// 获取返回的流
				byte[] datas = null;
				try {
					datas = readInputStream(soapResponseStream);// 从输入流中读取数据
				} catch (Exception e) {
					e.printStackTrace();
				}
				String result = new String(datas, "UTF-8");// 将二进制流转为String
				// 打印返回结果
				System.out.println(result);
				return result;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch ( org.apache.commons.httpclient.HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendJsonPost(String uri, String param, String token) {
		System.out.println(uri);
		System.out.println(param);
		try {
			URL url = new URL(uri);// 创建连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("POST"); // 设置请求方式
			connection.setRequestProperty("Accept", "application/json;charset=UTF-8"); // 设置接收数据的格式
			connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
			connection.connect();
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
			out.append(param);
			out.flush();
			out.close();
			// 读取响应
			int length = (int) connection.getContentLength();// 获取长度
			InputStream is = connection.getInputStream();
			if (length != -1) {
				byte[] data = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) > 0) {
					System.arraycopy(temp, 0, data, destPos, readLen);
					destPos += readLen;
				}
				String result = new String(data, "UTF-8"); // utf-8编码
				System.out.println(result);
				return result;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error"; // 自定义错误信息
	}

	/**
	 * 从输入流中读取数据
	 *
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;
	}
	
	public static int convertIpToInt(String ipAddress) {
		int result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");

		for (int i = 3; i >= 0; i--) {
			int ip = Integer.parseInt(ipAddressInArray[3 - i]);
			// left shifting 24,16,8,0 and bitwise OR
			// 1. 192 << 24
			// 1. 168 << 16
			// 1. 1 << 8
			// 1. 2 << 0
			result |= ip << (i * 8);
		}
		return result;
	}
	
	public static String intToIP(int intIp){   
        StringBuffer sb = new StringBuffer("");   
        //直接右移24位   
        sb.append(String.valueOf((intIp >>> 24)));   
        sb.append(".");   
        //将高8位置0，然后右移16位   
        sb.append(String.valueOf((intIp & 0x00FFFFFF) >>> 16));   
        sb.append(".");   
        //将高16位置0，然后右移8位   
        sb.append(String.valueOf((intIp & 0x0000FFFF) >>> 8));   
        sb.append(".");   
        //将高24位置0   
        sb.append(String.valueOf((intIp & 0x000000FF)));   
        return sb.toString();   
    }

    public static void main(String[] args) throws ConnectTimeoutException, HttpException {
		//提交给昆山医院
			Map map = new HashMap();
			map.put("operatorCode", "LX_MNIS");
			map.put("token", "");

			JSONObject obj = new JSONObject();
			obj.put("WardCode", "412");
			obj.put("CureNo", "597329");
			obj.put("Key", "");
			obj.put("ExecuterCode", "");
			obj.put("Tokens", "");

			LOG.info("param:{}",JSONObject.toJSONString(obj));
			LOG.info("map:{}",JSONObject.toJSONString(map));
			LOG.info("url:{}","http://172.16.200.6:8100/ExecuteOrderByScan/");
			String returnString = HttpRequestUtil.sendPostMnis(JSONObject.toJSONString(obj), map, "http://172.16.200.6:8100/ExecuteOrderByScan/", null, 0);
			LOG.info(returnString);
	}
}

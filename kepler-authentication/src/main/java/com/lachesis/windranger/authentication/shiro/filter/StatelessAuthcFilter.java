package com.lachesis.windranger.authentication.shiro.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;

import com.lachesis.windranger.authentication.shiro.realm.StatelessToken;
import com.lachesis.windranger.authentication.shiro.util.Constants;
import com.lachesis.windranger.common.exception.AppException;


public class StatelessAuthcFilter extends AccessControlFilter {

	@SuppressWarnings("unused")
	private final static String TEXT_HTML_VALUE = "text/html";
	private final static String APPLICATION_JSON_VALUE = "application/json";
	private final static String APPLICATION_JSON_UTF8_VALUE = APPLICATION_JSON_VALUE + ";charset=UTF-8";
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		return true;
		
		/*//1、客户端生成的消息摘要
		String clientDigest = request.getParameter(Constants.PARAM_DIGEST);
		//2、客户端传入的用户身份
		String username = request.getParameter(Constants.PARAM_USERNAME);
		//3、客户端请求的参数列表
		Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
		params.remove(Constants.PARAM_DIGEST);

		//4、生成无状态Token
		StatelessToken token = new StatelessToken(username, params, clientDigest);

		try {
			//5、委托给Realm进行登录
			getSubject(request, response).login(token);
		} catch (Exception e) {
			e.printStackTrace();
			onLoginFail(response, e); //6、登录失败
			return false;
		}
		return true;*/
	}

	//登录失败时默认返回401状态码
	private void onLoginFail(ServletResponse response, Exception ex) throws IOException {

		String jsonString = AppException.getExceptionJsonString(ex, "Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
		
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		//使用response返回      
		httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //设置状态码
		httpResponse.setContentType(StatelessAuthcFilter.APPLICATION_JSON_UTF8_VALUE); //设置ContentType  
		httpResponse.setCharacterEncoding("UTF-8"); //避免乱码  
		httpResponse.setHeader("Cache-Control", "no-cache, must-revalidate");  		
		httpResponse.getWriter().write(jsonString);
	}
}

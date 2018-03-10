/**   
 * @Title: AuthenticationInterceptor.java 
 * @Package com.lachesis.windranger.interceptor 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author Xi   
 * @date 2017年4月5日 下午8:49:54 
 * @version V1.0   
 */
package com.lachesis.windranger.config.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lachesis.windranger.authentication.dbmodel.SysToken;
import com.lachesis.windranger.authentication.service.IAuthenticationService;
import com.lachesis.windranger.common.constants.CommonConstants;
import com.lachesis.windranger.common.constants.ExceptionMessage;
import com.lachesis.windranger.common.util.AESUtil;
import com.lachesis.windranger.common.util.StringUtils;

/**
 * @ClassName: AuthenticationInterceptor
 * @Description: 拦截器，安全方面做Token验证
 * @author Xi xi.yang@lachesis-mh.com
 * @date 2017年4月5日 下午8:49:54
 * 
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticationInterceptor.class);
	
	@Autowired
	IAuthenticationService authenticationService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	/**	 在实施的时候，请放开进行token验证。
	    String rui = request.getRequestURI();
		String queryString = request.getHeader("Authorization");
		if(rui.trim().equals("")){
			return true;
		}else if(rui.contains("/windranger/v2/api-docs")||rui.contains("/authentication/tokens")){ //请在上线前去除掉此，保证安全 
			return true;
		}
		// 获取设备类型等
		try {
			if (queryString != null && queryString.length() > 0) {
				String[] params = queryString.split("&");
				String ipAddress = IPAddressUtil.getIpAddress(request);
				boolean isMobile = HttpRequestDeviceUtils.isMobileDevice(request);
				for (String str : params) {
					// 验证token的合法性
					if (tokenVerification(str, isMobile, ipAddress)) {
						return true;
					}
				}
			}
			printInterceptorInfo(response);
		}catch(Exception e){
			printInterceptorInfo(response);
		}
			
		return false;*/
	}

	public void printInterceptorInfo(HttpServletResponse response) throws IOException {
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
		HttpStatus responseStatus = HttpStatus.UNAUTHORIZED;
		response.sendError(responseStatus.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); //设置ContentType  
		response.setCharacterEncoding("UTF-8"); //避免乱码  
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] b = new byte[100];
		int n = 0;
		while (-1 != (n = inputStream.read(b))) {
			swapStream.write(b, 0, n);
		}
		response.getOutputStream().write(swapStream.toByteArray());
	}

	public boolean tokenVerification(String str, boolean isMobile, String ipaddress) throws Exception {
		if (str != null && str.length() > 0) {

			SysToken token = decriptToken(str);
			token = authenticationService.getSysToken(token);
			if (token != null && token.getIsmobile() == StringUtils.booleanToInt(isMobile)
					&& ipaddress.equals(token.getIpaddress())
					&& token.getExpiredate().getTime() >= new Date().getTime()) {
				authenticationService.updateToken(token);
				return true;
			}
		}
		return false;
	}

	public SysToken decriptToken(String str) {
		SysToken token = new SysToken();
		try {
			String deStr = AESUtil.decrypt(str, CommonConstants.AES_SEED);
			if (deStr != null && deStr.contains(CommonConstants.TOKEN_EXPIRE_TIME) && deStr.contains(CommonConstants.TOKEN_IP)
					&& deStr.contains(CommonConstants.TOKEN_USER_NAME)) {
				String[] strSplit = deStr.split("&");

				for (String strLoop : strSplit) {
					if (strLoop != null && strLoop.contains(CommonConstants.TOKEN_USER_NAME)
							&& strLoop.length() > CommonConstants.TOKEN_USER_NAME.length()) {
						token.setUserCode(strLoop.substring(strLoop.indexOf(CommonConstants.TOKEN_USER_NAME)
								+ CommonConstants.TOKEN_USER_NAME.length(), strLoop.length()));
					} else if (strLoop != null && strLoop.contains(CommonConstants.TOKEN_IP)
							&& strLoop.length() > CommonConstants.TOKEN_IP.length()) {
						token.setIpaddress(strLoop.substring(
								strLoop.indexOf(CommonConstants.TOKEN_IP) + CommonConstants.TOKEN_IP.length(),
								strLoop.length()));
					} else if (strLoop != null && strLoop.contains(CommonConstants.TOKEN_EXPIRE_TIME)
							&& strLoop.length() > CommonConstants.TOKEN_EXPIRE_TIME.length()) {
						token.setExpiredate(
								new Date(
										Long.valueOf(strLoop.substring(
												strLoop.indexOf(CommonConstants.TOKEN_EXPIRE_TIME)
														+ CommonConstants.TOKEN_EXPIRE_TIME.length(),
												strLoop.length()))));
					} else {
						token.setIsvalid(0);
						return token;
					}
				}
				token.setIsvalid(1);
				return token;
			}
		} catch (Exception e) {
			LOG.error(ExceptionMessage.DECRYPT_EXCEPTION);
			token.setIsvalid(0);
			return token;
		}
		return token;
	}
}

package com.lachesis.windranger.config.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lachesis.windranger.common.constants.ExceptionMessage;

public class RequestUtil {
	
	private RequestUtil(){
		throw new IllegalStateException(ExceptionMessage.UTILITY_CLASS);
	}
	
	public static HttpServletRequest getRequest(){
    	return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
}

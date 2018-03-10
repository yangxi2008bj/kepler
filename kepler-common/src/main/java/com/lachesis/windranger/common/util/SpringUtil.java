package com.lachesis.windranger.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware{
	 
    private static ApplicationContext applicationContext;
 
    private ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
    	SpringUtil.applicationContext = applicationContext;
    }
     
    @SuppressWarnings("unchecked")
	public <T> T getBean(String name) throws BeansException {
        return (T) applicationContext.getBean(name);  
    }
}
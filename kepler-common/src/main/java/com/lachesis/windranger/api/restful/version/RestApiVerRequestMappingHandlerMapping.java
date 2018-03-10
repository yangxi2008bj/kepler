/**
* Project Name: windranger-restful
* File Name: CustomRequestMappingHandlerMapping.java
* Package Name: com.lachesis.windranger.restful.version
* Date: 2017年6月27日下午5:30:26
* Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
*
*/
package com.lachesis.windranger.api.restful.version;

import java.lang.reflect.Method;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
* ClassName: CustomRequestMappingHandlerMapping <br/>
* Function: TODO ADD FUNCTION. <br/>
* Reason: TODO ADD REASON. <br/>
* Date: 2017年6月27日 下午5:30:26 <br/>
* @author 胡诗蔚
* @version
* @since JDK 1.7.0.45
* @see
*/
public class RestApiVerRequestMappingHandlerMapping  extends RequestMappingHandlerMapping {

	@Override
    protected RequestCondition<RestApiVerRequestCondition> getCustomTypeCondition(Class<?> handlerType) {
        RestApiVer apiVersion = AnnotationUtils.findAnnotation(handlerType, RestApiVer.class);
        return createCondition(apiVersion);
    }
 
    @Override
    protected RequestCondition<RestApiVerRequestCondition> getCustomMethodCondition(Method method) {
        RestApiVer apiVersion = AnnotationUtils.findAnnotation(method, RestApiVer.class);
        return createCondition(apiVersion);
    }
     
    private RequestCondition<RestApiVerRequestCondition> createCondition(RestApiVer apiVersion) {    	
    	
    	return apiVersion == null ? null : new RestApiVerRequestCondition(apiVersion.value());
    }
	
}

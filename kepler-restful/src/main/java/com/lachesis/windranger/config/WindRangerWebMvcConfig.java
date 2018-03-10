/**
* Project Name: windranger-restful
* File Name: Config.java
* Package Name: com.lachesis.windranger.restful.version
* Date: 2017年6月27日下午5:31:23
* Copyright (c) 2017, Shenzhen Lachesis Mhealth Co., Ltd All Rights Reserved.
*
*/
package com.lachesis.windranger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lachesis.windranger.api.restful.version.RestApiVerRequestMappingHandlerMapping;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: Config <br/>
 * Function: Windranger MVC Configuration <br/>
 * Date: 2017年6月27日 下午5:31:23 <br/>
 * 
 * @author 胡诗蔚
 * @version
 * @since JDK 1.7.0.45
 * @see
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
public class WindRangerWebMvcConfig extends WebMvcConfigurationSupport {

	@Override
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {

		RequestMappingHandlerMapping handlerMapping = new RestApiVerRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(getInterceptors());
		return handlerMapping;
	}

	/**
	 * Every Docket bean is picked up by the swagger-mvc framework - allowing
	 * for multiple swagger groups i.e. same code base multiple swagger resource
	 * listings.
	 *
	 * @author 胡诗蔚
	 * @return
	 * @since JDK 1.7.0.45
	 */
	@Bean
	public Docket customDocket() {

		Docket aDocket = new Docket(DocumentationType.SWAGGER_2);

		Contact authorContact = new Contact("WindRanger", "", "shiwei.hu@lachesis-mh.com");
		ApiInfo apiInfo = new ApiInfo("Lachesis WindRanger API Document", "WindRanger用于构建联新整体智慧医院的接口平台", "V1.0", "",
				authorContact, "", "");
		aDocket.apiInfo(apiInfo);

		return aDocket;
	}
}

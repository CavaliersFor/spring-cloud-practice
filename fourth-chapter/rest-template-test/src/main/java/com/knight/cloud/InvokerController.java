/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: InvokerController.java 
 * @Prject: rest-template-test
 * @Package: com.knight.cloud 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年2月25日 下午3:59:24 
 * @version: V1.0   
 */
package com.knight.cloud;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 在控制器中使用RestTemplate
 * 
 * @ClassName: InvokerController
 * @author: knight
 * @date: 2018年2月25日 下午3:59:24
 */
@Configuration
@RestController
public class InvokerController {

	@Bean
	@MyLoadBalanced
	public RestTemplate getMyRestTemplate() {
		return new RestTemplate();
	}

	/**
	 * 
	 * @Title: router
	 * @author knight
	 */
	@RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String router() {
		RestTemplate restTpl = getMyRestTemplate();
		// 该请求url将会被置换
		String json = restTpl.getForObject("http://my-server/hello", String.class);
		return json;
	}
}

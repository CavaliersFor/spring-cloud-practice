/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyAutoConfigration.java 
 * @Prject: rest-template-test
 * @Package: com.knight.cloud 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年2月25日 下午3:42:19 
 * @version: V1.0   
 */
package com.knight.cloud;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

/** 
 * Spring配置类，在初始化的Bean中为RestTemplate实例设置自定义拦截器
 * @ClassName: MyAutoConfigration 
 * @author: knight
 * @date: 2018年2月25日 下午3:42:19  
 */
@Configuration
public class MyAutoConfiguration {
	
	@Autowired(required=false)
	@MyLoadBalanced
	private List<RestTemplate> myTemplates = Collections.emptyList();
	
	/**
	 * 
	 * @return SmartInitializingSingleton(所有非lazy单例Bean实例化完成后的回调方法)
	 * @Title: myLoadBalancedRestTemplateInitializer
	 * @author knight
	 */
	@Bean
	public SmartInitializingSingleton myLoadBalancedRestTemplateInitializer() {
		System.out.println("======= 这个bean将在容器初始化时创建 ========");
		return new SmartInitializingSingleton() {
			
			/**
			 * 所有非lazy单例Bean实例化完成后的回调方法
			 */
			@Override
			public void afterSingletonsInstantiated() {
				for(RestTemplate tpl : myTemplates) {
					// 创建一个自定义的拦截器
					MyInterceptor myInterceptor = new MyInterceptor();
					// 获取RestTemplate原来的拦截器
					List<ClientHttpRequestInterceptor> interceptors = tpl.getInterceptors();
					// 自定义的拦截器添加到原来的拦截器
					interceptors.add(myInterceptor);
					// 将新的拦截器集合设置到RestTemplate实例
					tpl.setInterceptors(interceptors);
				}
			}
		};
	}

}

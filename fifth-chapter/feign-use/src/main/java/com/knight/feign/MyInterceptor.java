/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyInterceptor.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月3日 上午11:46:36 
 * @version: V1.0   
 */
package com.knight.feign;

import feign.Feign;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/** 
 * 自定义请求拦截器，可以对发送的模板进行操作，例如设置请求头的属性
 * @ClassName: MyInterceptor 
 * @author: knight
 * @date: 2018年3月3日 上午11:46:36  
 */
public class MyInterceptor implements RequestInterceptor{

	/* 何止请求头
	 * @param template
	 * @Title: apply 
	 * @see feign.RequestInterceptor#apply(feign.RequestTemplate) 
	 */
	@Override
	public void apply(RequestTemplate template) {
		template.header("Content-Type", "application/json");
	}
	
	public static void main(String[] args) {
		PersonClient personClient = Feign.builder().requestInterceptor(new MyInterceptor()).target(PersonClient.class,
				"http://localhost:8080/");
		String result = personClient.sayHello();
		System.out.println(result);
	}

}

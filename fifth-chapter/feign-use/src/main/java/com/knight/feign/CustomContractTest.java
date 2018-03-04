/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: CustomContractTest.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月1日 下午9:42:17 
 * @version: V1.0   
 */
package com.knight.feign;

import feign.Feign;

/** 
 * 
 * Feign解析第三方注解原理,自定义Contract测试
 * Spring Cloud也实现了对应的Contract，可以在接口中使用@RequestMaping，也和我们自定义的Contract类似
 * @ClassName: CustomContractTest 
 * @author: knight
 * @date: 2018年3月1日 下午9:42:17  
 */
public class CustomContractTest {
	public static void main(String[] args) {
		HelloClient helloClient = Feign.builder().contract(new MyContract()).target(HelloClient.class,
				"http://localhost:8080/");
		String result = helloClient.myHello();
		System.out.println("   接口响应："+result);
	}
}

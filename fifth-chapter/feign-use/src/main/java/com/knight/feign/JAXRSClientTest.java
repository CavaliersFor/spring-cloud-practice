/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: JAXRSClientTest.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月1日 下午9:02:19 
 * @version: V1.0   
 */
package com.knight.feign;

import feign.Feign;
import feign.jaxrs.JAXRSContract;

/** 
 * 使用JAXRS规范创建Client
 * @ClassName: JAXRSClientTest 
 * @author: knight
 * @date: 2018年3月1日 下午9:02:19  
 */
public class JAXRSClientTest {
	public static void main(String[] args) {
		// 调用contract方法设置JAXRS注解的解析，告诉Feign使用的是JAXRS
		RSClient rsClient = Feign.builder().contract(new JAXRSContract()).target(RSClient.class,
				"http://localhost:8080/");
		String result = rsClient.rsHello();
		System.out.println(result);
	}
}

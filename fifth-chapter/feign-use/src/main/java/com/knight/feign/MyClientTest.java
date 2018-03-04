/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyClientTest.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年2月28日 下午9:54:12 
 * @version: V1.0   
 */
package com.knight.feign;

import feign.Feign;
import feign.gson.GsonEncoder;

/**
 * 对MyFeignClient类进行测试
 * 
 * @ClassName: MyClientTest
 * @author: knight
 * @date: 2018年2月28日 下午9:54:12
 */
public class MyClientTest {
	public static void main(String[] args) {
		PersonClient personClient = Feign.builder().encoder(new GsonEncoder()).client(new MyFeignClient())
				.target(PersonClient.class, "http://localhost:8080/");


		String result = personClient.sayHello();
		System.out.println("接口响应结果："+result);
	}
}

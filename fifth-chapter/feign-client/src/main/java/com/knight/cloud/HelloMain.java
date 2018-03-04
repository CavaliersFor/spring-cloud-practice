package com.knight.cloud;

import feign.Feign;

/**
 * 服务接口运行类
 * @author knight
 *
 */
public class HelloMain {
	public static void main(String[] args) {
		// 创建HelloClient接口服务的实例
		HelloClient hello = Feign.builder().target(HelloClient.class, "http://localhost:8080/");
		System.out.println(hello.sayHello());
	}
}

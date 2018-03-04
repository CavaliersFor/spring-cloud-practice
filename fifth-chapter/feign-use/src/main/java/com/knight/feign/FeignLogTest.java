package com.knight.feign;

import feign.Feign;
import feign.Logger;

/**
 * 接口日志，可以清楚的显示接口的调用情况
 * 
 * @ClassName: FeignLogTest
 * @author: knight
 * @date: 2018年3月3日 下午1:46:17
 */
public class FeignLogTest {
	public static void main(String[] args) {
		// logLevel可以有以下候选值：
		// NONE：默认值，不记录日志
		// BASIC：记录请求方法、url、响应状态码和执行时间
		// HEADERS：除了BASIC记录的信息外，还包括请求头和响应头
		// FULL：记录全部日志
		PersonClient person = Feign.builder().logLevel(Logger.Level.FULL)
				.logger(new Logger.JavaLogger().appendToFile("D:\\tmp\\http.log"))
				.requestInterceptor(new MyInterceptor()).target(PersonClient.class, "http://localhost:8080/");
		person.sayHello();
		// 保存日志记录如下：

		/*
		 * [PersonClient#sayHello] ---> GET http://localhost:8080/hello HTTP/1.1
		 * [PersonClient#sayHello] ---> END HTTP (0-byte body) [PersonClient#sayHello]
		 * <--- HTTP/1.1 200 (38ms) [PersonClient#sayHello] content-length: 11
		 * [PersonClient#sayHello] content-type: text/plain;charset=UTF-8
		 * [PersonClient#sayHello] date: Sat, 03 Mar 2018 06:02:25 GMT
		 * [PersonClient#sayHello] [PersonClient#sayHello] Hello World
		 * [PersonClient#sayHello] <--- END HTTP (11-byte body)
		 */

	}
}

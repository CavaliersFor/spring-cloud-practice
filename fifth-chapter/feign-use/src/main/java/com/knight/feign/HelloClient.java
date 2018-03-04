/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: HelloClient.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月1日 下午9:35:45 
 * @version: V1.0   
 */
package com.knight.feign;

/**
 * 
 * Feign解析第三方注解原理，自定义注解
 * 
 * @ClassName: HelloClient
 * @author: knight
 * @date: 2018年3月1日 下午9:35:45
 */
public interface HelloClient {

	@MyUrl(method = "GET", url = "/hello")
	String myHello();
}

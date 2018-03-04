/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyUrl.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月1日 下午9:32:06 
 * @version: V1.0   
 */
package com.knight.feign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Feign解析第三方注解原理，自定义注解
 * 
 * @ClassName: MyUrl
 * @author: knight
 * @date: 2018年3月1日 下午9:32:06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {
	
	// 定义url与method属性
	String url();
	String method();
}

package com.knight.cloud.contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义注解
 * @ClassName: MyUrl
 * @author: knight
 * @date: 2018年3月4日 下午2:16:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyUrl {

	String url();

	String method();
}

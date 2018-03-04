package com.knight.cloud.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.cloud.netflix.feign.support.SpringMvcContract;

import feign.MethodMetadata;

/**
 * @Description: 自定义的Contract
 * @ClassName: MyContract
 * @author: knight
 * @date: 2018年3月4日 下午2:10:42
 */
public class MyContract extends SpringMvcContract {

	/* 用于处理方法级别的注解
	 * @param data
	 * @param methodAnnotation
	 * @param method
	 * @Title: processAnnotationOnMethod 
	 * @see org.springframework.cloud.netflix.feign.support.SpringMvcContract#processAnnotationOnMethod(feign.MethodMetadata, java.lang.annotation.Annotation, java.lang.reflect.Method) 
	 */
	@Override
	protected void processAnnotationOnMethod(MethodMetadata data, Annotation methodAnnotation, Method method) {
		super.processAnnotationOnMethod(data, methodAnnotation, method);
		// 是MyUrl注解才处理
		if(MyUrl.class.isInstance(methodAnnotation)) {
			// 获取MyUrl注解实例
			MyUrl myUrlAnn = method.getAnnotation(MyUrl.class);
			// 获取请求方法
			String httpMethod = myUrlAnn.method();
			// 获取请求url
			String httpUrl = myUrlAnn.url();
			// 设置到模板中
			data.template().method(httpMethod);
			data.template().append(httpUrl);
			
		}
	}
}

/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: MyContract.java 
 * @Prject: feign-use
 * @Package: com.knight.feign 
 * @Description: TODO
 * @author: YQ   
 * @date: 2018年3月1日 下午9:36:45 
 * @version: V1.0   
 */
package com.knight.feign;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import feign.Contract.BaseContract;
import feign.MethodMetadata;

/** 
 * Feign解析第三方注解原理,自定义Contract
 * @ClassName: MyContract 
 * @author: knight
 * @date: 2018年3月1日 下午9:36:45  
 */
public class MyContract extends BaseContract{

	/* 类级别的注解
	 * @param data
	 * @param clz
	 * @Title: processAnnotationOnClass 
	 * @see feign.Contract.BaseContract#processAnnotationOnClass(feign.MethodMetadata, java.lang.Class) 
	 */
	@Override
	protected void processAnnotationOnClass(MethodMetadata data, Class<?> clz) {
		
	}

	/* 方法级别的注解，我们这里只有方法级别的注解
	 * @param data
	 * @param annotation
	 * @param method
	 * @Title: processAnnotationOnMethod 
	 * @see feign.Contract.BaseContract#processAnnotationOnMethod(feign.MethodMetadata, java.lang.annotation.Annotation, java.lang.reflect.Method) 
	 */
	@Override
	protected void processAnnotationOnMethod(MethodMetadata data, Annotation annotation, Method method) {
		// 是MyUrl注解才进行处理
		if(MyUrl.class.isInstance(annotation)) {
			// 获取注解的实例
			MyUrl myUrlAnn = method.getAnnotation(MyUrl.class);
			// 获取配置的方法
			String httpMethod = myUrlAnn.method();
			//获取配置的url
			String httpUrl = myUrlAnn.url();
			// 将值设置到模板中
			data.template().method(httpMethod);
			data.template().append(httpUrl);
		}
		
	}

	/* 参数级别的注解 
	 * @param data
	 * @param annotations
	 * @param paramIndex
	 * @return
	 * @Title: processAnnotationsOnParameter 
	 * @see feign.Contract.BaseContract#processAnnotationsOnParameter(feign.MethodMetadata, java.lang.annotation.Annotation[], int) 
	 */
	@Override
	protected boolean processAnnotationsOnParameter(MethodMetadata data, Annotation[] annotations, int paramIndex) {
		return false;
	}

}

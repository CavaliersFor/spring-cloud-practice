package com.knight.cloud.contract;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;

/** 
 * @Description: 自定义注解翻译器
 * @ClassName: MyConfig 
 * @author: knight
 * @date: 2018年3月4日 下午2:09:18  
 */
@Configuration
public class MyConfig {
	
	/**
	 * 返回一个自定义的Contract
	 * @return
	 * @Title: feignContract
	 * @author knight
	 */
	@Bean
	public Contract feignContract() {
		return new MyContract();
	}
}

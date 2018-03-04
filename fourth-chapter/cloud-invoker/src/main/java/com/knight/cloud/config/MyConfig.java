package com.knight.cloud.config;

import org.springframework.context.annotation.Bean;

import com.knight.cloud.MyPing;
import com.knight.cloud.MyRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;

public class MyConfig {

	@Bean
	public IRule getRule() {
		return new MyRule();
	}

	@Bean
	public IPing getPing() {
		return new MyPing();
	}

}

package com.knight.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//声明该应用是一个Eureka客户端
@EnableEurekaClient
@SpringBootApplication
public class FirstServiceProvider {

	public static void main(String[] args) {
		new SpringApplicationBuilder(FirstServiceProvider.class).run(args);
	}
}

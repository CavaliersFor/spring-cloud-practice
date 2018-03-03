package com.knight.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//EnableEurekaServer注解声明这是一个Eureka服务器
@EnableEurekaServer
@SpringBootApplication
public class FirstServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(FirstServer.class).run(args);
	}
}

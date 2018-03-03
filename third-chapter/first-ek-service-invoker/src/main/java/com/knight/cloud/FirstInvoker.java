package com.knight.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//该注解使得服务调用者有能力去Eureka中发现服务
@EnableDiscoveryClient
public class FirstInvoker {
	public static void main(String[] args) {
		SpringApplication.run(FirstInvoker.class, args);
	}
}

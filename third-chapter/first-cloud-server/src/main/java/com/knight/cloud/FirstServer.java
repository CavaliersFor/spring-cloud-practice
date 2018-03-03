package com.knight.cloud;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//EnableEurekaServer注解声明这是一个Eureka服务器
@EnableEurekaServer
@SpringBootApplication
public class FirstServer {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String profiles = scanner.nextLine();
		new SpringApplicationBuilder(FirstServer.class).profiles(profiles).run(args);
		scanner.close();
	}
}

package com.knight.cloud;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//声明该应用是一个Eureka客户端
@EnableEurekaClient
@SpringBootApplication
public class FirstServiceProvider {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String port = scanner.nextLine();
		new SpringApplicationBuilder(FirstServiceProvider.class).properties("server.port=" + port).run(args);
		scanner.close();
	}
}

package com.knight.cloud;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 服务提供者，Eureka客户端
 * @ClassName: ProviderApplication
 * @author: knight
 * @date: 2018年3月3日 下午4:34:55
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication {

	public static void main(String[] args) throws Exception {

		// 读取控制台输入的端口，避免端口冲突
		Scanner scan = new Scanner(System.in);
		String port = scan.nextLine();
		new SpringApplicationBuilder(ProviderApplication.class).properties("server.port=" + port).run(args);
		scan.close();
	}

}

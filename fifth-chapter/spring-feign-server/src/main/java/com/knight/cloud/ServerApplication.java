package com.knight.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
 * @Description: 发现服务
 * @ClassName: ServerApplication 
 * @author: knight
 * @date: 2018年3月3日 下午3:16:28  
 */
@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ServerApplication.class, args);
	}

}

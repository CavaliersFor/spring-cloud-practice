package com.knight.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/** 
 * @Description: 服务调用者的启动类
 * @ClassName: InvokerApplication 
 * @author: knight
 * @date: 2018年3月3日 下午4:49:52  
 */
@SpringBootApplication
@EnableEurekaClient
// 打开Feign开关
@EnableFeignClients
public class InvokerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(InvokerApplication.class, args);
	}
}

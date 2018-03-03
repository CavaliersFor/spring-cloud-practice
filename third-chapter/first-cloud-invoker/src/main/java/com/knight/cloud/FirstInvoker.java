package com.knight.cloud;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
// 该注解使得服务调用者有能力去Eureka中发现服务
@EnableDiscoveryClient
public class FirstInvoker {
	public static void main(String[] args) {
		SpringApplication.run(FirstInvoker.class, args);
		// 通过httpClient模拟调用
		CloseableHttpClient httpClient = HttpClients.createDefault();
		for (int i = 0; i < 6; i++) {
			HttpGet httpget = new HttpGet("http://localhost:9000/router");
			try {
				HttpResponse response = httpClient.execute(httpget);
				System.out.println(EntityUtils.toString(response.getEntity()));
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

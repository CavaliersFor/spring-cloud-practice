package com.knight.cloud;

import com.netflix.client.ClientException;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

/**
 * 使用配置的方式
 * 
 * @author knight
 *
 */
public class TestMyRuleConifg {
	public static void main(String[] args) throws Exception {
		// 设置请求的服务器
		ConfigurationManager.getConfigInstance().setProperty("my-client.ribbon.listOfServers",
				"localhost:8080,localhost:8081");
		// 设置自定义的负载规则
		ConfigurationManager.getConfigInstance().setProperty("my-client.ribbon.NFLoadBalancerRuleClassName",
				MyRule.class.getName());
		// 获取具有负载均衡的REST请求客户端
		RestClient client = (RestClient) ClientFactory.getNamedClient("my-client");

		HttpRequest request = HttpRequest.newBuilder().uri("/person/1").build();
		// 模拟请求
		for (int i = 0; i < 6; i++) {
			HttpResponse response = client.executeWithLoadBalancer(request);
			String result = response.getEntity(String.class);
			System.out.println(result);
		}
	}
}

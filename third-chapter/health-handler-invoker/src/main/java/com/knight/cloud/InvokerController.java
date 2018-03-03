package com.knight.cloud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;

@RestController
@Configuration
public class InvokerController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@Bean
	// 被该注解修饰后具有访问分布式服务的能力
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String router() {
		//获取服务实例
		List<ServiceInstance> ins = getServiceInstance();

		for (ServiceInstance service : ins) {
			EurekaServiceInstance esi = (EurekaServiceInstance) service;
			InstanceInfo info = esi.getInstanceInfo();
			System.out.println(info.getAppName() + "-----" + info.getInstanceId() + "----" + info.getStatus());
		}

		return "";
	}

	/**
	 * 查询可用服务
	 * @return
	 */
	private List<ServiceInstance> getServiceInstance() {
		List<String> ids = discoveryClient.getServices();
		List<ServiceInstance> result = new ArrayList<>();

		for (String id : ids) {
			List<ServiceInstance> ins = discoveryClient.getInstances(id);
			result.addAll(ins);
		}

		return result;

	}

}

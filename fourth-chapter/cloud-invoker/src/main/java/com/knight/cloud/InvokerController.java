package com.knight.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Configuration
public class InvokerController {

	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/router", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String router() {
		RestTemplate restTpl = getRestTemplate();
		// 根据名称调用服务
		String json = restTpl.getForObject("http://cloud-provider/person/1", String.class);
		return json;
	}

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@RequestMapping(value = "/uselb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceInstance uselb() {
		ServiceInstance si = loadBalancerClient.choose("cloud-provider");
		return si;
	}

}

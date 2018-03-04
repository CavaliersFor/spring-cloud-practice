package com.knight.cloud.contract;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 测试自定义ContractClient
 * @ClassName: HelloClient
 * @author: knight
 * @date: 2018年3月4日 下午2:26:02
 */
@FeignClient(name = "spring-feign-provider")
public interface HelloClient {

	@MyUrl(method = "GET", url = "/")
	String myHello();

	@RequestMapping(method = RequestMethod.GET, value = "/")
	String springHello();
}

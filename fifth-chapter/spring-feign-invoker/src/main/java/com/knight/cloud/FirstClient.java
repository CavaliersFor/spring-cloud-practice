package com.knight.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 服务调用者
 * @ClassName: FirstClient
 * @author: knight
 * @date: 2018年3月3日 下午4:51:27
 */
// 声明调用的服务名
@FeignClient("spring-feign-provider")
public interface FirstClient {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	String hello();
}

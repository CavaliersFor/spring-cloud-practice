package com.knight.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knight.cloud.entity.Person;

/**
 * @Description: 调用provider的获取Person的接口
 * @ClassName: PersonClient
 * @author: knight
 * @date: 2018年3月3日 下午5:11:54
 */
//声明调用的服务名
@FeignClient("spring-feign-provider")
public interface PersonClient {

	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET)
	Person getPerson(@PathVariable("personId") Integer personId);

}

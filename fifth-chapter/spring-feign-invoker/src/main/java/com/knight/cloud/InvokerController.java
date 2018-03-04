package com.knight.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knight.cloud.contract.HelloClient;
import com.knight.cloud.entity.Person;

/**
 * @Description: Invoker者Contoller
 * @ClassName: InvokerController
 * @author: knight
 * @date: 2018年3月3日 下午4:53:47
 */
@RestController
@Configuration
public class InvokerController {

	@Autowired
	private FirstClient firstClient;

	@Autowired
	private PersonClient personClient;

	@Autowired
	private HelloClient helloClient;

	@RequestMapping(value = "/invokeHello", method = RequestMethod.GET)
	public String invokeHello() {
		return firstClient.hello();
	}

	@RequestMapping(value = "/router", method = RequestMethod.GET)
	public String router() {
		Person person = personClient.getPerson(2);
		return person.getMessage();
	}

	@RequestMapping(value = "/testContract", method = RequestMethod.GET)
	public String testContract() {
		String springResult = helloClient.springHello();
		System.out.println("使用@RequestMapping注解的接口返回结果：" + springResult);
		String myResult = helloClient.myHello();
		System.out.println("使用@MyUrl注解的接口返回结果：" + myResult);
		return "";
	}

}

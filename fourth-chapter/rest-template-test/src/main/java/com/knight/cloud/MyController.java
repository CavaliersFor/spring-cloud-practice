package com.knight.cloud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	/**
	 * 最终的请求会转发到这
	 * @Title: hello
	 * @author knight
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
}

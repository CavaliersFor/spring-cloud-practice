package com.knight.feign;

import feign.Headers;
import feign.RequestLine;
import lombok.Data;

public interface PersonClient {

	@RequestLine("POST /person/create")
	// 通过Headers标签声明请求的内容类型为json
	@Headers("Content-type: application/json")
	String createPerson(Person person);

	@Data // 为所有属性添加getter和setter方法
	class Person {
		Integer id;
		String name;
		Integer age;
		String message;
	}
}

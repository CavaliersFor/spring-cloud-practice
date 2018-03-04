package com.knight.cloud;

import feign.Param;
import feign.RequestLine;
import lombok.Data;

/**
 * person服务类
 * @author knight
 *
 */
public interface PersonClient {

	@RequestLine("GET /person/{personId}")
	Person findById(@Param("personId") Integer personId);
	
	@Data //为所有属性添加getter和setter方法
	class Person {
		Integer id;
		String name;
		Integer age;
		String message;
	}
}

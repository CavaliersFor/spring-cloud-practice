package com.knight.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import feign.Headers;
import feign.RequestLine;
import lombok.Data;

/**
 * 支持xml格式的接口
 * @author knight
 *
 */
public interface PersonClient {

	@RequestLine("POST /person/createXML")
	@Headers("Content-type: application/xml")
	Result createPersonXML(Person person);
	
	@Data // 为所有属性添加getter和setter方法
	@XmlRootElement
	class Person {
		@XmlElement
		Integer id;
		@XmlElement
		String name;
		@XmlElement
		Integer age;
		@XmlElement
		String message;
	}
	
	@Data
	@XmlRootElement
	class Result{
		@XmlElement
		String message;
	}
}

package com.knight.cloud;

import com.knight.cloud.PersonClient.Person;

import feign.Feign;
import feign.gson.GsonDecoder;

/**
 * Person服务调用类
 * @author knight
 *
 */
public class PersonMain {
	public static void main(String[] args) {
		// 使用GsonDecoder解码器
		PersonClient personService = Feign.builder().decoder(new GsonDecoder()).target(PersonClient.class,"http://localhost:8080/");
		Person person = personService.findById(2);
		System.out.println(person.id);
		System.out.println(person.name);
		System.out.println(person.age);
		System.out.println(person.message);
	}
}

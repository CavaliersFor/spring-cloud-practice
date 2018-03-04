package com.knight.feign;

import com.knight.feign.PersonClient.Person;

import feign.Feign;
import feign.gson.GsonEncoder;

/**
 * Feign解码测试
 * @author knight
 *
 */
public class EncoderTest {
	public static void main(String[] args) {
		PersonClient personClient = Feign.builder().encoder(new GsonEncoder()).target(PersonClient.class,
				"http://localhost:8080/");
		
		Person person = new Person();
		person.id = 1;
		person.name = "knight";
		person.age = 22;
		String response = personClient.createPerson(person);
		System.out.println(response);
	}
}

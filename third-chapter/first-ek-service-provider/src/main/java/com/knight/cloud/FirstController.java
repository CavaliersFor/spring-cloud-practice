package com.knight.cloud;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knight.cloud.entity.Person;

@RestController
public class FirstController {

	@RequestMapping(value = "/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findPerson(@PathVariable("personId") Integer personId) {
		Person person = new Person(personId, "Knight", 21);
		return person;
	}
}

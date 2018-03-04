package com.knight.cloud;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findPerson(@PathVariable("personId") Integer personId, HttpServletRequest request) {
		Person person = new Person();
		person.setId(personId);
		person.setName("Knight");
		person.setAge(33);
		person.setMessage(request.getRequestURL().toString());		
		return person;
	}
}

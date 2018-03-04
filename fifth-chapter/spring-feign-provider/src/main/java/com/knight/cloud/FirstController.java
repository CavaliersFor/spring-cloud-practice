package com.knight.cloud;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.knight.cloud.entity.Person;

/**
 * @Description: 服务Controller
 * @ClassName: FirstController
 * @author: knight
 * @date: 2018年3月3日 下午4:37:48
 */
@RestController
public class FirstController {

	@RequestMapping("/")
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Person findPerson(@PathVariable("personId") Integer personId, HttpServletRequest request) {
		Person person = new Person(personId, "Knight", 21);
		person.setMessage(request.getRequestURL().toString());
		return person;
	}

}

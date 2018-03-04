package com.knight.xml;

import com.knight.xml.PersonClient.Person;
import com.knight.xml.PersonClient.Result;

import feign.Feign;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

/**
 * xml编码解码
 * @author knight
 *
 */
public class XMLTest {
	public static void main(String[] args) {

		JAXBContextFactory jaxbFactory = new JAXBContextFactory.Builder().build();

		PersonClient personClient = Feign.builder().encoder(new JAXBEncoder(jaxbFactory))
				.decoder(new JAXBDecoder(jaxbFactory)).target(PersonClient.class, "http://localhost:8080/");

		Person person = new Person();
		person.id = 1;
		person.name = "xml";
		person.age = 22;

		Result result = personClient.createPersonXML(person);
		System.out.println(result.message);

	}
}

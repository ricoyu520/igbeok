package com.igbeok.jaxb.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igbeok.showcase.xml.entity.jaxb.Address;
import com.igbeok.showcase.xml.entity.jaxb.Person;

@Controller
@RequestMapping("/jaxb")
public class JaxbController {

	@RequestMapping("/person/{id}")
	@ResponseBody
	public Person getPerson(@PathVariable("id") Long id) {
		Person person = new Person();
		person.setBirthday(new Date(1982, 11, 9));
		person.setCellPhone("13913672167");
		person.setFirstName("Rico");
		person.setLastName("Yu");
		person.setGraduateSchool("JiangSu University");
		person.setId(1l);

		Address address = new Address();
		address.setId(1l);
		address.setPostalCode("215125");
		address.setStreatName("钟慧路");
		person.setAddress(address);

		return person;
	}

	@RequestMapping("/persons")
	@ResponseBody
	public List<Person> getPersons() {
		Person person = new Person();
		person.setBirthday(new Date(1982, 11, 9));
		person.setCellPhone("13913672167");
		person.setFirstName("Rico");
		person.setLastName("Yu");
		person.setGraduateSchool("JiangSu University");
		person.setId(1l);

		Address address = new Address();
		address.setId(1l);
		address.setPostalCode("215125");
		address.setStreatName("钟慧路");
		person.setAddress(address);

		Person person2 = new Person("NCS", 10000);
		person2.setBirthday(new Date(1982, 11, 9));
		person2.setCellPhone("13913672167");
		person2.setFirstName("Rico");
		person2.setLastName("Yu");
		person2.setGraduateSchool("JiangSu University");
		person2.setId(1l);

		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(person2);
		return persons;
	}
}

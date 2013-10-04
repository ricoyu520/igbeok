package com.igbeok.jackson.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.igbeok.jpa.entity.Person;

@Controller
@RequestMapping("/jackson")
public class JacksonController {
	
	@RequestMapping("/persons")
	@ResponseBody
	public List<Person> getPersons() {
		Person rico = new Person();
		rico.setAge(31l);
		rico.setName("rico");
		List<Person> persons = new ArrayList<Person>();
		persons.add(rico);
		return persons;
	}

	@RequestMapping("/person/{id}")
	@ResponseBody
	public Person getPerson() {
		Person person = new Person();
		person.setAge(11l);
		person.setJob("Software Engineer");
		person.setMarried(true);
		person.setName("Rico");
		person.setSchool("SooChow");
		person.setEmail("ricoyu_521@hotmail.net");
		return person;
	}

	@RequestMapping(value = "/person/create", method = RequestMethod.POST)
	@ResponseBody
	public Person createPerson(@RequestBody Person person) {
		person.setSchool("Soo Choow University");
		person.setEmail("sss@s.com");
		return person;
	}
}

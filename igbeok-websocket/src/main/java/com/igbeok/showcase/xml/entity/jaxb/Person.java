package com.igbeok.showcase.xml.entity.jaxb;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Person {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthday;
	private String graduate_school;
	private String cellPhone;
	private Address address;
	@XmlElement(name="company")
	private String company;
	@XmlElement(name="companyType")
	private String companyType = "美资企业";
	private Integer income;

	public Person() {
	}

	public Person(String company, Integer income) {
		this.company = company;
		this.setIncome(income);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "my_birtday")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@XmlAttribute(name = "graduate_school")
	public String getGraduateSchool() {
		return graduate_school;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduate_school = graduateSchool;
	}

	@XmlTransient
	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public void setFullName(String fullName) {

	}

	@XmlElement
	public String getDescription() {
		return "你很棒";
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

}

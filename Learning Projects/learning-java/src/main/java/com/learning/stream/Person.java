package com.learning.stream;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Person {

	public Person(String name, LocalDate birthday, Sex gender, String emailAddress) {
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.emailAddress = emailAddress;
	}

	public enum Sex {
		MALE, FEMALE
	}

	String name;
	LocalDate birthday;
	Sex gender;
	String emailAddress;

	public static List<Person> createRoster() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Kumar Chandrakant", LocalDate.of(1982, Month.NOVEMBER, 20), Person.Sex.MALE, "kchandrakant@gmail.com"));
		list.add(new Person("Mansi Rastogi", LocalDate.of(1982, Month.NOVEMBER, 27), Person.Sex.FEMALE, "mansi.rg@gmail.com"));
		list.add(new Person("Aradhya Chandrakant", LocalDate.of(2015, Month.NOVEMBER, 25), Person.Sex.FEMALE, "aradhya.tia@gmail.com"));
		return list;
	}
	
	public String getName() {
		return this.name;
	}

	public int getAge() {

		return Period.between(birthday, LocalDate.now()).getYears();
	}

	public Sex getGender() {

		return this.gender;
	}

	public String getEmailAddress() {

		return this.emailAddress;
	}

	public void printPerson() {

		System.out.println(this.getEmailAddress());
	}
}
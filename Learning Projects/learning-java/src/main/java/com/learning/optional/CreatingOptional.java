package com.learning.optional;

import java.time.LocalDate;
import java.util.Optional;

public class CreatingOptional {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Person p1 = new Person();

		Optional<Person> op1 = Optional.empty();

		Optional<Person> op2 = Optional.of(p1);
		
		Optional<Person> op3 = Optional.ofNullable(p1);
		
		Person p2 = new Person("dec",LocalDate.now(),Person.Sex.MALE,"abc@mail.com");
		
		Optional<Person> op4 = Optional.of(p2);
		
		if(p2.getName() != null){
			 System.out.println(p2.getName());
		}
		
		p2.getName().ifPresent(System.out::println);
		
		
		Optional<String> name = p2.getName();
		
		name
			.filter(x -> "abc".equals(x))
			.ifPresent(System.out::println);
	}

}

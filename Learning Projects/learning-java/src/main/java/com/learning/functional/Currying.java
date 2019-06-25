package com.learning.functional;

import java.time.LocalDate;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Currying {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Letter letter = null;

		Currying play = new Currying();

		BiFunction<String, String, Letter> SIMPLE_LETTER_CREATOR = 
				(salutation,
				body) -> play.new Letter(salutation, body);
				
		letter = SIMPLE_LETTER_CREATOR.apply("Mr.", "Kumar Chandrakant");
		letter = print(SIMPLE_LETTER_CREATOR, "Mr.", "Kumar Chandrakant");
		System.out.println(letter);

		Function<String, Function<String, Letter>> SIMPLE_CURRIED_LETTER_CREATOR = 
				salutation -> body -> play.new Letter(
				salutation, body);
				
		letter = SIMPLE_CURRIED_LETTER_CREATOR.apply("Mr.").apply("Kumar Chandrakant");	
		System.out.println(letter);
				

		Function<String, Function<String, Function<LocalDate, Function<String, Function<String, Function<String, Letter>>>>>> LETTER_CREATOR = 
				returnAddress -> closing -> dateOfLetter -> insideAddress -> salutation -> body -> play.new Letter(
				returnAddress, insideAddress, dateOfLetter, salutation, body,
				closing);
				
				
		BiFunction<Integer, Integer, Integer> composite1 = (a, b) -> a + b; 
		
		composite1 = composite1.andThen(a -> 2 * a); 
		
		System.out.println("Composite1 = " + composite1.apply(2, 3));

	}

	@SuppressWarnings("unused")
	public class Letter {
		private String returningAddress;
		private String insideAddress;
		private LocalDate dateOfLetter;
		private String salutation;
		private String body;
		private String closing;

		Letter(String salutation, String body) {
			this.salutation = salutation;
			this.body = body;
		}

		Letter(String returningAddress, String insideAddress,
				LocalDate dateOfLetter, String salutation, String body,
				String closing) {
			this.returningAddress = returningAddress;
			this.insideAddress = insideAddress;
			this.dateOfLetter = dateOfLetter;
			this.salutation = salutation;
			this.body = body;
			this.closing = closing;
		}
		
		@Override
		public String toString() {
			return salutation + " " + body;
		}
	}

	Letter createLetter(String salutation, String body) {
		return new Letter(salutation, body);
	}
	
	public static <T, U, R> R print(BiFunction<T, U, R> func, T t, U u) {
		return func.apply(t, u);
	}

}

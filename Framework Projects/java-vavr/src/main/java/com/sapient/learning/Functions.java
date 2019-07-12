package com.sapient.learning;

import io.vavr.Function1;

public class Functions {

	public static void main(String[] args) {
		
		// Function Composition
		
		Function1<Integer, Integer> plusOne = a -> a + 1;
		Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

		Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);
		
		System.out.println(add1AndMultiplyBy2.apply(3));

	}
}
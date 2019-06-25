package com.learning.functional;

import java.util.function.BiFunction;

public class Functions {

	public static BiFunction<Integer, Integer, Integer> getFunc(
			String operation) {

		switch (operation) {
			case "add" :
				return Functions::add;
			case "multiply" :
				return Functions::multiply;
			case "subtract" :
				return Functions::subtract;
			case "divide" :
				return Functions::divide;
			default :
				return null;
		}

	}

	public static void main(String[] args) {

		System.out.println(Functions.getFunc("add").apply(2, 3));
		System.out.println(Functions.getFunc("multiply").apply(2, 3));
		System.out.println(Functions.getFunc("subtract").apply(2, 3));
		System.out.println(Functions.getFunc("divide").apply(2, 3));

	}

	public static Integer add(Integer a, Integer b) {
		return a + b;
	}
	public static Integer multiply(Integer a, Integer b) {
		return a * b;
	}
	public static Integer subtract(Integer a, Integer b) {
		return a - b;
	}
	public static Integer divide(Integer a, Integer b) {
		return a / b;
	}

}

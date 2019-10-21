package com.learning.string;

public class ReverseString {

	public static void main(String[] args) {
		
		String str = "Hello World!";
		
		str = reverse(str);

		System.out.println(str);

	}

	private static String reverse(String original) {
		return "\u202E" + original;
	}

}

package com.learning.hackerrank;

import java.util.Arrays;

public class TextWrap {

	public static void main(String[] args) {

		wrapText("How".toCharArray(), 2);

	}

	public static void wrapText(char[] text, int length) {
		boolean done = false;
		int from = 0;
		int to = 0;
		while (!done) {
			to = from + length - 1;
			if (to >= text.length - 1) {
				to = text.length - 1;
				done = true;
			} else if (text[to + 1] != ' ') {
				int temp = to;
				while (temp > from && text[temp] != ' ') {
					temp--;
				}
				if (temp == from) {
					temp = to + length - 1;
					while (temp <= text.length - 1 && text[temp] != ' ') {
						temp++;
					}
				}
				to = temp - 1;
			}
			if (to <= text.length - 1) {
				System.out.println(Arrays.copyOfRange(text, from, to + 1));
				if (to + 2 < text.length - 1) {
					from = to + 2;
				} else {
					done = true;
				}
			}
		}
	}
}

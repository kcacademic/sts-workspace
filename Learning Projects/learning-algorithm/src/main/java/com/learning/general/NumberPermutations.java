package com.learning.general;

import java.util.Arrays;

public class NumberPermutations {

	public static void main(String[] args) {

		findHigherPermutations(new int[] { 2,3,0,1 }, new int[] {}, new int[] { 3,2,1,0 }, true);

	}

	public static void findHigherPermutations(int[] input, int[] previous, int[] remaining, boolean equal) {

		if (equal) {
			for (int i = 0; i < remaining.length; i++) {
				if (input[previous.length] < remaining[i]) {
					findHigherPermutations(input, addToArray(previous, remaining[i]),
							removeFromArray(remaining, remaining[i]), false);
				} else if (input[previous.length] == remaining[i]) {
					findHigherPermutations(input, addToArray(previous, remaining[i]),
							removeFromArray(remaining, remaining[i]), true);
				}
			}
		} else {
			for (int i = 0; i < remaining.length; i++) {
				findHigherPermutations(input, addToArray(previous, remaining[i]),
						removeFromArray(remaining, remaining[i]), false);
			}
		}
		if (previous.length == input.length)
			System.out.println(Arrays.toString(previous));

	}

	public static int[] addToArray(int[] input, int target) {

		int[] output = new int[input.length + 1];
		for (int i = 0; i < input.length; i++)
			output[i] = input[i];
		output[output.length - 1] = target;
		return output;
	}

	public static int[] removeFromArray(int[] input, int target) {
		int[] output = new int[input.length - 1];
		boolean found = false;
		int k = 0;
		for (int i = 0; i < input.length; i++) {
			if (!found) {
				if (input[i] == target) {
					found = true;
				} else {
					output[k] = input[i];
					k++;
				}
			} else {
				output[k] = input[i];
				k++;
			}
		}
		
		return output;
	}

}

package com.learning.hackerrank;

import java.util.Arrays;

public class FinalProblem {

	public static void main(String[] args) {

		System.out.println(new FinalProblem().theFinalProblem(
				new int[] { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 }));

	}

	public int theFinalProblem(int[] target) {
		int flip = 0;
		int[] base = new int[target.length];
		for (int i = 0; i < target.length; i++) {
			if (base[i] == target[i])
				continue;
			else {
				System.out.println("Flip at: " + (i + 1));
				for (int j = i; j < target.length; j++) {
					if (base[j] == 0)
						base[j] = 1;
					else
						base[j] = 0;
				}
				System.out.println(Arrays.toString(base));
				flip++;
			}
		}
		return flip;
	}

}
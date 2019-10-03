package com.learning.hackerrank;

public class PoisonousPlants {

	public static void main(String[] args) {

		System.out.println(poisonousPlants(new int[] { 4, 3, 7, 5, 6, 4, 2 }));

	}

	static int poisonousPlants(int[] p) {

		int day = 0;
		boolean survive = false;

		while (!survive) {
			int k = 0;
			int temp = p[0];
			for (int i = 1; i < p.length; i++) {
				if (p[i] == -1) {
					continue;
				} else if (p[i] > temp) {
					temp = p[i];
					p[i] = -1;
					k++;
				} else {
					temp = p[i];
				}
			}
			if (k == 0)
				survive = true;
			else
				day++;
		}

		return day;
	}

}

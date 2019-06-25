package com.learning.algorithm;

import java.util.Scanner;

public class ArraySearch {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tc = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < tc; i++) {
			String[] lOne = sc.nextLine().split(" ");
			int n = Integer.parseInt(lOne[0]);
			int sum = Integer.parseInt(lOne[1]);
			String[] lTwo = sc.nextLine().split(" ");
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(lTwo[j]);
			}

			int temp = 0;
			int start = 0;
			int end = 0;
			boolean found = false;

			while (start < n || end < n) {
				if (temp < sum) {
					temp = temp + arr[end];
					if (end != n - 1)
						end++;
					System.out.println("Count:" + end + "Temp:" + temp + "Start:" + start + "End:" + end);
				} else if (temp > sum) {
					temp = temp - arr[start];
					if (start != n - 1)
						start++;
					System.out.println("Count:" + end + "Temp:" + temp + "Start:" + start + "End:" + end);
				}

				if (temp == sum) {
					System.out.println((start + 1) + " " + (end));
					found = true;
					break;
				}
			}

			if (!found) {
				System.out.println(-1);
			}
		}

		sc.close();

	}

}

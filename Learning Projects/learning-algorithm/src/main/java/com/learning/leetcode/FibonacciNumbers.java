package com.learning.leetcode;

public class FibonacciNumbers {

	public static void main(String[] args) {

		System.out.println(fibonacciRecursion(9));
		System.out.println(fibonacciDynamicProgramming(9));

	}

	static int fibonacciRecursion(int n) {
		if (n <= 1)
			return n;
		return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
	}

	static int fibonacciDynamicProgramming(int n) {

		int f[] = new int[n + 2];
		int i;

		f[0] = 0;
		f[1] = 1;
		
		for (i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}

		return f[n];
	}

}

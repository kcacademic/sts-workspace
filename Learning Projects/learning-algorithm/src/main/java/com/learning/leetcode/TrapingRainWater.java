package com.learning.leetcode;

public class TrapingRainWater {

	public static void main(String[] args) {

		System.out.println(new TrapingRainWater().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));
		
		System.out.println(new TrapingRainWater().trapDynamicProgramming(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));

	}

	public int trap(int[] height) {

		int total = 0;

		for (int i = 0; i < height.length; i++) {
			int maxLeft = 0;
			int maxRight = 0;
			for (int j = 0; j < i; j++) {
				maxLeft = maxLeft > height[j] ? maxLeft : height[j];
			}
			for (int j = i + 1; j < height.length; j++) {
				maxRight = maxRight > height[j] ? maxRight : height[j];
			}
			int bound = maxLeft >= maxRight ? maxRight : maxLeft;
			int depth = (bound - height[i]) >= 0 ? (bound - height[i]) : 0;
			total = total + depth;
		}

		return total;
	}

	public int trapDynamicProgramming(int[] height) {

		int total = 0;

		int[] leftMax = new int[height.length];
		int[] rightMax = new int[height.length];

		int maxLeft = 0;
		for (int i = 0; i < height.length; i++) {
			if (height[i] > maxLeft) {
				maxLeft = height[i];
				leftMax[i] = maxLeft;
			} else {
				leftMax[i] = maxLeft;
			}
		}

		int maxRight = 0;
		for (int i = height.length - 1; i >= 0; i--) {
			if (height[i] > maxRight) {
				maxRight = height[i];
				leftMax[i] = maxRight;
			} else {
				rightMax[i] = maxRight;
			}
		}

		for (int i = 0; i < height.length; i++) {
			int bound = leftMax[i] >= rightMax[i] ? rightMax[i] : leftMax[i];
			int depth = (bound - height[i]) >= 0 ? (bound - height[i]) : 0;
			total = total + depth;
		}

		return total;
	}

}

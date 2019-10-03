package com.learning.leetcode;

import java.util.Arrays;

public class MaximumGap {

	public static void main(String[] args) {

		System.out.println(new MaximumGap().maximumGap(new int[] { 3, 6, 9, 1 }));

	}

	public int maximumGap(int[] nums) {
		
		if (nums.length < 2)
			return 0;

		Arrays.sort(nums);
		
		int diff = 0;

		for (int i = 1; i < nums.length; i++) {
			if(Math.abs(nums[i]-nums[i-1])>diff)
				diff = Math.abs(nums[i]-nums[i-1]);
		}

		return diff;
	}

}

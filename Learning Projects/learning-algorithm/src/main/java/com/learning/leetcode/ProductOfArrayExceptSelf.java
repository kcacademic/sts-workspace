package com.learning.leetcode;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

	public static void main(String args[]) {

		Solution solution = new ProductOfArrayExceptSelf().new Solution();
		System.out.println(Arrays.toString(solution.productExceptSelf(new int[] { 1, 2, 3, 4, 0 })));
	}

	class Solution {
		public int[] productExceptSelf(int[] nums) {

			int[] output = new int[nums.length];
			int[] left = new int[nums.length];
			int[] right = new int[nums.length];

			for (int i = 0; i < nums.length; i++) {
				if(i==0)
					left[i] = 1;
				else
					left[i] = left[i-1]*nums[i-1];
			}
			
			for (int i = nums.length-1; i >=0; i--) {
				if(i==nums.length-1)
					right[i] = 1;
				else
					right[i] = right[i+1]*nums[i+1];
			}
			
			for (int i = 0; i < nums.length; i++) {
				if(i==0)
					output[i] = right[i];
				else if(i==nums.length-1)
					output[i] = left[i];
				else
					output[i] = left[i]*right[i];
			}

			return output;

		}
	}
}

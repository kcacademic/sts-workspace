package com.learning.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FirstMissingPositive {

	public static void main(String[] args) {
		
		System.out.println(new FirstMissingPositive().firstMissingPositive(new int[] {1,2,0}));

	}

	public int firstMissingPositive(int[] nums) {

		int temp = 1;
		
		Map<Integer, Boolean> map = new HashMap<>();
		
		boolean found = false;
		
		while(!found) {
			if(map.containsKey(new Integer(temp))) {
				temp++;
			} else {
				boolean x = false;
				for(int i =0; i<nums.length; i++) {
					if(temp==nums[i]) {
						map.put(new Integer(temp), Boolean.TRUE);
						x = true;
						break;
					}
				}
				if(x==false) {
					found=true;
				}
			}
		}

		return temp;
	}

}

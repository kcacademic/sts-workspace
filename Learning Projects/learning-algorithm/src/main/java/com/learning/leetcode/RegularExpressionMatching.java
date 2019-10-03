package com.learning.leetcode;

public class RegularExpressionMatching {

	public static void main(String[] args) {

		System.out.println(new RegularExpressionMatching().isMatch("aab", "c*a*b"));

	}

	public boolean isMatch(String s, String p) {

		char[] target = s.toCharArray();
		char[] pattern = p.toCharArray();

		int current = 0;

		boolean match = false;

		for (int i = 0; i < pattern.length; i++) {
			System.out.println("i: " + i + " current: " + current);
			if (current > target.length - 1) {
				match = false;
				break;
			}
			if (i == pattern.length - 1 && current < target.length - 1) {
				match = false;
				break;
			}
			if (i < pattern.length - 1 && pattern[i + 1] == '*') {
				if (pattern[i] == '.') {
					if (i == pattern.length - 2) {
						match = true;
						break;
					} else {
						while(current < target.length && pattern[i+2] == target[current])
							current++;
					}
				} else {
					while (current < target.length && pattern[i] == target[current]) {
						current++;
						match = true;
					}
					if (i < pattern.length - 2) {
						if(pattern[i+2] == target[current-1])
							current--;
					}
				}
				i++;
			} else {
				if (pattern[i] == '.') {
					current++;
				} else if (pattern[i] != target[current]) {
					break;
				} else {
					match = true;
					current++;
				}
			}

		}

		return match;
	}

}

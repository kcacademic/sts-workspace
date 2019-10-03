package com.learning.leetcode;

public class MedianTwoSortedArrays {

	public static void main(String[] args) {

		// System.out.println(new MedianTwoSortedArrays().findMedianSortedArrays(new
		// int[] {}, new int[] { 1, 2, 3, 4, 5 }));

		System.out.println(new MedianTwoSortedArrays().medianTwoSortedArrays(new int[] {}, new int[] { 2, 3 }));

	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		int[] merged = mergeTwoSortedArrays(nums1, nums2);

		double median = 0;

		if (merged.length % 2 == 0) {
			median = (merged[(merged.length / 2) - 1] + merged[(merged.length / 2)]) / 2.0;
		} else {
			median = merged[merged.length / 2];
		}

		return median;
	}

	public double medianTwoSortedArrays(int[] nums1, int[] nums2) {

		int cutoff = 0;
		if ((nums1.length + nums2.length) % 2 == 0)
			cutoff = ((nums1.length + nums2.length) / 2) + 1;
		else
			cutoff = (nums1.length + nums2.length) / 2;

		int[] merged = new int[nums1.length + nums2.length];

		int a = 0;
		int b = 0;
		int c = 0;

		while (a < nums1.length || b < nums2.length) {
			if (a == nums1.length) {
				for (int x = b; x < nums2.length; x++) {
					merged[c] = nums2[x];
					c++;
				}
				b = nums2.length;
			} else if (b == nums2.length) {
				for (int x = a; x < nums1.length; x++) {
					merged[c] = nums1[x];
					c++;
				}
				a = nums1.length;
			} else if (nums1[a] <= nums2[b]) {
				merged[c] = nums1[a];
				a++;
				c++;
			} else {
				merged[c] = nums2[b];
				b++;
				c++;
			}

			if (c > cutoff)
				break;
		}

		double median = 0;
		if ((nums1.length + nums2.length) % 2 == 0)
			median = (merged[cutoff - 2] + merged[cutoff - 1]) / 2.0;
		else
			median = merged[cutoff];

		return median;
	}

	public int[] mergeTwoSortedArrays(int[] nums1, int[] nums2) {

		int[] merged = new int[nums1.length + nums2.length];

		int a = 0;
		int b = 0;
		int c = 0;

		while (a < nums1.length || b < nums2.length) {
			if (a == nums1.length) {
				for (int x = b; x < nums2.length; x++) {
					merged[c] = nums2[x];
					c++;
				}
				b = nums2.length;
			} else if (b == nums2.length) {
				for (int x = a; x < nums1.length; x++) {
					merged[c] = nums1[x];
					c++;
				}
				a = nums1.length;
			} else if (nums1[a] <= nums2[b]) {
				merged[c] = nums1[a];
				a++;
				c++;
			} else {
				merged[c] = nums2[b];
				b++;
				c++;
			}
		}

		return merged;
	}

}

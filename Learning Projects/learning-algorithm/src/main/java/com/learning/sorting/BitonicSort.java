package com.learning.sorting;

/*
	 Bitonic sort is one of the fastest sorting networks. 
	 A sorting network is a special kind of sorting algorithm, where the 
	 sequence of comparisons is not data-dependent. This makes sorting 
	 networks suitable for implementation in hardware or in parallel processor 
	 arrays. 
	 This sorts increasingly larger intermingled subsets, somewhat like 
	 Shell sort, and merges subsets, like merge sort.
	 
	 Bitonic sort consists of O(n·log(n)2) comparisons in O(log(n)2) stages. 
	 
	 So here's how we do a bitonic sort:
	 # We sort only sequences a power of two in length, so we can always divide 
	 	 a subsequence of mnore than one element into two halves. 
	 # We sort the lower half into ascending (=non-decreasing, remember) order 
	 	 and the upper half into descending order. This gives us a bitonic 
	 	 sequence. 
	 # We perform a bitonic merge on the sequence, which gives us a bitonic 
	 	 sequence in each half and all the larger elements in the upper half. 
	 # We recursively bitonically merge each half until all the elements are 
	 	 sorted. 
*/

public class BitonicSort {

	private final static boolean ASCENDING = true, DESCENDING = false;

	public int[] sort(int[] input) {

		// The processing below takes care of array sizes not equal to 2pow(k)
		int n = input.length;
		int k = 1;
		int r = 0;
		while (k < n)
			k = k * 2;
		r = k - n;

		int[] temp = new int[n + r];
		for (int i = r; i < n + r; i++)
			temp[i] = input[i - r];
		bitonicSort(temp, 0, n + r, ASCENDING);
		for (int i = 0; i < n; i++)
			input[i] = temp[i + r];

		return input;
	}

	private void compare(int[] input, int i, int j, boolean dir) {
		if (dir == (input[i] > input[j])) {
			int h = input[i];
			input[i] = input[j];
			input[j] = h;
		}
	}

	private void bitonicMerge(int[] input, int lo, int n, boolean dir) {
		if (n > 1) {
			int k = n / 2;
			for (int i = lo; i < lo + k; i++)
				compare(input, i, i + k, dir);
			bitonicMerge(input, lo, k, dir);
			bitonicMerge(input, lo + k, k, dir);
		}
	}

	private void bitonicSort(int[] input, int lo, int n, boolean dir) {
		if (n > 1) {
			int k = n / 2;
			bitonicSort(input, lo, k, ASCENDING);
			bitonicSort(input, lo + k, k, DESCENDING);
			bitonicMerge(input, lo, n, dir);
		}
	}

	public static void main(String[] args) {

		BitonicSort bis = new BitonicSort();
		System.out.print("The Sorted Array: ");
		int[] sorted = bis.sort(new int[] { 5, 24, 67, 12, 4, 5, 16 });
		for (int i = 0; i < sorted.length; i++)
			System.out.print(sorted[i] + " ");
	}
}
package com.learning.sorting;

/*	 	
 	The selection sort works by selecting the smallest 
  	unsorted item remaining in the list, 
  	and then swapping it with the item in the next position to be filled.
  		
  	The selection sort has a complexity of O(n2). 
  	
  	Pros: Simple and easy to implement.
  	Cons: Inefficient for large lists, so similar to the more efficient 
  		insertion sort that the insertion sort should be used in its place.
*/

public class SelectionSort {

	public int[] sort1(int[] input) {

		int n = input.length;
		int temp;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (input[i] > input[j]) {
					// ... Exchange elements
					temp = input[i];
					input[i] = input[j];
					input[j] = temp;
				}
			}
		}

		return input;

	}

	public int[] sort2(int[] input) {

		int n = input.length;
		for (int i = 0; i < n; i++) {
			int minIndex = i; // Index of smallest remaining value.
			for (int j = i + 1; j < n; j++) {
				if (input[minIndex] > input[j]) {
					minIndex = j; // Remember index of new minimum
				}
			}
			if (minIndex != i) {
				// ... Exchange current element with smallest remaining.
				int temp = input[i];
				input[i] = input[minIndex];
				input[minIndex] = temp;
			}
		}

		return input;

	}

	public static void main(String[] args) {

		SelectionSort ss = new SelectionSort();

		System.out.print("The Sorted Array by Impl1: ");
		int[] sorted1 = ss.sort1(new int[] { 5, 24, 67, 12, 4, 5, 16 });
		for (int i = 0; i < sorted1.length; i++)
			System.out.print(sorted1[i] + " ");
		System.out.println("");

		System.out.print("The Sorted Array by Impl2: ");
		int[] sorted2 = ss.sort2(new int[] { 5, 24, 67, 12, 4, 5, 16 });
		for (int i = 0; i < sorted2.length; i++)
			System.out.print(sorted2[i] + " ");
	}
}
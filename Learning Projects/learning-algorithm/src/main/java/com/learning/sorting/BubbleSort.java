package com.learning.sorting;

/*	
 	The bubble sort works by comparing each item in the list with 
  	the item next to it, and swapping them if required. 
  	The algorithm repeats this process until it makes a pass 
  	all the way through the list without swapping any items, 
  	in other words, all items are in the correct order. 
 	This causes larger values to "bubble" to the end of the list 
  	while smaller values "sink" towards the beginning of the list. 
	
	The bubble sort is generally considered to be the most inefficient 
	sorting algorithm in common usage.
	Under best-case conditions (the list is already sorted), 
	the bubble sort can approach a constant O(n) level of complexity. 
	General-case is an abysmal O(n2).
	While the insertion, selection, and shell sorts also have O(n2) complexities, 
	they are significantly more efficient than the bubble sort. 
	
	Pros: Simplicity and ease of implementation.
	Cons: Horribly inefficient. 
*/

public class BubbleSort {

	public int[] sort(int[] input) {

		int n = input.length;
		int temp;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1 - i; j++)
				if (input[j + 1] < input[j]) { /* compare the two neighbors */
					temp = input[j]; /* swap a[j] and a[j+1] */
					input[j] = input[j + 1];
					input[j + 1] = temp;
				}
		}
		return input;

	}

	public static void main(String[] args) {

		BubbleSort bs = new BubbleSort();
		System.out.print("The Sorted Array: ");
		int[] sorted = bs.sort(new int[] { 5, 24, 67, 12, 4, 5, 16 });
		for (int i = 0; i < sorted.length; i++)
			System.out.print(sorted[i] + " ");
	}
}
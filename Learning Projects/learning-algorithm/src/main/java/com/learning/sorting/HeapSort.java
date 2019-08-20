package com.learning.sorting;

/*	
	The heap sort is the slowest of the O(n log n) sorting algorithms, 
 	but unlike the merge and quick sorts it doesn't require massive recursion
 	or multiple arrays to work. This makes it the most attractive option for 
 	very large data sets of millions of items. 

	The heap sort works as it name suggests - 
	It begins by building a heap out of the data set, 
	and then removing the largest item and placing it at the end of the sorted array. 
	After removing the largest item, it reconstructs the heap and removes 
	the largest remaining item and places it in the next open position from 
	the end of the sorted array. This is repeated until there are no items left 
	in the heap and the sorted array is full. Elementary implementations require 
	two arrays - one to hold the heap and the other to hold the sorted elements. 
	
	Pros: In-place and non-recursive, making it a good choice for extremely large 
			data sets.
	Cons: Slower than the merge and quick sorts.
	
	Heap Property: Parent >= Children
	Parent(i) = [i/2]
	LeftChild(i) = (2*i)
	RightChild(i) = (2*i + 1)
*/

public class HeapSort {

	public int[] sort(int[] input) {

		int n = input.length;
		int i, temp;

		// Build heap from input array
		for (i = (n / 2) - 1; i >= 0; i--)
			heapify(input, i, n);

		// Take the largest out and heapify in loop
		for (i = n - 1; i >= 0; i--) {
			temp = input[0];
			input[0] = input[i];
			input[i] = temp;
			heapify(input, 0, i - 1);
		}

		return input;
	}

	void heapify(int numbers[], int root, int bottom) {
		int maxChild, temp;
		boolean done = false;

		while ((root * 2 <= bottom) && (!done)) {
			// Find the max of root's children
			if (root * 2 == bottom)
				maxChild = root * 2;
			else if (numbers[root * 2] > numbers[root * 2 + 1])
				maxChild = root * 2;
			else
				maxChild = root * 2 + 1;

			// If any of the children is greater than parent do swap
			if (numbers[root] < numbers[maxChild]) {
				temp = numbers[root];
				numbers[root] = numbers[maxChild];
				numbers[maxChild] = temp;
				root = maxChild;
			} else
				// If the root is greater than its children, stop the loop
				done = true;
		}
	}

	public static void main(String[] args) {

		HeapSort hs = new HeapSort();
		System.out.print("The Sorted Array: ");
		int[] sorted = hs.sort(new int[] { 5, 24, 67, 12, 4, 5, 16 });
		for (int i = 0; i < sorted.length; i++)
			System.out.print(sorted[i] + " ");
	}
}
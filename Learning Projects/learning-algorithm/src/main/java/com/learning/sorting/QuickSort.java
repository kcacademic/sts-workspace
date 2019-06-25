package com.learning.sorting;


/*	The quick sort is an in-place, divide-and-conquer, massively recursive sort. 
 	As a normal person would say, it's essentially a faster in-place version of 
 	the merge sort. The quick sort algorithm is simple in theory, but very 
 	difficult to put into code (computer scientists tied themselves into knots 
 	for years trying to write a practical implementation of the algorithm, and 
 	it still has that effect on university students). 

	The recursive algorithm consists of four steps (which closely resemble the 
	merge sort): 

	1.If there are one or less elements in the array to be sorted, return immediately. 
	2.Pick an element in the array to serve as a "pivot" point. (Usually the left-most 
	  element in the array is used.) 
	3.Split the array into two parts - one with elements larger than the pivot and the 
	  other with elements smaller than the pivot. 
	4.Recursively repeat the algorithm for both halves of the original array. 
	
	The efficiency of the algorithm is majorly impacted by which element is choosen 
	as the pivot point. The worst-case efficiency of the quick sort, O(n2), occurs 
	when the list is sorted and the left-most element is chosen. Randomly choosing 
	a pivot point rather than using the left-most element is recommended if the data 
	to be sorted isn't random. As long as the pivot point is chosen randomly, the 
	quick sort has an algorithmic complexity of O(n log n). 

	Pros: Extremely fast.
	Cons: Very complex algorithm, massively recursive. 

*/

public class QuickSort{
	
	public int[] sort(int[] input){
		
		int n = input.length;
		quicksort(input, 0, n - 1);
		return input;
	}
	
	void quicksort(int numbers[], int left, int right)
	{
	  int pivot, l_hold, r_hold;

	  l_hold = left;
	  r_hold = right;
	  pivot = numbers[left];
	  while (left < right)
	  {
	    while ((numbers[right] >= pivot) && (left < right))
	      right--;
	    if (left != right)
	    {
	      numbers[left] = numbers[right];
	      left++;
	    }
	    while ((numbers[left] <= pivot) && (left < right))
	      left++;
	    if (left != right)
	    {
	      numbers[right] = numbers[left];
	      right--;
	    }
	  }
	  numbers[left] = pivot;
	  pivot = left;
	  left = l_hold;
	  right = r_hold;
	  if (left < pivot)
		  quicksort(numbers, left, pivot-1);
	  if (right > pivot)
		  quicksort(numbers, pivot+1, right);
	}

	
	public static void main(String [] args){
		
		QuickSort qs = new QuickSort();
		System.out.print("The Sorted Array: ");
		int [] sorted = qs.sort(new int[]{5,24,67,12,4,5,16});
		for(int i=0;i<sorted.length;i++)
		System.out.print(sorted[i]+" ");
	}
}
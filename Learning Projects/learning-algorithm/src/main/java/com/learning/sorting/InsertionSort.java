package com.learning.sorting;


/*	The insertion sort works just like its name suggests,
   	it inserts each item into its proper place in the final list. 
   	The simplest implementation of this requires two list structures,
   	the source list and the list into which sorted items are inserted. 
   	To save memory, most implementations use an in-place sort that works 
   	by moving the current item past the already sorted items and repeatedly 
   	swapping it with the preceding item until it is in place.
 	
 	Like the bubble sort, the insertion sort has 
 	a complexity of O(n2). Although it has the same complexity, 
 	the insertion sort is a little over twice as efficient as the bubble sort.
 	
 	Pros: Relatively simple and easy to implement.
 	Cons: Inefficient for large lists. */

public class InsertionSort{
	
	public int[] sort1(int[] input){
		
		int n = input.length;
		int [] sorted = new int[input.length];
		int i, j;
	
		for (i=0; i < n; i++)
		  {
		    j = i;
		    while ((j > 0) && (sorted[j-1] > input[i]))
		    {
		      if(j!=0){
		    	  sorted[j] = sorted[j-1];
		    	  j = j - 1;
		      }
		    }
		    sorted[j] = input[i];
		  }
		return sorted;
	}
	
	public int[] sort2(int[] input){
		
		int n = input.length;
		int i, j, index;

		for (i=1; i < n; i++)
		  {
		    index = input[i];
		    j = i;
		    while ((j > 0) && (input[j-1] > index))
		    {
		      input[j] = input[j-1];
		      j = j - 1;
		    }
		    input[j] = index;	
		  }
		return input;
	}
	
	public static void main(String [] args){
		
		InsertionSort is = new InsertionSort();
		
		System.out.print("The Sorted Array by Impl1: ");
		int [] sorted1 = is.sort1(new int[]{5,24,67,12,4,5,16});
		for(int i=0;i<sorted1.length;i++)
		System.out.print(sorted1[i]+" ");
		System.out.println("");
		
		System.out.print("The Sorted Array by Impl2: ");
		int [] sorted2 = is.sort2(new int[]{5,24,67,12,4,5,16});
		for(int i=0;i<sorted2.length;i++)
		System.out.print(sorted2[i]+" ");
	}
}
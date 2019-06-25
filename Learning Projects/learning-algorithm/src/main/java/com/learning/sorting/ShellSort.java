package com.learning.sorting;


/*	Invented by Donald Shell in 1959, the shell sort is the most efficient 
  	of the O(n2) class of sorting algorithms. Of course, the shell sort is 
  	also the most complex of the O(n2) algorithms. 
 
  	The shell sort is a "diminishing increment sort", better known as a "comb sort". 
 	The algorithm makes multiple passes through the list, 
 	and each time sorts a number of equally sized sets using the insertion sort. 
 	The size of the set to be sorted gets larger with each pass through the list, 
 	until the set consists of the entire list. 
 	Note that as the size of the set increases, the number of sets to be sorted decreases. 
 	This sets the insertion sort up for an almost-best case run each iteration 
 	with a complexity that approaches O(n). 
 	
 	Pros: Efficient for medium-size lists.
 	Cons: Somewhat complex algorithm, not nearly as efficient as the 
 		  merge, heap, and quick sorts.*/

public class ShellSort{
	
	public int[] sort(int[] input){
		
		int n = input.length;
		int i, j, increment, temp;

		increment = 3;
		while (increment > 0)
		{
		  for (i=0; i < n; i++)
		  {
		    j = i;
		    temp = input[i];
		    while ((j >= increment) && (input[j-increment] > temp))
		    {
		      input[j] = input[j - increment];
		      j = j - increment;
		    }
		    input[j] = temp;
		  }
		  if (increment/2 != 0)
		      increment = increment/2;
		  else if (increment == 1)
		      increment = 0;
		  else
		      increment = 1;
		}
		return input;
	}
	
	public static void main(String [] args){
		
		ShellSort shs = new ShellSort();
		System.out.print("The Sorted Array: ");
		int [] sorted = shs.sort(new int[]{5,24,67,12,4,5,16});
		for(int i=0;i<sorted.length;i++)
		System.out.print(sorted[i]+" ");
	}
}
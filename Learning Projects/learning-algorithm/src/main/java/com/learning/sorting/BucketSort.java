package com.learning.sorting;

/*	 
	A bucket sort works as follows: An array of m counters, 
	or buckets , is used. Each of the counters is set initially to zero. 
	Then, a pass is made through the input array, during which the buckets 
	are used to keep a count of the number of occurrences of each value 
	between 0 and m-1. Finally, the sorted result is produced by first 
	placing the required number of zeroes in the array, then the required 
	number of ones, followed by the twos, and so on, up to m-1.
	 
	The running time of Program  is O(mn) from a cursory analysis.
	The running time of Program  is O(m+n)from a careful analysis.  
*/

public class BucketSort{
	
	public int[] sort(int[] input){
		
		int n = input.length;
		int m = 100;
		int bucket[] = new int[m];
		
	//	for(int a=0;a<m;++a)
	//		bucket[a] = 0;
		for(int b=0;b<n;++b)
			++bucket[input[b]];
		for(int c=0,d=0;d<m;++d)
			for(int e=bucket[d];e>0;--e)
				input[c++]=d;
		
		return input;
	}
	
	public static void main(String [] args){
		
		BucketSort bs = new BucketSort();
		System.out.print("The Sorted Array: ");
		int [] sorted = bs.sort(new int[]{5,24,67,12,4,5,16});
		for(int i=0;i<sorted.length;i++)
		System.out.print(sorted[i]+" ");
	}
}
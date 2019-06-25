package com.learning.sorting;

/*	 A multiple pass distribution sort algorithm that distributes each item 
	 to a bucket according to part of the item's key beginning with the least 
	 significant part of the key. After each pass, items are collected from 
	 the buckets, keeping the items in order, then redistributed according to 
	 the next most significant part of the key.
	 
	 A radix sort algorithm works as follows:

	 1.Take the least significant digit (or group of bits, both being examples 
		of radices) of each key. 
	 2.Sort the list of elements based on that digit, but keep the order of 
		elements with the same digit (this is the definition of a stable sort). 
	 3.Repeat the sort with each more significant digit. 
	
	 The sort in step 2 is usually done using bucket sort or counting sort, 
	 which are efficient in this case since there are usually only a small 
	 number of digits.


*/

public class RadixSort{
	
	public int[] sort(int[] input){
		
		int n = input.length;
		int r = 8;
		int R = 1 << r;
		int p = (32 + r + 1)/r;
		int count[] = new int[R];
		int temp[] = new int[n];
		
	
		for(int a=0;a<p;++a)
		{
			for(int b=0;b<R;++b)
				count[b]=0;
			for(int c=0;c<n;++c)
			{
				++count[(input[c]>>>(r*a))&(R-1)];
				temp[c] = input[c];
			}
			
			int pos = 0;
			for(int d=0;d<R;++d)
			{
				int t = pos;
				pos += count[d];
				count[d] = t;
			}
			for(int e=0;e<n;++e)
			{
				int j = (temp[e]>>>(r*a))&(R-1);
				input[count[j]++] = temp[e];
			}
		}
		
		return input;
	}
	
	public static void main(String [] args){
		
		RadixSort rs = new RadixSort();
		System.out.print("The Sorted Array: ");
		int [] sorted = rs.sort(new int[]{5,24,67,12,4,5,16});
		for(int i=0;i<sorted.length;i++)
		System.out.print(sorted[i]+" ");
	}
}
package com.learning.sorting;

public class Sort{
	
	public static void main(String args[]){
		
		int unsorted[] = new int[]{5,23,1,56,78,94,23,34,52,12,31,89,70,43};
		
		BubbleSort bs = new BubbleSort();
		System.out.print("Bubble Sort\t:");
		int [] sorted1 = bs.sort(replicate(unsorted));
		for(int i=0;i<sorted1.length;i++)
		System.out.print(sorted1[i]+" ");
		System.out.println("");

		InsertionSort is = new InsertionSort();
		System.out.print("Insertion Sort\t:");
		int [] sorted2 = is.sort2(replicate(unsorted));
		for(int i=0;i<sorted2.length;i++)
		System.out.print(sorted2[i]+" ");
		System.out.println("");
		
		SelectionSort ss = new SelectionSort();
		System.out.print("Selection Sort\t:");
		int [] sorted3 = ss.sort2(replicate(unsorted));
		for(int i=0;i<sorted3.length;i++)
		System.out.print(sorted3[i]+" ");
		System.out.println("");
		
		ShellSort shs = new ShellSort();
		System.out.print("Shell Sort\t:");
		int [] sorted4 = shs.sort(replicate(unsorted));
		for(int i=0;i<sorted4.length;i++)
		System.out.print(sorted4[i]+" ");
		System.out.println("");
		
		HeapSort hs = new HeapSort();
		System.out.print("Heap Sort\t:");
		int [] sorted5 = hs.sort(replicate(unsorted));
		for(int i=0;i<sorted5.length;i++)
		System.out.print(sorted5[i]+" ");
		System.out.println("");
		
		MergeSort ms = new MergeSort();
		System.out.print("Merge Sort\t:");
		int [] sorted6 = ms.sort(replicate(unsorted));
		for(int i=0;i<sorted6.length;i++)
		System.out.print(sorted6[i]+" ");
		System.out.println("");
		
		QuickSort qs = new QuickSort();
		System.out.print("Quick Sort\t:");
		int [] sorted7 = qs.sort(replicate(unsorted));
		for(int i=0;i<sorted7.length;i++)
		System.out.print(sorted7[i]+" ");
		System.out.println("");
		
		BucketSort bus = new BucketSort();
		System.out.print("Bucket Sort\t:");
		int [] sorted8 = bus.sort(replicate(unsorted));
		for(int i=0;i<sorted8.length;i++)
		System.out.print(sorted8[i]+" ");
		System.out.println("");
		
		RadixSort rs = new RadixSort();
		System.out.print("Radix Sort\t:");
		int [] sorted9 = rs.sort(replicate(unsorted));
		for(int i=0;i<sorted9.length;i++)
		System.out.print(sorted9[i]+" ");
		System.out.println("");
		
		BitonicSort bis = new BitonicSort();
		System.out.print("Bitonic Sort\t:");
		int [] sorted10 = bis.sort(replicate(unsorted));
		for(int i=0;i<sorted10.length;i++)
		System.out.print(sorted10[i]+" ");
		System.out.println("");
	}
	
	private static int[] replicate(int[] unsorted){
		int[] temp = new int[unsorted.length];
		for(int i=0;i<unsorted.length;i++)
			temp[i]=unsorted[i];
		return temp;
	}
}
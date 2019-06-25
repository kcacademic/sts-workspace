package com.learning.algorithm;

public class Palindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		String test = "rfabbarghggtgduklhjjhhfddf,khjjhjgjk";
		
		char[] array = test.toCharArray();
		
		for(int i=0; i<array.length-1; i++){
			
			for(int j=array.length-1; j>i+1; j--)
			{
				if(isPalindrome(array, i, j))
				{
					for(int k=i; k<=j; k++)
						System.out.print(array[k]);
					System.out.println();
				}
			}

		}
		

	}
	
	private static boolean isPalindrome(char[] array, int start, int end){
		
		int length = end-start;
		
		for(int i=0; i<=length/2; i++){
			if(array[start+i]!=array[end-i])
				return false;
		}
		
		return true;
		
	}

}

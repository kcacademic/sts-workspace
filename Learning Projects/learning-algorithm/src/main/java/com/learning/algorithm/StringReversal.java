package com.learning.algorithm;

public class StringReversal {
	
	public static void main(String[] args) {

		reverse2(reverse1(reverse3("the world is beautiful".toCharArray())));
		
	}

	private static char[] reverse1(char[] array){
		
		System.out.println(array);
		int strlength = array.length;
		char[] newarray = new char[strlength];
		
		int flag1 = 0;
		int flag2 = 0;
		int flag3 = 0;
		int flag4 = 0;
		boolean notlast = false;
		
		for(int i=0; i<strlength; i++){
			
			notlast = array[i]==' '|array[i]==','|array[i]=='.';
			
			if(array[i]==' '|array[i]==','|array[i]=='.'|i==strlength-1){
				flag2=flag4=i;
				for(int j=flag1; j<flag2; j++){					
					if(notlast){	
						flag4--;
						newarray[flag3]=array[flag4];						
					}else{					
						newarray[flag3]=array[flag4];	
						flag4--;
					}
					flag3++;
				}
				if(i==strlength-1){
					newarray[flag3]=array[flag4];
				}
				
				flag1 = i;
				flag1++;
				if(array[i]==' '|array[i]==','|array[i]=='.'){
					newarray[flag3]=array[i];
					flag3++;
				}
			}
		}
		
		System.out.println(newarray);	
		return newarray;
		
	}

	private static char[] reverse2(char[] array){
			
		System.out.println(array);
		
		int strlength = array.length;	
		char[] newarray = new char[strlength+1];
		
		int flag1 = strlength -1;
		int flag2 = 0;
		
		for(int i=0; i<strlength; i++){
			
			if(array[strlength-i-1] == ' '){
				for(int j=(strlength-i); j<=flag1; j++){
					newarray[flag2] = array[j];
					flag2++;
				}
				if(flag1==(strlength-1)){
					newarray[flag2] = ' ';
					flag2++;
				}
				
				flag1 = strlength-i-1;
			}
			
			if((strlength-i-1)==0){
				for(int j=(strlength-i-1); j<=flag1; j++){
					newarray[flag2] = array[j];
					flag2++;
				}
			}
			
		}
		
		System.out.println(newarray);		
		return newarray;
		
	}
	
	private static char[] reverse3(char[] array){
		
		System.out.println(array);
		
		int strlength = array.length;	
		
		char[] newarray = new char[strlength];


		for(int i=0; i<strlength; i++){
			
			newarray[strlength-1-i] = array[i];
		}
		
		System.out.println(newarray);
		return newarray;
		
	}
}

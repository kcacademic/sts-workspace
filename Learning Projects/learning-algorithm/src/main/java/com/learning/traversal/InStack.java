package com.learning.traversal;

public class InStack{

	public static char[] postfix(char [] form){
		
		char [] operator = new char[form.length];
		char [] numeral = new char[form.length];
		
		int  flag1=0 , flag2=0;
		for(int i=0;i<form.length;i++){
			switch(form[i]){
				case '+':{
					operator[flag1]=form[i];
					flag1++;break;}
				case '-':{
					operator[flag1]=form[i];
					flag1++;break;}
				case '*':{
					operator[flag1]=form[i];
					flag1++;break;}
				case '/':{
					operator[flag1]=form[i];
					flag1++;break;}
				default:{
					numeral[flag2]=form[i];
					flag2++;break;}
			}
		}
		for(int j=flag1-1;j>=0;j--){
			numeral[flag2]=operator[j];
			flag2++;
		}
		
		return numeral;
		
	}
	
	public static char[] prefix(char [] form){
		
		char [] operator = new char[form.length];
		char [] numeral = new char[form.length];
		
		int  flag1=0 , flag2=0;
		for(int i=0;i<form.length;i++){
			switch(form[i]){
				case '+':{
					operator[flag1]=form[i];
					flag1++;break;}
				case '-':{
					operator[flag1]=form[i];
					flag1++;break;}
				case '*':{
					operator[flag1]=form[i];
					flag1++;break;}
				case '/':{
					operator[flag1]=form[i];
					flag1++;break;}
				default:{
					numeral[flag2]=form[i];
					flag2++;break;}
			}
		}
		for(int j=flag2-1;j>=0;j--){
			operator[flag1]=numeral[j];
			flag1++;
		}
		
		return operator;
		
	}
	
	
	public static void main(String [] args){
		
		System.out.print("The Infix  : ");
	//	char [] form = new char[]{'3','-','4','+','5','*','6','-','8','/','2'};
		char [] form = {'a','+','b','*','c','-','d'};
		for(int i=0;i<form.length;i++)
			System.out.print(form[i]);
		System.out.println("");
		
		char [] post = postfix(form);
		System.out.print("The Postfix: ");
		for(int i=0;i<post.length;i++)
			System.out.print(post[i]);
		System.out.println("");
		
		char [] pre = prefix(form);
		System.out.print("The Prefix : ");
		for(int i=0;i<pre.length;i++)
			System.out.print(pre[i]);
	}

}
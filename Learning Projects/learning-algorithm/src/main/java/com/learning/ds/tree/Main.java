package com.learning.ds.tree;

public class Main {

	public static void main(String[] args) {
		
		BinaryTree tree = new BinarySearchTree();
		
		tree.create(new int[]{ 5,4,3,2,1,6,7,8,9 });
		
		System.out.println(tree.depth());
		
		tree.print();

	}

}

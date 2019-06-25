package com.learning.datastructure;

public class BinaryTreeTraversal {

	private class Node {
		
		Node(int i){
			setData(i);
		}
		
		private Node left;
		private Node right;
		private int data;	
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
	}
	
	private Node create(int[] array){
		
		Node root=null;
		for(int i=0;i<=array.length-1;i++){
			Node temp = new Node(array[i]);
			if(i==0)
				root=temp;
			else
				insert(temp, root);	
		}
		
		return root;
	}

	private void insert(Node temp, Node root) {
		
		if(temp.getData()>root.getData()){
			if(root.getRight()!=null)
				insert(temp,root.getRight());
			else{
				root.setRight(temp);
			}
		}
		if(temp.getData()<=root.getData()){
			if(root.getLeft()!=null)
				insert(temp,root.getLeft());
			else{
				root.setLeft(temp);
			}
		}
	}
	
	private int count(Node root){
		if(root == null)
			return 0;
		else{
			return 1 + count(root.getLeft())+count(root.getRight());
		}
	}
	
	private void printPreOrder(Node root){
		if(root != null){
			System.out.print(root.getData()+" ");	
			printPreOrder(root.getRight());		
			printPreOrder(root.getLeft());
		}	
	}
	
	private void printPostOrder(Node root){
		if(root != null){				
			printPostOrder(root.getRight());		
			printPostOrder(root.getLeft());
			System.out.print(root.getData()+" ");
		}	
	}
	
	private void printInOrder(Node root){
		if(root != null){			
			printInOrder(root.getRight());	
			System.out.print(root.getData()+" ");
			printInOrder(root.getLeft());
		}	
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("CREATION");
		BinaryTreeTraversal tree = new BinaryTreeTraversal();
		Node root = tree.create(new int[]{14,4,12,16,7});
		System.out.print("The Tree in Pre Order:");tree.printPreOrder(root);System.out.println("");
		System.out.print("The Tree in Post Order:");tree.printPostOrder(root);System.out.println("");
		System.out.print("The Tree in In Order:");tree.printInOrder(root);System.out.println("");
		System.out.println("Initial Root :"+root.getData());
		System.out.println("Whats the count: "+tree.count(root));
		System.out.println("");

	}

}

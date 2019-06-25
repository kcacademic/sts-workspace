package com.learning.datastructure;

public class BinaryTreeToLinkedList {

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
	
	private void printTree(Node root){
		if(root != null){
			printTree(root.getRight());
			System.out.print(root.getData()+" ");	
			printTree(root.getLeft());
		}	
	}
	
	private void printList(Node root){
		if(root != null){
			printList(root.getLeft());
			System.out.print(root.getData()+" ");	
		}	
	}
	
	private Node treeToList(Node root){
				
		return createList(null, root, null);
		
	}
	
	private Node createList(Node parent, Node tree, Node list){
		
		if(tree == null){
			return list;
		}
		list = createList(tree, tree.getLeft(), list);
		list = createList(tree, tree.getRight(), list);
		
		tree.setLeft(list);
		list = tree;
		
		if(parent!=null){
			if(parent.getLeft()!=null && parent.getLeft().getData()==tree.getData())
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
		
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("CREATION");
		BinaryTreeToLinkedList tree = new BinaryTreeToLinkedList();
		Node root = tree.create(new int[]{14,4,12,16,7});
		System.out.print("The Tree :");tree.printTree(root);System.out.println("");
		System.out.println("Initial Root :"+root.getData());
		System.out.println("Whats the count: "+tree.count(root));
		System.out.println("");
		
		System.out.println("TREE TO LIST");
		root = tree.treeToList(root);
		System.out.print("The List :");tree.printList(root);System.out.println("");
	}

}

package com.learning.ds.tree;

public class BinarySearchTree extends BinaryTree {

	@Override
	public boolean insert(int data) {
		insert(data, root);
		return false;
	}
	
	private void insert(int data, Node node) {
		if(node == null)
			node = new Node(data);
		else {
			if(data <= node.getData()) {
				if(node.getLeft() != null)
					insert(data, node.getLeft());
				else
					node.setLeft(new Node(data));
			}
			else {
				if(node.getRight() != null)
					insert(data, node.getRight());
				else
					node.setRight(new Node(data));
			}
		}	
	}
	
	@Override
	public boolean delete(int data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean search(int data) {
		// TODO Auto-generated method stub
		return false;
	}
}

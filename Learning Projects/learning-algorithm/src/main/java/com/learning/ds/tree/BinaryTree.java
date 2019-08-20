package com.learning.ds.tree;

import java.util.Arrays;

public abstract class BinaryTree {

	Node root;

	class Node {

		Node(int i) {
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

	public boolean create(int[] array) {
		for (int i = 0; i <= array.length - 1; i++) {
			if (i == 0)
				root = new Node(array[i]);
			else
				insert(array[i]);
		}

		return true;
	}

	abstract boolean insert(int data);

	abstract boolean search(int data);

	abstract boolean delete(int data);

	public int count() {
		return count(root);
	}

	private int count(Node root) {
		if (root == null)
			return 0;
		else {
			return 1 + count(root.getLeft()) + count(root.getRight());
		}
	}

	public int depth() {
		return depth(root);
	}

	private int depth(Node node) {
		if (node == null)
			return 0;
		else {
			int lDepth = depth(node.left);
			int rDepth = depth(node.right);
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	public void print() {
		print(new Node[] { root }, depth(root));
	}

	private void print(Node[] nodes, int depth) {
		Node[] nextLevel = new Node[nodes.length * 2];
		System.out.print(String.format("%1$" + (depth * 2) + "s", ""));
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				if (depth > 1)
					System.out.print(nodes[i].getData() + String.format("%1$" + (depth - 1) + "s", ""));
				else
					System.out.print(nodes[i].getData() + String.format("%1$" + (depth) + "s", ""));
				nextLevel[i * 2 + 0] = nodes[i].getLeft();
				nextLevel[i * 2 + 1] = nodes[i].getRight();
			} else {
				System.out.print(String.format("%1$" + (depth) + "s", ""));
			}
		}
		System.out.println();
		long nextLevelCount = Arrays.stream(nextLevel).filter(value -> value != null).count();
		if (nextLevelCount > 0)
			print(nextLevel, depth - 1);
	}

}

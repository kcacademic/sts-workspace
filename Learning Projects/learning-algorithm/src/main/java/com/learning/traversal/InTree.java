package com.learning.traversal;

public class InTree {

	static class Node {
		char c;
		Node right;
		Node left;

		public char getC() {
			return c;
		}

		public void setC(char c) {
			this.c = c;
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

	public static int operator(char[] form, int i, int j) {
		for (int k = i; k <= j; k++) {
			if (form[k] == '-')
				return k;
		}
		for (int k = i; k <= j; k++) {
			if (form[k] == '+')
				return k;
		}
		for (int k = i; k <= j; k++) {
			if (form[k] == '*')
				return k;
		}
		for (int k = i; k <= j; k++) {
			if (form[k] == '/')
				return k;
		}
		return i;
	}

	public static void Treefy(char[] form, Node node, int i, int j) {
		int x = operator(form, i, j);
		if (x == i)
			node.setC(form[x]);
		else {
			node.setC(form[x]);
			Node left = new Node();
			node.setLeft(left);
			Treefy(form, left, i, x - 1);
			Node right = new Node();
			node.setRight(right);
			Treefy(form, right, x + 1, j);
		}
	}

	public static void printin(Node root) {
		if (root == null)
			return;
		else {
			printin(root.getLeft());
			System.out.print(root.getC());
			printin(root.getRight());
		}
	}

	public static void printpre(Node root) {
		if (root == null)
			return;
		else {
			System.out.print(root.getC());
			printpre(root.getLeft());
			printpre(root.getRight());
		}
	}

	public static void printpost(Node root) {
		if (root == null)
			return;
		else {
			printpost(root.getLeft());
			printpost(root.getRight());
			System.out.print(root.getC());
		}
	}

	public static void main(String[] args) {
		Node root = new Node();
		// char [] form = {'3','-','4','+','5','*','6','-','8','/','2'};
		char[] form = { 'a', '+', 'b', '*', 'c', '-', 'd' };
		Treefy(form, root, 0, form.length - 1);
		System.out.print("The Infix  : ");
		printin(root);
		System.out.println("");
		System.out.print("The Postfix: ");
		printpost(root);
		System.out.println("");
		System.out.print("The Prefix : ");
		printpre(root);
		System.out.println("");
	}
}
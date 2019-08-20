package com.learning.datastructure;

public class BinarySearchTree {

	private class Node {

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

	private Node create(int[] array) {

		Node root = null;
		for (int i = 0; i <= array.length - 1; i++) {
			Node temp = new Node(array[i]);
			if (i == 0)
				root = temp;
			else
				insert(temp, root);
		}

		return root;
	}

	private void insert(Node temp, Node root) {

		if (temp.getData() > root.getData()) {
			if (root.getRight() != null)
				insert(temp, root.getRight());
			else {
				root.setRight(temp);
			}
		}
		if (temp.getData() <= root.getData()) {
			if (root.getLeft() != null)
				insert(temp, root.getLeft());
			else {
				root.setLeft(temp);
			}
		}
	}

	private boolean search(int data, Node root) {

		if (root == null)
			return false;
		if (data == root.getData())
			return true;
		if (data > root.getData())
			return search(data, root.getRight());
		else
			return search(data, root.getLeft());

	}

	private Node delete(int data, Node root) {
		Node temp = delete(data, root, null, 'N');
		if (temp == null)
			return root;
		else
			return temp;
	}

	private Node delete(int data, Node root, Node prev, char dir) {

		if (root == null) {
			return null;
		}
		if (data == root.getData()) {
			Node temp = root.getRight();
			if (temp != null) {
				while (temp.getLeft() != null) {
					temp = temp.getLeft();
				}
				temp.setLeft(root.getLeft());
				if (prev != null) {
					if (dir == 'R')
						prev.setRight(root.getRight());
					else
						prev.setLeft(root.getRight());
					return null;
				} else {
					return root.getRight();
				}
			} else {
				root.setRight(root.getLeft());
				if (prev != null) {
					if (dir == 'R')
						prev.setRight(root.getRight());
					else
						prev.setLeft(root.getRight());
				} else {
					return root.getRight();
				}
			}

			return null;
		}
		if (data > root.getData() && root.getRight() != null) {
			return delete(data, root.getRight(), root, 'R');
		}
		if (data <= root.getData() && root.getLeft() != null) {
			return delete(data, root.getLeft(), root, 'L');
		}
		return null;
	}

	private int count(Node root) {
		if (root == null)
			return 0;
		else {
			return 1 + count(root.getLeft()) + count(root.getRight());
		}
	}

	private void print(Node root) {
		if (root != null) {
			print(root.getRight());
			System.out.print(root.getData() + " ");
			print(root.getLeft());
		}
	}

	public static void main(String[] args) {

		System.out.println("CREATION");
		BinarySearchTree tree = new BinarySearchTree();
		Node root = tree.create(new int[] { 14, 4, 12, 16, 7 });
		System.out.print("The Tree :");
		tree.print(root);
		System.out.println("");
		System.out.println("Initial Root :" + root.getData());
		System.out.println("Whats the count: " + tree.count(root));
		System.out.println("");

		System.out.println("INSERTION");
		System.out.println("Is 2 there : " + tree.search(2, root));
		BinarySearchTree.Node node = new BinarySearchTree().new Node(2);
		tree.insert(node, root);
		System.out.print("After adding 2, ");
		System.out.print("The Tree :");
		tree.print(root);
		System.out.println("");
		System.out.println("Is 2 there : " + tree.search(2, root));
		System.out.println("Whats the count: " + tree.count(root));
		System.out.println("");

		System.out.println("DELETION");
		System.out.print("The Tree :");
		tree.print(root);
		System.out.println("");
		System.out.println("Deleting 4: ");
		root = tree.delete(4, root);
		System.out.print("The Tree :");
		tree.print(root);
		System.out.println("");
		System.out.println("Whats the count: " + tree.count(root));
		System.out.println("Is 12 there : " + tree.search(12, root));
		System.out.println("Deleting 12: ");
		root = tree.delete(12, root);
		System.out.println("Deleting 88: ");
		root = tree.delete(88, root);
		System.out.println("Is 12 there: " + tree.search(12, root));
		System.out.print("The Tree :");
		tree.print(root);
		System.out.println("");
		System.out.println("Whats the count: " + tree.count(root));
		System.out.println("Deleting Root: ");
		root = tree.delete(14, root);
		System.out.print("The Tree :");
		tree.print(root);
		System.out.println("");
		System.out.println("Root :" + root.getData());
		System.out.println("Whats the count: " + tree.count(root));
		System.out.println("");
	}

}
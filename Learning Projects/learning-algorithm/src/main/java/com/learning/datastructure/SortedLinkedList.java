package com.learning.datastructure;

public class SortedLinkedList {

	private class Node {

		Node(int i) {
			setData(i);
		}

		private Node next;
		private int data;

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	private Node create(int[] array) {

		Node root = null;
		for (int i = 0; i <= array.length - 1; i++) {
			Node temp = new Node(array[i]);
			root = insert(temp, root);
		}
		return root;
	}

	private Node insert(Node temp, Node root) {

		Node flag = root;
		Node prev = null;
		if (flag == null) {
			return temp;
		}
		if (temp.getData() >= root.getData()) {
			temp.setNext(root);
			return temp;
		} else {
			while (flag != null && temp.getData() < flag.getData()) {
				prev = flag;
				flag = flag.getNext();
			}
			temp.setNext(flag);
			prev.setNext(temp);
			return root;
		}
	}

	private Node delete(int Data, Node root) {

		Node flag = root;
		Node prev = null;
		if (root == null)
			return null;
		if (root.getData() == Data)
			return root.getNext();
		else {
			while (flag != null && Data != flag.getData()) {
				prev = flag;
				flag = flag.getNext();
			}
			if (flag != null)
				prev.setNext(flag.getNext());
			return root;
		}
	}

	private void print(Node root) {
		if (root != null) {
			System.out.print(root.getData() + " ");
			print(root.getNext());
		}
	}

	private Node reverse(Node root) {
		return this.reverseImp(root, null);
	}

	private Node reverseImp(Node root, Node prev) {
		Node temp = null;
		if (root != null) {

			temp = root;
			root = root.getNext();
			temp.setNext(prev);
			prev = temp;

			temp = reverseImp(root, prev);
			if (temp != null)
				root = temp;
		}
		return root;
	}

	public static void main(String[] args) {

		System.out.println("CREATION");
		SortedLinkedList list = new SortedLinkedList();
		Node root = list.create(new int[] { 14, 4, 12, 16, 7 });
		System.out.print("The List :");
		list.print(root);
		System.out.println("");
		System.out.println("");

		System.out.println("INSERTION");
		SortedLinkedList.Node node = new SortedLinkedList().new Node(10);
		root = list.insert(node, root);
		System.out.print("The List :");
		list.print(root);
		System.out.println("");
		System.out.println("");

		System.out.println("DELETION");
		root = list.delete(99, root);
		root = list.delete(4, root);
		root = list.delete(16, root);
		System.out.print("The List :");
		list.print(root);
		System.out.println("");
		System.out.println("");

		System.out.println("REVERSAL");
		root = list.reverse(root);
		System.out.print("The List :");
		list.print(root);
		System.out.println("");
		System.out.println("");

	}

}
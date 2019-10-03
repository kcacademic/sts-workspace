package com.learning.leetcode;

public class MergeSortedLinkedList {

	public static void main(String[] args) {

		MergeSortedLinkedList routines = new MergeSortedLinkedList();

		ListNode first = routines.new ListNode(1);
		first.next = routines.new ListNode(4);
		first.next.next = routines.new ListNode(5);
		ListNode second = routines.new ListNode(1);
		second.next = routines.new ListNode(3);
		second.next.next = routines.new ListNode(4);
		ListNode third = routines.new ListNode(2);
		third.next = routines.new ListNode(6);

		ListNode[] lists = new ListNode[] { first, second, third};

		printList(routines.mergeKLists(lists));
		
	}

	public ListNode mergeKLists(ListNode[] lists) {

		ListNode output = null;
		
		for (int i = 0; i < lists.length; i++) {
			output = merge2Lists(output, lists[i]);
			printList(output);
		}
		
		
		return output;

	}

	public ListNode merge2Lists(ListNode a, ListNode b) {

		ListNode root = null;
		ListNode current = null;
		ListNode a_temp = a;
		ListNode b_temp = b;

		while (a_temp != null || b_temp != null) {
			if (a_temp == null) {
				if (current == null) 
					root = current = b_temp;
				else
					current.next = b_temp;
				
				b_temp = null;
			} else if (b_temp == null) {
				if (current == null)
					root = current = a_temp;
				else
					current.next = a_temp;
				a_temp = null;
			} else {
				if (root == null) {
					if (a_temp.val <= b_temp.val) {
						root = current = a_temp;
						a_temp = a_temp.next;
					} else {
						root = current = b_temp;
						b_temp = b_temp.next;
					}
				} else {
					if (a_temp.val <= b_temp.val) {
						current.next = a_temp;
						current = a_temp;
						a_temp = a_temp.next;
					} else {
						current.next = b_temp;
						current = b_temp;
						b_temp = b_temp.next;
					}
				}
			}

		}

		return root;
	}

	public static void printList(ListNode list) {

		ListNode next = list;
		while (next != null) {
			System.out.print(next.val);
			next = next.next;
		}
		System.out.println();
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}

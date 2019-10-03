package com.learning.leetcode;

public class AddTwoNumbers {

	public static void main(String[] args) {

		AddTwoNumbers routines = new AddTwoNumbers();

		ListNode first = routines.new ListNode(1);
		//first.next = routines.new ListNode(4);
		//first.next.next = routines.new ListNode(3);
		ListNode second = routines.new ListNode(9);
		second.next = routines.new ListNode(9);
		//second.next.next = routines.new ListNode(4);

		ListNode output = routines.addTwoNumbers(first, second);
		ListNode next = output;
		while (next != null) {
			System.out.print(next.val);
			next = next.next;
		}

	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode root = null;
		ListNode current = null;
		ListNode first = l1;
		ListNode second = l2;
		int carry = 0;
		while (first != null || second != null || carry!=0) {
			int firstVal = first!=null?first.val:0;
			int secondVal = second!=null?second.val:0;
			ListNode node = new ListNode((firstVal + secondVal + carry) % 10);
			carry = (firstVal + secondVal + carry) / 10;
			if (root == null) {
				root = current = node;
			} else {
				current.next = node;
				current = current.next;
			}
			first = first!=null?first.next:null;
			second = second!=null?second.next:null;
		}
		
		return root;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
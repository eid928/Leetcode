package LinkedList;

import datastructure.ListNode;

public class ReorderList {

	public static void main(String[] args) {
		/**
		 * 給定一個linkedList將它reorder
		 * 例如1,2,3,4,5 => 1,5,2,4,3
		 */
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		reorderList(head);
		
		head.print();
	}

	public static void reorderList(ListNode head) {
        /**
         * 新的order其實是將list後半reverse後
         * 前半後半交替merge的結果
         * 例如1,2,3,4,5，後半reverse後變為1,2,3,5,4
         * 前半1,2,3，後半5,4，交替merge
         * 1,5,2,4,3即為答案
         * 要找到中間的點=>採用快慢指針
         */
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		
		ListNode firstHalf = head;
		ListNode secondHalf = slow.next;
		slow.next = null;
		secondHalf = reverseList(secondHalf);
		
		firstHalf.print();
		secondHalf.print();
		
		while (firstHalf != null && secondHalf != null) {
			ListNode nextTemp = firstHalf.next;
			firstHalf.next = secondHalf;
			firstHalf = nextTemp;
			nextTemp = secondHalf.next;
			secondHalf.next = firstHalf;
			secondHalf = nextTemp;
		}
    }
	
	private static ListNode reverseList(ListNode head) {
		
		ListNode pre = null;
		ListNode curr = head;
		
		while (curr != null) {
			
			ListNode nextTemp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = nextTemp;
		}
		
        return pre;
    }
}

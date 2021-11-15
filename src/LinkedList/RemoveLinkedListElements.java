package LinkedList;

import datastructure.ListNode;

public class RemoveLinkedListElements {

	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(6);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next.next = new ListNode(6);
		
		head.print();
		
		removeElements(head, 6);
		head.print();
	}

    public static ListNode removeElements(ListNode head, int val) {
    	
    	ListNode curNode = head;
    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
    	ListNode prev = dummy;
    	
    	while (curNode != null) {
    		
    		if (curNode.val == val) {
				prev.next = curNode.next;
			} else {
				prev = prev.next;
			}
    		
    		curNode = curNode.next;
    	}
    	
        return dummy.next;
    }
}

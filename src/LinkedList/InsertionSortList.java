package LinkedList;
import datastructure.ListNode;

public class InsertionSortList {

	public static void main(String[] args) {
		
		ListNode head = new ListNode(-1);
		head.next = new ListNode(5);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(0);
		
		insertionSortList(head);
		head.print();

	}
    public static ListNode insertionSortList(ListNode head) {
    	
    	ListNode dummy = new ListNode(-1);
    	/* 因為插入的點為前一個node，為了插入最小的node，弄一個dummy當作最小node的插入點。之後回傳dummy.next */
    	/**
    	 * head - 原先的List
    	 * dummy - 排好的List
    	 */
    	
    	while (head != null) {
    		/* 遍歷list，head為目前要插入的node */
    		
    		ListNode next = head.next; /* 先儲存next，因為後續交換的時候head.next會變成temp */
    		
    		ListNode curr = dummy;
    		/* 再度遍歷list，curr為目前要插入的位置 */
    		while (curr.next != null && curr.next.val <= head.val) {
    			curr = curr.next;
    		}
    		
    		ListNode temp = curr.next;
    		curr.next = head;
    		head.next = temp;
    		
    		head = next;
    	}
    	
        return dummy.next;
    }
}

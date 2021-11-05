package LinkedList;

import datastructure.ListNode;

public class OddEvenLinkedList {

	public static void main(String[] args) {
		/**
		 * 給定一個linkedlist
		 * 將奇數位置節點通通擺前面，偶數位置節點通通擺後面
		 * 空間複雜度限定O(1)
		 */
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		oddEvenList(head).print();
	}

    public static ListNode oddEvenList(ListNode head) {
        
    	if (head == null || head.next == null) {
			return head;
		}
    	/**
    	 * 因為偶數部分需要綁在後面
    	 * 以一個evenHead來指向偶數部分的頭
    	 * 再以雙指針去遍歷list
    	 */
    	ListNode odd = head;
    	ListNode even = head.next;
    	ListNode evenHead = even;
    	
    	while (even != null && even.next != null) {
    		
    		odd.next = even.next;
    		even.next = even.next.next;
    		
    		odd = odd.next;
    		even = even.next;
    	}
    	odd.next = evenHead;
    	
    	return head;
    }
}

package LinkedList;

import datastructure.ListNode;

public class RotateList {

	public static void main(String[] args) {
		/**
		 * 給定一linkedlist以及一個整數k
		 * 請將list向右rotate k次
		 */
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		rotateRight(head, 2).print();
		
		head = new ListNode(1);
		rotateRight(head, 1).print();;
	}

    public static ListNode rotateRight(ListNode head, int k) {
        
    	if (head == null || k == 0) {
			return head;
		}
    	
    	ListNode currNode = head;
    	int length = 0;
    	
    	while (currNode != null) { /* 先計算長度 */
    		currNode = currNode.next;
    		length ++;
    	}
    	
    	int degradeK = k % length; /* k可以degrade為k % length */
    	int firstHalf = length - degradeK;
    	currNode = head;
    	
    	for (int i = 0; i < firstHalf-1; i++) { /* 找到第一段的尾巴 */
    		currNode = currNode.next;
    	}
    	
    	ListNode secondHalf = currNode.next; /* 找到第二段的頭 */
    	currNode.next = null; /* 第一段把尾巴切斷 */
    	
    	currNode = secondHalf;
    	while (currNode != null && currNode.next != null) { /* 找到第二段的尾巴 */
    		currNode = currNode.next;
    	}
    	
    	if (currNode != null) {
    		currNode.next = head; /* 第二段接上第一段的頭 */
    		return secondHalf;
    	} else {
			return head;
		}
    }
}

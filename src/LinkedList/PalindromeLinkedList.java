package LinkedList;

import datastructure.ListNode;

public class PalindromeLinkedList {

	public static void main(String[] args) {
		/**
		 * 有一個linkedList
		 * 請判斷是否為回文？
		 * 條件為時空複雜度=O(N),O(1)
		 */
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(1);
		
		head.print();

		System.out.println( isPalindrome(head));
	}

    public static boolean isPalindrome(ListNode head) {
    	/**
    	 * 若不考慮空間複雜度，則遍歷時把值存進stack就完事了
    	 * 若要達成空間複雜度為常數
    	 * 則用快慢指針走，讓慢指針跑到後半段第一個位置
    	 * 之後將後半段翻轉
    	 * 最後一個指針從頭，一個指針從slow開始比對
    	 */
    	ListNode slow = head;
    	ListNode fast = head;
    	
    	while (fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	
    	if (fast != null) { 
    		/* 長度為奇數，slow將落在中位數，為了後續的翻轉，把指針往後移 */
			slow = slow.next;
		}
    	/* 長度為偶數的話，slow會自然落在後半段的第一個 */
    	
    	ListNode reverseSecondHalf = reverseList(slow);
    	
    	ListNode firstHalf = head;
    	ListNode secondHalf = reverseSecondHalf;
    	
    	while (secondHalf != null) {
    		
    		if (firstHalf.val != secondHalf.val) {
				return false;
			}
    		firstHalf = firstHalf.next;
    		secondHalf = secondHalf.next;
    	}
        
    	return true;
    }
    
    public static ListNode reverseList(ListNode head) {
		
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

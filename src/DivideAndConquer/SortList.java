package DivideAndConquer;

import datastructure.ListNode;

public class SortList {

	public static void main(String[] args) {
		
		ListNode head = new ListNode(-1);
		head.next = new ListNode(5);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(0);
		
		head.print();
		
		ListNode sortedList = sortList(head);
		sortedList.print();
	}

    public static ListNode sortList(ListNode head) {
        /**
         * mergeSort
         * 會用到MergeTwoSortedLists這題的方法，直接copy過來
         * 兩個size只有1的Node調用mergeTwoLists，則一定是排序好的
         * 
         * 而這個方法會持續拆分，直到參數的node只剩下自己，或是null
         */
    	/* step1: 拆分的終點：只剩下自己or自己為null */
    	if (head == null || head.next == null) {
			return head;
		}
    	
    	/* step2: 實際的拆分過程，使用快慢指針 */
    	ListNode slow = head;
    	ListNode fast = head;
    	ListNode pre = head;
    	while (fast != null && fast.next != null) {
    		pre = slow;
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	pre.next = null;
    	/**
    	 * 第一個list l1 = head~pre(pre要斷開)
    	 * 第二個list l2 = slow~最後
    	 */
    	
    	ListNode l1 = sortList(head);
    	ListNode l2 = sortList(slow);
    	
    	return mergeTwoLists(l1, l2);
    }
    
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	
    	ListNode dummy = new ListNode(-1);
    	ListNode curr = dummy;
    	
    	while (l1 != null || l2 != null) {
    		
    		if (l1 == null) {
				curr.next = l2;
				break;
			}
    		if (l2 == null) {
				curr.next = l1;
				break;
			}
    		
    		if (l1.val <= l2.val) {
				curr.next = l1;
				l1 = l1.next;
			} else {
				curr.next = l2;
				l2 = l2.next;
			}
    		
    		curr = curr.next;
    	}
    	
        return dummy.next;
    }
}

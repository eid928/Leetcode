package DivideAndConquer;
import java.util.PriorityQueue;

import datastructure.ListNode;

public class MergekSortedLists {

	public static void main(String[] args) {
		
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);
		
		ListNode l2 = new ListNode(1);
		l2.next = new ListNode(3);
		l2.next.next = new ListNode(4);
		
		ListNode l3 = new ListNode(2);
		l3.next = new ListNode(6);
		
		ListNode[] lists = {l1,l2,l3};
		
		//mergeKLists(lists).print();;
		
		mergeKLists2(lists).print();;
		
		ListNode l4 = new ListNode(-2);
		l4.next = new ListNode(-1);
		l4.next.next = new ListNode(-1);
		l4.next.next.next = new ListNode(-1);
		
		ListNode[] lists2 = {l4, null};
		
		mergeKLists2(lists2).print();
	}
    public static ListNode mergeKLists(ListNode[] lists) {
        /**
         * Divide and Conquer
         * 合併k個sorted list，直覺想法就是兩兩合併，但效率不好
         * 所以也是用merge sort的概念，將lists一直拆分，直到剩兩個sorted list再合併
         */
    	return divid(lists, 0 , lists.length-1);
    }
    
    private static ListNode divid(ListNode[] lists, int left, int right) {
		
    	if (left > right) {
			return null;
		}
    	if (left == right) { /* 拆分到只剩一個：回傳l1 or l2提供後續Merge */
			return lists[left];
		}
    	
    	int mid = (left + right) / 2;
    	
    	ListNode l1 = divid(lists, left, mid);
    	ListNode l2 = divid(lists, mid + 1, right);
    	
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
	
	public static ListNode mergeKLists2(ListNode[] lists) {
		/**
		 * 法二：minHeap
		 * 比法一稍慢但很直覺
		 */
		PriorityQueue<ListNode> minHeap = new PriorityQueue<>((node1, node2) -> {
			return node1.val - node2.val;
		});
		
		for (int i = 0; i < lists.length; i++) {
			ListNode head = lists[i];
			while (head != null) {
				minHeap.add(head);
				head = head.next;
			}
		}
		
		int size = minHeap.size();
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		
		for (int i = 0; i < size; i++) {
			ListNode currNode = minHeap.poll();
			currNode.next = null;
			// 切斷後面一次只加一個node以免有cycle
			curr.next = currNode;
			curr = curr.next;
		}
		
		return dummy.next;
	}
}

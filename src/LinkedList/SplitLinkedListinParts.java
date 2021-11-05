package LinkedList;

import datastructure.ListNode;

public class SplitLinkedListinParts {

	public static void main(String[] args) {
		/**
		 * 給定一linkedlist以及一整數k
		 * 將linkedlist平均拆成k等分後儲存在array中回傳
		 * 若無法剛好平均拆分，餘數的部分從前面的part開始分配
		 * 例如11個拆分為3份的話，就是4,4,3
		 */
		int[] nums = {1,2,3,4,5,6,7,8,9,10};
		
		ListNode head = new ListNode(1);
		ListNode curr = head;
		for (int i = 1; i < nums.length; i++) {
			curr.next = new ListNode(nums[i]);
			curr = curr.next;
		}
		
		ListNode[] splitListToParts = splitListToParts(head, 3);
		for (ListNode node : splitListToParts) {
			node.print();
		}
		
		int[] nums2 = {1,2,3};
		
		ListNode head2 = new ListNode(1);
		ListNode curr2 = head2;
		for (int i = 1; i < nums2.length; i++) {
			curr2.next = new ListNode(nums2[i]);
			curr2 = curr2.next;
		}
		
		ListNode[] splitListToParts2 = splitListToParts(head2, 5);
		for (ListNode node : splitListToParts2) {
			if (node != null) node.print();
			else System.out.println("null");
		}
	}

    public static ListNode[] splitListToParts(ListNode head, int k) {
        /**
         * 首先要知道每個拆分是多少個Node
         * 先遍歷list得知size
         * 計算出每個拆分最少是k/size
         * 而餘數為size % k
         * 前幾個拆分<餘數的皆可以在拿到一個Node
         */
    	ListNode[] result = new ListNode[k];
    	
    	ListNode curr = head;
    	int size = 0;
    	while (curr != null) {
    		size ++;
    		curr = curr.next;
    	}
    	
    	int avg = size / k;
    	int ext = size % k;
    	
    	curr = head;
    	for (int i = 0; i < k; i++) {
    		/* 先把curr存進array */
    		result[i] = curr;
    		int number = avg;
    		if (i < ext) number += 1;
    		/* 接著移動到該拆分的最後一個node */
    		while (number > 1) {
    			curr = curr.next;
    			number --;
    		}
    		if (curr == null) {
				continue;
			}
    		/* 斷尾，並將curr再往前一個node給下一個loop使用 */
    		ListNode tmp = curr.next;
    		curr.next = null;
    		curr = tmp;
    	}
    	
    	return result;
    }
}

package LinkedList;

import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;

public class LinkedListCycleII {

	public static void main(String[] args) {
		/**
		 * 給一個linkedlist，若list中的cycle
		 * 回傳cycle的起點(index最低的node)
		 * 否則回傳null
		 */
		ListNode head = new ListNode(3);
		head.next = new ListNode(2);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(-4);
		head.next.next.next.next = head.next;
		
		System.out.println(detectCycle2(head).val);
	}

    public static ListNode detectCycle(ListNode head) {
        /*
         * 法一：HashSet
         * hashset紀錄走訪的node
         * 一旦碰過曾經走訪的node，就回傳該node
         * 無則回傳null，時間O(N)，空間O(N)
         */
    	Set<ListNode> nodes = new HashSet<>();
    	
    	ListNode curr = head;
    	ListNode cycleStart = null;
    	
    	while (curr != null) {
    		if (nodes.contains(curr)) {
				cycleStart = curr;
				break;
			} else {
				nodes.add(curr);
				curr = curr.next;
			}
    	}
    	
    	return cycleStart;
    }
    
    public static ListNode detectCycle2(ListNode head) {
    	/**
    	 * 法二：快慢指針
    	 * 讓快慢指針去跑，fast一次走兩格，slow一次走一格
    	 * 若有cycle則fast一定能比slow多走一圈追上slow
    	 * --F--起點---a--相遇點
    	 *        \___c-a____/
    	 * 2D(slow) = D(fast)
    	 * 2(F + n'c + a) = F + nc + a
    	 * (n - 2n')c = F + a
    	 * kc = F + a
    	 * 也就是相遇點距離原點kc，為cycle長度的正整數倍
    	 * 相遇後再讓slow回到head
    	 * 從原點出發走F，會走到起點
    	 * 從相遇點出發走F，也就是kc + F，其實就等於走到起點再繞k圈
    	 * 所以也會走到起點
    	 * 這樣就能找到起點了
    	 * 時間O(N)，空間O(1)
    	 */
    	ListNode slow = head;
    	ListNode fast = head;
    	
    	while (fast != null && fast.next != null) {
    		
    		slow = slow.next;
    		fast = fast.next.next;
    		if (slow == fast) break;
    	}
    	
    	if (fast == null || fast.next == null) return null;
    	
    	slow = head;
    	
    	while (slow != fast) {
    		slow = slow.next;
    		fast = fast.next;
    	}
    	
    	return fast;
    }
}

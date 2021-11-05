package LinkedList;
import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;

public class LinkedListCycle {

	public static void main(String[] args) {
		
		ListNode head1 = new ListNode(3);
		head1.next = new ListNode(2);
		head1.next.next = new ListNode(0);
		head1.next.next.next = new ListNode(4);
		head1.next.next.next.next = head1.next;
		
		ListNode head2 = new ListNode(3);
		head2.next = new ListNode(2);
		head2.next.next = new ListNode(0);
		head2.next.next.next = new ListNode(4);

		
		System.out.println("is head1 cycle? " + hasCycle(head1));
		System.out.println("is head2 cycle? " + hasCycle(head2));
	}

	public static boolean hasCycle(ListNode head) {
        
		Set<ListNode> nodeSet = new HashSet<>();
		
		return traversalAndStoreNode(head, nodeSet);
    }
	
	public static boolean traversalAndStoreNode(ListNode node, Set<ListNode> nodeSet) {
		
		if (node == null) {
			return false;
		}
		if (!nodeSet.contains(node)) {
			nodeSet.add(node);
			return traversalAndStoreNode(node.next, nodeSet);
		} else {
			return true;
		}
	}
}

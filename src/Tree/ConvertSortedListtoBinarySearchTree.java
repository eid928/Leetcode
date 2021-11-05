package Tree;

import datastructure.ListNode;
import datastructure.TreeNode;

public class ConvertSortedListtoBinarySearchTree {

	public static void main(String[] args) {
		/**
		 * 將一個sorted linked list重構為平衡BST
		 */
		ListNode head = new ListNode(-10);
		head.next = new ListNode(-3);
		head.next.next = new ListNode(0);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(9);

		TreeNode bst = sortedListToBST(head);
		System.out.println(bst.getLevelOrderList());
	}

    public static TreeNode sortedListToBST(ListNode head) {
    	/**
    	 * slow,fast pointer
    	 */
    	if (head == null) {
			return null;
		}
    	
    	if (head.next == null) {
			return new TreeNode(head.val);
		}
    	
    	ListNode prev = null;
    	ListNode slow = head;
    	ListNode fast = head;
    	
    	while (fast.next != null && fast.next.next != null) {
    		prev = slow;
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	TreeNode node = new TreeNode(slow.val);
    	
    	/**
    	 * prev的功能是前半段的終點，要接上前半段前須先將終點後的node斷開
    	 */
    	if (prev == null) {
    		node.left = null;
		} else {
			prev.next = null;
	    	node.left = sortedListToBST(head);
		}
    	
    	node.right = sortedListToBST(slow.next);
    	
    	return node;
    }
}

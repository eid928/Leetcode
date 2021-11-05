package LinkedList;
import datastructure.ListNode;

public class ReverseLinkedList {

	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		
		head.print();
		ListNode reverseList = reverseList(head);
		reverseList.print();
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

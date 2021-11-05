package LinkedList;
import datastructure.ListNode;

public class AddTwoNumbers {

	public static void main(String[] args) {
		/**
		 * 加法，listnode從個位數開始
		 * 個-十-百-...
		 * 2-4-3
		 * 5-6-4
		 * 7-0-8
		 */
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		
		l1.print();
		l2.print();
		
		ListNode sum = addTwoNumbers(l1, l2);
		sum.print();
	}

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	
    	
        return addTwoNumbers(l1, l2, false);
    }
    
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2, boolean plusOne) {
    	
    	if (l1 == null || l2 == null) {
    		if (l1 != null) { /* 如果一邊結束了，當作0繼續處理以免有進位 */
				return addTwoNumbers(l1, new ListNode(0), plusOne);
			} else if (l2 != null){
				return addTwoNumbers(new ListNode(0), l2, plusOne);
			} else if (plusOne) { 
				/* 如果兩邊都結束了但還是有進位，回傳多出的一個位數1 */
				/* 例如9999 + 1 */
				return new ListNode(1);
			}
			return null;
		}
    	
    	int sum = l1.val + l2.val;
    	if (plusOne) {
			sum += 1;
		}
    	plusOne = sum >= 10;
    	
    	ListNode currentNode = new ListNode(sum % 10);
    	currentNode.next = addTwoNumbers(l1.next, l2.next, plusOne);
    	
        return currentNode;
    }
}

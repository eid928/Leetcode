package DataStructrueDesign;

import java.util.Stack;

public class MinStack {

	public static void main(String[] args) {
		/**
		 * 實作一個MinStack Class
		 * 同時有stack的功能，且能夠隨時以O(1)的時間找出stack中最小的值
		 */
		MinStack minStack = new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin()); // return -3
		minStack.pop();
		System.out.println(minStack.top());    // return 0
		System.out.println(minStack.getMin()); // return -2
	}
	
	private Stack<Integer> stack;
	private Stack<Integer> minStack;

	public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
    	/**
    	 * 每加入一個數字到stack的時候，都要同時加入該數字時的最小值在minstack中
    	 * 例如：
    	 * statck: -2 -> 0 -> -3
    	 * minStack: -2 -> -2 -> -3
    	 * 加入0的時候再加入一個-2，表示0之前的最小值為-2
    	 * 而pop的時候要一起pop掉，不然兩個stack會不對稱
    	 */
        stack.push(val);
        if (minStack.isEmpty()) {
        	minStack.push(val);
        	return;
		}
        int min = minStack.peek();
        if (val < min) {
			minStack.push(val);
		} else {
			minStack.push(min);
		}
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

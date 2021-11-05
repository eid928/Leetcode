package Tree;

import datastructure.TreeNode;

public class SumRoottoLeafNumbers {

	private static int sum = 0;
	
	public static void main(String[] args) {
		/**
		 * 給定一個tree，求每個到leaf的路徑的數字之和
		 * 例如到leaf5，路徑的數字就是4-9-5＝495
		 * 到leaf1，路徑的數字就是4-9-1＝491
		 * 把這些通通加起來
		 */
		
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(9);
		root.right = new TreeNode(0);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(1);
		
		System.out.println(sumNumbers(root));
	}
    public static int sumNumbers(TreeNode root) {
    	/* 用field來記錄總和，先初始化 */
    	sum = 0;
    	dfs(root, 0);
    	
        return sum;
    }
    
    public static void dfs(TreeNode node, int lastNum) {
    	/**
    	 * dfs直到每個leaf，lastNum是走到目前的node前的數字
    	 */
    	if (node == null) {
			return;
		}
    	
    	if (node.left == null && node.right == null) { /* 找到leaf了 */
    		int currNum = lastNum*10 + node.val;
    		sum += currNum;
    		return;
    	}
    	/* 將目前node的數字和lastNum做計算傳下去 */
    	int currNum = lastNum*10 + node.val;
    	
    	dfs(node.left, currNum);
    	dfs(node.right, currNum);
    }
}

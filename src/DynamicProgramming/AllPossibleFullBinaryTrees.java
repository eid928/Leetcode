package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructure.TreeNode;

public class AllPossibleFullBinaryTrees {

	public static void main(String[] args) {
		
		List<TreeNode> result = allPossibleFBT(7);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).getLevelOrderList());
		}
	}
	
	public static List<TreeNode> allPossibleFBT(int n) {
		/**
		 * dp: tabulation
		 * dp[i] 儲存i個節點下可組成的所有FBT
		 * base case: dp[1] = [單獨node]
		 * 從i=1開始遍歷，j查找可能的左節點組合
		 */
		@SuppressWarnings("unchecked")
		List<TreeNode>[] dp = new ArrayList[n+1];
		dp[1] = new ArrayList<TreeNode>();
		TreeNode root = new TreeNode(0);
		dp[1].add(root);
		
		for (int i = 2; i <= n; i++) {
			dp[i] = new ArrayList<TreeNode>();
			if (i % 2 == 0) continue;
			
			for (int j = 1; j < i; j+=2) {
				
				List<TreeNode> listForleft =  dp[j];
				List<TreeNode> listForRight = dp[i-1-j];
				root = new TreeNode(0);
				for (TreeNode leftSubTree : listForleft) {
					for (TreeNode rightSubTree : listForRight) {
						root = new TreeNode(0);
						root.left = leftSubTree;
						root.right = rightSubTree;
						dp[i].add(root);
					}
				}
			}
		}
		return dp[n];
	}
	
    public static List<TreeNode> allPossibleFBT2(int n) {
    	/**
    	 * dp: dfs + memo
    	 * memo儲存n個節點下可組成的所有FBT，若是n為偶數，則為empty list
    	 */
    	Map<Integer, List<TreeNode>> dp = new HashMap<Integer, List<TreeNode>>();
    	List<TreeNode> result = dfs(n, dp);
    	
    	return result;
    }

	private static List<TreeNode> dfs(int n, Map<Integer, List<TreeNode>> dp) {
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		if (dp.containsKey(n)) {
			return dp.get(n);
		}
		
		if (n == 1) {
			TreeNode root = new TreeNode(0);
			list.add(root);
			dp.put(1, list);
			return list;
		}
		
		if (n % 2 != 1) {
			return list;
		}
		
		for (int i = 1; i < n; i+=2) { /* 要組成FBT subtree，其節點數須為奇數 */
			
			List<TreeNode> listForleft = dfs(i, dp); /* 左邊子樹的所有可能 */
			List<TreeNode> listForRight = dfs(n-i-1, dp); /* 右邊子樹的所有可能 */
			
			for (TreeNode leftSubTree : listForleft) {
				for (TreeNode rightSubTree : listForRight) {
					TreeNode root = new TreeNode(0);
					root.left = leftSubTree;
					root.right = rightSubTree;
					list.add(root);
				}
			}
		}
		
		dp.put(n, list);
		return list;
	}
}

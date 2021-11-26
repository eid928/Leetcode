package DynamicProgramming;

import java.util.Arrays;

public class PartitionArrayforMaximumSum {

	public static void main(String[] args) {
		/**
		 * 給定一組array以及一正整數k
		 * 請將array任意分成若干組subarray
		 * 且每組subarray長度不得超過k
		 * 分組後，各組的數字將全部變成該組最大的數字
		 * 請求出所有分組方法中，可能的和最高是多少
		 */
		int[] arr = {1,15,7,9,2,5,10};
		System.out.println(maxSumAfterPartitioning(arr, 3)); // 84, [15,15,15,9,10,10,10]
		int[] arr2 = {1,4,1,5,7,3,6,1,9,9,3};
		System.out.println(maxSumAfterPartitioning(arr2, 4)); // 83
	}
	
	public static int maxSumAfterPartitioning(int[] arr, int k) {
		/**
		 * dp: tabulation
		 * dp[i]記錄了從0~i的答案
		 * 
		 * dp[i]就是從i往前面找k個來試分組，試玩後跟dp[i-k-1]相加
		 * 最大的即是dp[i]
		 */
		
		int[] dp = new int[arr.length];
		dp[0] = arr[0];
		
		for (int i = 1; i < arr.length; i++) {
			
			int index = i;
			int maxBeforeI = arr[i];
			
			for (int j = index; j >= 0 && j > index - k; j--) {
				
				maxBeforeI = Math.max(maxBeforeI, arr[j]);
				dp[i] = Math.max(dp[i], maxBeforeI*(index-j+1) + ((j-1) < 0 ? 0 : dp[j-1]));
			}
		}
		
		return dp[arr.length-1];
	}

    public static int maxSumAfterPartitioning2(int[] arr, int k) {
        /**
         * dp: dfs + memo
         * 從index=0開始分組，每一組最大為k個
         * 以樹狀圖來看的話
         * 每個節點都有k個分支，分別表示從該index開始的分組，長度為1~k
         * 以1,15,7,9,2,5,10，k=3為例，從1開始
         * 第一組就可能是[1], [15,15], [15,15,15]這三種可能
         * 再以這三個節點下去延伸
         */
    	int[] dp = new int[arr.length];
    	Arrays.fill(dp, -1);
    	int result = dfs(arr, 0, k, dp);
    	
    	return result;
    }

	private static int dfs(int[] arr, int index, int k, int[] dp) {
		
		if (index >= arr.length) {
			return 0;
		}
		
		if (dp[index] != -1) {
			return dp[index];
		}
		
		int result = 0;
		int maxSum = 0;
		int maxFromIndex = Integer.MIN_VALUE;
		
		for (int i = index; i < index+k && i < arr.length; i++) {
			
			int curNum = arr[i];
			maxFromIndex = Math.max(maxFromIndex, curNum);
			
			result = maxFromIndex * (i - index + 1) + dfs(arr, i+1, k, dp);
			maxSum = Math.max(maxSum, result);
		}
		
		dp[index] = maxSum;
		return maxSum;
	}
}

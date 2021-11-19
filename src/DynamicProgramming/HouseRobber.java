package DynamicProgramming;

import java.util.Arrays;

public class HouseRobber {

	public static void main(String[] args) {
		/**
		 * nums＝一排房子
		 * 沿著nums搶劫，nums中的數字代表可以搶劫的$$
		 * 但是不能同時搶相鄰的兩間房子
		 * 求最大可搶到的$$
		 */
		int[] nums1 = {1,2,3,1};
		int[] nums2 = {2,7,9,3,1};
		int[] nums3 = {2,0,9,8,1,3};
		
		System.out.println(rob(nums1));
		System.out.println(rob(nums2));
		System.out.println(rob(nums3));
		System.out.println(rob2(nums1));
		System.out.println(rob2(nums2));
		System.out.println(rob2(nums3));
		System.out.println(rob3(nums1));
		System.out.println(rob3(nums2));
		System.out.println(rob3(nums3));
	}
	
	public static int rob3(int[] nums) {
		/**
		 * dfs + memo
		 * 每到一間房子就只有兩種選擇
		 * 樹狀圖就是每個節點底下兩個分支
		 */
		int[] dp = new int[nums.length];
		Arrays.fill(dp, -1);
		return dfs(nums, 0, dp);
	}
	
    private static int dfs(int[] nums, int index, int[] dp) {
    	
    	if (index >= nums.length) {
			return 0;
		}
    	
    	if (dp[index] != -1) {
			return dp[index];
		}
		
    	int money = nums[index];
    	
    	int resultToRob = money + dfs(nums, index+2, dp);
    	int resultNotToRob = dfs(nums, index+1, dp);
    	
    	dp[index] = Math.max(resultToRob, resultNotToRob);
		return Math.max(resultToRob, resultNotToRob);
	}

	public static int simpleRob(int[] nums) {
    	/**
    	 * simple dp:
    	 * dp存的是nums那一格為最後一間可rob的最大錢
    	 * index往後時，還要再查看該index一格前的所有dp中的最大值
    	 * 所以時間複雜度是n2
    	 * 例如
    	 * nums＝{2,0,9,8,1,3};
    	 * dp={2,0,11,10,12,14}
    	 */
    	int[] dp = new int[nums.length];
    	int max = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		int currentMoney = nums[i];
    		int maxSumBefore = 0;
    		for (int j = 0; j < i - 1; j++) {
    			maxSumBefore = Math.max(maxSumBefore, dp[j]);
    		}
    		dp[i] = currentMoney + maxSumBefore;
    		max = Math.max(max, dp[i]);
    	}
    	
        return max;
    }
    
    public static int rob(int[] nums) {
    	/**
    	 * 改良simpleRob
    	 * 既然每次都還要去找dp[i-2]前的最大值
    	 * 不如直接記起來
    	 * dp[i]儲存的變成目前的最大值，而非搶劫i為最後一間的最大值
    	 * 當走到i的時候，可能搶i也可能不搶i，就看哪個大。moneyToRobI vs moneyNotToRobI
    	 */
    	int[] dp = new int[nums.length];
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		int currentMoney = nums[i];
    		int maxBefore2 = i-2>=0? dp[i-2] : 0;
    		int moneyToRobI = currentMoney + maxBefore2;
    		
    		int moneyNotToRobI = i-1>=0? dp[i-1] : 0;
    		
    		dp[i] = Math.max(moneyToRobI, moneyNotToRobI);
    	}
    	
        return dp[dp.length-1];
    }
    
    public static int rob2(int[] nums) {
    	/**
    	 * 直接儲存兩種狀態的dp版本
    	 * 稍微多花一些儲存空間，但是更加好懂
    	 */
    	int[][] dp = new int[nums.length][2];
    	/**
    	 * 0: 不搶，下一間房子可以是0也可以是1
    	 * 1: 搶，下一間房子只能是0
    	 */
    	
    	dp[0][0] = 0;
    	dp[0][1] = nums[0];
    	
    	for (int i = 1; i < nums.length; i++) {
    		
    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]); // 決定不搶：上一間可以是搶也可以是不搶
    		dp[i][1] = dp[i-1][0] + nums[i]; // 決定搶：上一間只能不搶
    	}
    	
    	return Math.max(dp[dp.length-1][0], dp[dp.length-1][1]);
    }
}

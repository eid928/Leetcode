package DynamicProgramming;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeleteandEarn {

	public static void main(String[] args) {
		/**
		 * 給定一個array
		 * 每刪除一個數字就可以得到該數字的得分，但也必須刪除該數字+-1的所有數字
		 * 例如刪除4得到4分，但也必須刪除所有3和5
		 * 求最多得幾分？
		 */
		int[] nums = {3,4,2};
		int[] nums2 = {2,2,3,3,3,4};
		int[] nums3 = {8,10,4,9,1,3,5,9,4,10};
		int[] nums4 = {1,2,3,15,16,17,18};
		
		System.out.println(deleteAndEarn(nums));
		System.out.println(deleteAndEarn(nums2));
		System.out.println(deleteAndEarn(nums3)); // 37
		System.out.println(deleteAndEarn(nums4));
	}
	public static int deleteAndEarn(int[] nums) {
		/**
		 * 數字不超過10000，所以簡單的做法就是直接dp
		 * 做法和邏輯和house robber類似，偷了4就不能偷3和5(相鄰)
		 * 先建立pointMap，紀錄i位置總共可以得到幾分
		 * 以及最小和最大的數字(重要，否則dp都要遍歷10000個index)
		 * 接著建立dp，紀錄0~最大的數字
		 * 接下來就和house robber一樣了
		 * 時間空間複雜度都是O(max-min)，因為dp要從最小遍歷到最大，這個差距可能會大於N
		 * 缺點是會佔用較多的空間，最小到最大中間所有的數字都會佔據一個位置，包括沒有的數字
		 */
		int[] pointMap = new int[10001];
		int smallestNum = Integer.MAX_VALUE;
		int largestNum = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int curNum = nums[i];
			pointMap[curNum] += curNum;
			smallestNum = Math.min(smallestNum, curNum);
			largestNum = Math.max(largestNum, curNum);
		}
		
		int[][] dp = new int[largestNum+1][2];
    	/**
    	 * 0: 不取，下一個位置可取可不取，0 or 1
    	 * 1: 取，下一個位置不可取，0
    	 */
    	dp[smallestNum][0] = 0;
    	dp[smallestNum][1] = pointMap[smallestNum];
		
    	for (int i = smallestNum+1; i < dp.length; i++) {
    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
    		dp[i][1] = dp[i-1][0] + pointMap[i];
    	}
		
    	return Math.max(dp[dp.length-1][0], dp[dp.length-1][1]);
	}

    public static int deleteAndEarn2(int[] nums) {
    	/**
    	 * 省空間的做法，也不算太慢
    	 * 時間複雜度是O(NlogN)
    	 * 空間複雜度是O(N)
    	 * 先排序後用linkedHashMap來當作pointMap，就不去紀錄沒有的數字了
    	 */
        Arrays.sort(nums);
    	Map<Integer, Integer> pointMap = new LinkedHashMap<>();
    	for (int i = 0; i < nums.length; i++) {
    		pointMap.put(nums[i], pointMap.getOrDefault(nums[i], 0)+nums[i]);
    	}
    	System.out.println(pointMap);
    	
    	int[][] dp = new int[pointMap.size()][2];
    	/**
    	 * 0: 不取，+1的數可取可不取
    	 * 1: 取，＋1的數不可取
    	 */
    	Iterator<Integer> iterator = pointMap.keySet().iterator();
    	int curNum = iterator.next();
    	dp[0][0] = 0;
    	dp[0][1] = pointMap.get(curNum);
    	
    	for (int i = 1; i < dp.length; i++) {
    		curNum = iterator.next();
    		dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
    		if (pointMap.containsKey(curNum-1)) {
    			dp[i][1] = dp[i-1][0] + pointMap.get(curNum);
			} else {
				dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]) + pointMap.get(curNum);
			}
    	}
    	
    	return Math.max(dp[dp.length-1][0], dp[dp.length-1][1]);
    }
}

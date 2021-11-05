package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class MinimumCostForTickets {

	public static void main(String[] args) {
		/**
		 * 你正要規劃一個一年份的旅行
		 * days是你要出遊的日子，costs三個數代表1-day, 7-day, 30-day pass的價格
		 * 請計算你最少要買多少價格的票價，才可以讓每個出遊的日子都有票
		 */
		int[] days = {1,4,6,7,8,20};
		int[] costs = {2,7,15};
		int[] days2 = {1,2,3,4,5,6,7,8,9,10,30,31};
		int[] costs2 = {2,7,15};
		int[] days3 = {1,4,6,7,8,20};
		int[] costs3 = {7,2,15};
		
		System.out.println(mincostTickets(days, costs)); // 11
		System.out.println(mincostTickets(days2, costs2)); // 17
		System.out.println(mincostTickets(days3, costs3)); // 6
	}

    public static int mincostTickets(int[] days, int[] costs) {
    	/**
    	 * dp
    	 * dp[i]代表到了第i天，總共會花得最少票價
    	 * 而第i天不一定會出遊，所以做一個set來紀錄哪些天要出遊
    	 * 
    	 * 若是不出遊則dp[i] = dp[i-1]
    	 * 若是要出遊，則轉移方程式為：
    	 * dp[i] = dp[i-1]+cost[0], dp[i-7]+cost[1], dp[i-30]+cost[2] 中選一個小的
    	 */
    	Set<Integer> daysToGoOut = new HashSet<>();
    	for (int i = 0; i < days.length; i++) {
    		daysToGoOut.add(days[i]);
    	}
    	
    	int[] dp = new int[days[days.length-1]+1];
    	dp[days[0]] = Math.min(costs[0], Math.min(costs[1], costs[2]));
    	/* 初始化第一天，要注意的是，1-day pass不一定是最便宜的，媽的哪有這種低能票 */
    	
    	for (int i = days[0]+1; i < dp.length; i++) {
    		if (!daysToGoOut.contains(i)) { /* 不出遊 */
				dp[i] = dp[i-1];
				continue;
			}
    		int buy1DayPass = dp[i-1]+costs[0];
    		int buy7DayPass = i-7>=0 ? dp[i-7]+costs[1] : costs[1];
    		int buy30DayPass = i-30>=0 ? dp[i-30]+costs[2] : costs[2];
    		
    		dp[i] = Math.min(buy1DayPass, Math.min(buy7DayPass, buy30DayPass));
    	}
    	
    	for (int i = 0; i < dp.length; i++) {
    		System.out.print(dp[i]+",");
    	}
    	
        return dp[dp.length-1];
    }
}

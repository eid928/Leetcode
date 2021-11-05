package DynamicProgramming;

public class BestTimetoBuyandSellStockII {

	public static void main(String[] args) {
		/**
		 * 買賣股票
		 * 規則：
		 * 1.不限買賣次數，求最大獲利
		 * 2.一次只能持有一張
		 * 3.可再賣出的當天重新買進
		 */
		int[] prices = {7,1,5,3,6,4};
		System.out.println(maxProfit(prices));
	}

    public static int maxProfit(int[] prices) {
    	/**
    	 * 規則3是關鍵，反正賣出可以瞬間買進，所以一比昨天高就賣出，把獲利加進result中
    	 */
    	int result = 0;
    	
    	for (int i = 1; i < prices.length; i++) {
    		if (prices[i] > prices[i-1]) {
    			result += prices[i] - prices[i-1];
    		}
    	}
    	
        return result;
    }
}

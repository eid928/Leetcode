package DynamicProgramming;

import java.util.Arrays;

public class FillingBookcaseShelves {

	public static void main(String[] args) {
		/**
		 * 給定一組books array，裡面的pair代表的是寬度,高度
		 * 要依序將array中的書塞進寬度為width的書架中
		 * 請問書架最小需要多高？
		 */
		int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
		System.out.println(minHeightShelves(books, 4));  // 6
		int[][] books2 = {{1,3},{2,4},{3,2}};
		System.out.println(minHeightShelves(books2, 6)); // 4
		int[][] books3 = {{9,9},{5,4},{3,1},{1,5},{7,3}};
		System.out.println(minHeightShelves(books3, 10));
	}

    public static int minHeightShelves(int[][] books, int shelfWidth) {
        /**
         * dp: dfs + memo
         * dp[i][j]為起點是books[i]塞到最後一本，且起始該層書架寬度為j的答案
         * dp[0][shelfWidth]即為答案
         * 不用紀錄該層書架高，因為return給前一本書後，還會進行max或是+的計算
         * time: O(NM), N為books的長度，M為shelfWidth
         */
    	int[][] dp = new int[books.length][shelfWidth+1];
    	for (int i = 0; i < dp.length; i++) {
    		Arrays.fill(dp[i], -1);
    		
    	}
    	int result = dfs(books, 0, 0, shelfWidth, shelfWidth, dp);
    	
    	return result;
    }

	private static int dfs(int[][] books, int index, int curHeight, int remainWidth, int shelfWidth, int[][] dp) {
		/**
		 * 以樹狀圖來看
		 * 我要放一本書的時候，可以放在上一本書的旁邊
		 * 或是直接放在下一層，總共兩種選項
		 * 下一本書若放在旁邊的話，高度就會是目前高度和放完下一本後高度的max
		 * 下一本書若放在下層的話，高度就是目前高度直接加上去
		 */
		if (index >= books.length) {
			return 0;
		}
		
		if (dp[index][remainWidth] != -1) {
			return dp[index][remainWidth];
		}
		
		int curBookWidth = books[index][0];
		int curBookHeight = books[index][1];
		
		if (curBookWidth > remainWidth) {
			return Integer.MAX_VALUE;
		}
		
		curHeight = Math.max(curBookHeight, curHeight);
		
		int next = Math.max(curHeight, dfs(books, index+1, curHeight, remainWidth-curBookWidth, shelfWidth, dp));
		int down = curHeight + dfs(books, index+1, 0, shelfWidth, shelfWidth, dp);
		
		int minTotalHeight = Math.min(next, down);
		System.out.println("book:"+index+", cH:"+curBookHeight+", rW:"+remainWidth+", next:"+next+", down:"+down);
		dp[index][remainWidth] = minTotalHeight;
		return minTotalHeight;
	}
}

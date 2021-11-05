package DynamicProgramming;

public class UniquePathsII {

	public static void main(String[] args) {
		/**
		 * Just Like Unique Path
		 * 但在grid上有障礙物，1即代表該格無法進入
		 */
		
		int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
		System.out.println(uniquePathsWithObstacles(grid));
	}
	
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		/**
		 * 解題方法同Unique Path
		 * 創建一個dp grid來填格子
		 * 00的位置依據是否為障礙物初始化為1or0
		 * 若碰到障礙物，則該格子無條件為0
		 * 其餘的格子再根據上面or左邊的格子填入數字
		 */
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		
		int[][] dp = new int[m][n];
		dp[0][0] = obstacleGrid[0][0] == 0? 1:0;
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				
				if (i == 0 && j == 0) continue;
				if (obstacleGrid[i][j] == 1) {
					dp[i][j] = 0;
					continue;
				}
				if (i - 1 < 0) {
					dp[i][j] = dp[i][j-1];
					continue;
				}
				if (j - 1 < 0) {
					dp[i][j] = dp[i-1][j];
					continue;
				}
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(dp[i][j]+",");
			}
			System.out.println();
		}
		
        return dp[m-1][n-1];
    }
}

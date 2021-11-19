package DynamicProgramming;

import java.util.Arrays;

public class KnightProbabilityinChessboard {

	public static void main(String[] args) {
		
		System.out.println(knightProbability(3, 2, 0, 0)); // 0.06250
		System.out.println(knightProbability(1, 0, 0, 0)); // 1.00000
		System.out.println(knightProbability(8, 30, 6, 4)); // 0.00019
	}
	
	private static int[][] moves;
	static {
		moves = new int[][] {
			{1,2}, 
			{2,1}, 
			{-1,2}, 
			{-2,1},
			{1,-2},
			{2,-1},
			{-1,-2},
			{-2,-1}
		};
	}

	public static double knightProbability(int n, int k, int row, int column) {
		/**
		 * dp: tabulation
		 * dp[i][j], i儲存row, j儲存column
		 * dp[i][j]儲存在該次輪迴到達i,j的機率
		 * 總共k次輪迴
		 * 
		 * table:
		 * k=0      k=1        k=2
		 * 1 0 0    0 0 0      2/64 0 1/64
		 * 0 0 0 => 0 0 1/8 => 0    0 0
		 * 0 0 0    0 1/8 0    1/64 0 0
		 * 
		 * 要多準備一個double[][]來裝下一次的機率，否則會重複計算
		 */
		
		double[][] dp = new double[n][n];
		double[][] nextRunDp = new double[n][n];
		dp[row][column] = 1;
		
		for (int l = 1; l <= k; l++) {
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					
					if (dp[i][j] > 0) {
						
						for (int[] move : moves) {
							
							int nextRow = i + move[0];
							int nextColumn = j + move[1];
							if (nextRow >= 0 && nextRow < n && nextColumn >= 0 && nextColumn < n) {
								nextRunDp[i+move[0]][j+move[1]] += (dp[i][j] * 1/8);
							}
						}
					}
				}
			}
			dp = nextRunDp;
			nextRunDp = new double[n][n];
		}
		
		double totalProb = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				totalProb += dp[i][j];
			}
		}
		
		return totalProb;
	}
	
    public static double knightProbability2(int n, int k, int row, int column) {
        /**
         * dp: dfs + memo
         */
    	double[][][] dp = new double[k+1][n][n];
    	for (int i = 0; i < dp.length; i++) {
    		for (int j = 0; j < dp[i].length; j++) {
    			Arrays.fill(dp[i][j], -1);
    		}
    	}
    	double onBoardProb = dfs(n, k, row, column, dp);
    	
    	return onBoardProb;
    }

	private static double dfs(int n, int k, int row, int column, double[][][] dp) {
		/**
		 * 樹狀圖很單純，每個節點底下8個子節點，分別代表8個移動方式
		 * 樹的最大高度為k
		 * 當k用完，且還在board上，即回傳1，一旦跳出board即回傳0
		 * 每一節點都回傳機率，不能回傳成功次數，否則用總成功次數/總結果的話
		 * 總結果的量很有可能會超出double的上限
		 */
		if (row < 0 || column < 0 || row >= n || column >= n) {
			return 0;
		}
		
		if (k == 0) {
			return 1;
		}
		
		if (dp[k][row][column] != -1) {
			return dp[k][row][column];
		}
		
		if (dp[k][column][row] != -1) {
			return dp[k][column][row];
		}
		
		double totalProb = 0;
		
		for (int[] move : moves) {
			
			totalProb += dfs(n, k-1, row+move[0], column+move[1], dp);
		}
		
		totalProb /= 8;
		
		dp[k][row][column] = totalProb;
		dp[k][column][row] = totalProb;
		return totalProb;
	}
}

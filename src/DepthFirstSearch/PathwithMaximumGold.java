package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class PathwithMaximumGold {

	public static void main(String[] args) {
		/**
		 * 給定一組grid，上面的數字代表該位置的黃金數量
		 * 現在出發去獲取黃金
		 * 有以下規則
		 * 1. 可以從任何地方出發
		 * 2. 不可經過grid[i][j]=0的地方
		 * 3. 已經拿過黃金的地方不可重複造訪
		 * 請求出可以拿到的最多的黃金數量
		 */
		int[][] grid = {{0,6,0},{5,8,7},{0,9,0}};
		System.out.println(getMaximumGold(grid)); // 24
		int[][] grid2 = {{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
		System.out.println(getMaximumGold(grid2)); // 28
	}
	
	public static List<int[]> directions = new ArrayList<int[]>();
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}

    public static int getMaximumGold(int[][] grid) {
    	/**
    	 * 典型dfs，backstracking
    	 * 值為0的地方不可經過，就可以當作是visited
    	 */
    	int maxGold = 0;
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
    			int gold = dfs(grid, i, j);
    			maxGold = Math.max(maxGold, gold);
    		}
    	}
    	return maxGold;
    }

	private static int dfs(int[][] grid, int x, int y) {
		
		if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
			return 0;
		}
		
		if (grid[x][y] == 0) {
			return 0;
		}
		
		int maxGold = 0;
		int goldHere = grid[x][y];
		grid[x][y] = 0;
		
		for (int[] direction : directions) {
			
			int gold = goldHere + dfs(grid, x+direction[0], y+direction[1]);
			maxGold = Math.max(maxGold, gold);
		}
		
		grid[x][y] = goldHere;
		return maxGold;
	}
}

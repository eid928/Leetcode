package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class MaxAreaofIsland {

	public static void main(String[] args) {
		/**
		 * 給定一組grid
		 * 1為陸地-0為水
		 * 求出grid上最大的island面積是多少
		 */
		int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
		System.out.println(maxAreaOfIsland(grid)); // 6
	}

	private static List<int[]> directions = new ArrayList<int[]>();
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}
	
    public static int maxAreaOfIsland(int[][] grid) {
    	/**
    	 * 一樣dfs並track其面積
    	 */
    	int maxArea = 0;
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
    			if (grid[i][j] == 1) {
					int area = dfs(grid, i, j);
					maxArea = Math.max(maxArea, area);
				}
    		}
    	}
        return maxArea;
    }

	private static int dfs(int[][] grid, int x, int y) {
		
		if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
			return 0;
		}
		
		if (grid[x][y] == 0 || grid[x][y] == 2) {
			return 0;
		}
		
		grid[x][y] = 2;
		int area = 1;
		for (int[] direction : directions) {
			area += dfs(grid, x+direction[0], y+direction[1]);
		}
		
		return area;
	}
}

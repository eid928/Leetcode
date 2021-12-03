package DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberofDistinctIslands {

	public static void main(String[] args) {
		/**
		 * 給定一組grid
		 * 1是陸地，0是海
		 * 請求出grid中有多少“形狀獨特的島”
		 */
		int[][] grid = {
				{1,1,0,0,0},
			    {1,1,0,0,0},
			    {0,0,0,1,1},
			    {0,0,0,1,1}
		};
		System.out.println(numberofDistinctIslands(grid));
		int[][] grid2 = {
				{1,1,0,0,1},
			    {1,0,0,0,0},
			    {1,1,0,0,1},
			    {0,1,0,1,1}
		};
		System.out.println(numberofDistinctIslands(grid2));
	}
	
	private static List<int[]> directions = new ArrayList<int[]>();
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}

    public static int numberofDistinctIslands(int[][] grid) {
    	/**
    	 * dfs
    	 * 過程中track經過的相對座標(i*grid[0].length+j)
    	 * 並將經過的座標依序紀錄成一組字串
    	 * 這組字串一致的話，表示兩島形狀相同
    	 */
    	Set<String> islands = new HashSet<String>();
    	
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
    			if (grid[i][j] == 1) {
    				StringBuffer sb = new StringBuffer();
					dfs(grid, i, j, sb, i*grid[0].length+j);
					islands.add(sb.toString());
				}
    		}
    	}
    	System.out.println(islands);
        
    	return islands.size();
    }

	private static void dfs(int[][] grid, int x, int y, StringBuffer sb, int origin) {
		/**
		 * origin代表的是島最左上方的點，也就是原點的座標
		 * 每個點都要紀錄相對於原點的相對座標
		 */
		if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) {
			return;
		}
		if (grid[x][y] == 0 || grid[x][y] == 2) {
			return;
		}
		grid[x][y] = 2;
		sb.append((x*grid[0].length+y-origin)+">");
		for (int[] direction : directions) {
			
			dfs(grid, x+direction[0], y+direction[1], sb, origin);
		}
	}
}

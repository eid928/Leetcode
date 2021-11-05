package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestBridge {

	private static List<int[]> directions = new ArrayList<>();
	
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}
	
	public static void main(String[] args) {
		/**
		 * 給定一組矩陣，1為陸地，0為海洋
		 * 矩陣內含兩座由1組成的島嶼，求若要連接兩塊島嶼，最短的橋樑長度是多少？
		 */
		int[][] grid1 = {{0,1,0},{0,0,0},{0,0,1}};
		int[][] grid2 = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
		
		System.out.println(shortestBridge(grid1));
		System.out.println(shortestBridge(grid2));
	}

    public static int shortestBridge(int[][] grid) {
        /**
         * step1: 區分兩塊島嶼
         * 遍歷grid找到第一個1，對其DFS，並將其DFS到的格子標為2，並塞進queue中給後續BFS使用
         * 表示其為2號島嶼
         */
    	int m = grid.length;
    	int n = grid[0].length;
    	
    	boolean findSecondIsland = false;
    	Queue<int[]> queue = new LinkedList<>();
    	
    	for (int i = 0; i < m; i++) {
    		
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == 1) {
					dfs(i, j, grid, queue);
					findSecondIsland = true;
					break;
				}
    		}
    		if (findSecondIsland) break;
    	}
    	/**
    	 * step2: BFS
    	 * 將2號島一圈一圈向外擴張
    	 * 初始distance為0
    	 * 每向外擴張一圈distance++
    	 */
    	int distance = 0;
    	
    	while (!queue.isEmpty()) {
    		
    		int sizeInThisLayer = queue.size(); /* 先取得目前最外圈的size */
			for (int i = 0; i < sizeInThisLayer; i++) { /* 一次for迴圈就會消耗掉同一圈的 */
    			
    			int[] position = queue.poll();
        		int x = position[0];
        		int y = position[1];
        		
        		for (int[] dir : directions) {
        			
        			int neiborX = x + dir[0];
        			int neiborY = y + dir[1];
        			if (neiborX < 0 || neiborY < 0 || neiborX >= m || neiborY >= n) continue;
        			if (grid[neiborX][neiborY] == 2) continue;
        			if (grid[neiborX][neiborY] == 1) return distance;
        			grid[neiborX][neiborY] = 2; /* 向外擴張的格子設為2避免重複造訪 */
        			queue.add(new int[] {neiborX, neiborY}); /* 向外擴張的格子一樣加進queue中，會在下一次for迴圈被消耗 */
        		}
    		}
    		distance++;
    	}
    	
    	return -1;
    }

	private static void dfs(int i, int j, int[][] grid, Queue<int[]> queue) {
		
		if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
			return;
		}
		
		if (grid[i][j] == 2 || grid[i][j] == 0) {
			return;
		}
		
		grid[i][j] = 2;
		queue.add(new int[] {i,j});
		
		for (int[] direction : directions) {
			
			int x = i + direction[0];
			int y = j + direction[1];
			dfs(x, y, grid, queue);
		}
	}
}

package BreadthFirstSearch;
import java.util.LinkedList;
import java.util.Queue;

public class NumberofIslands {

	public static void main(String[] args) {
		char[][] map1 = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}
		};
		char[][] map2 = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','1','0','0'},
				{'0','0','0','1','1'}
		};

		System.out.println(numIslands(map1));
		System.out.println(numIslands(map2));
	}

	public static int numIslands(char[][] grid) {
		
		int totalNum = 0;
		
		for (int i = 0;i < grid.length; i++) {
			for (int j = 0;j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					totalNum ++;
					dfs(grid, i, j);
				}
			}
		}
		
        return totalNum;
    }
	
	public static int numIslandsBfs(char[][] grid) {
		
		int totalNum = 0;
		
		for (int i = 0;i < grid.length; i++) {
			for (int j = 0;j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					totalNum ++;
					Queue<int[]> queue = new LinkedList<>();
					queue.add(new int[] {i,j});
					bfs(grid, queue);
				}
			}
		}
		
        return totalNum;
    }

	private static void dfs(char[][] grid, int i, int j) {
		
		if (grid[i][j] == '0' || grid[i][j] == 'f') {
			return;
		}
		
		grid[i][j] = 'f';
		/* 把走訪過的點標成f */
		if (i-1 >= 0) {
			dfs(grid, i-1, j);
		}
		if (i+1 <= grid.length-1) {
			dfs(grid, i+1, j);
		}
		if (j-1 >= 0) {
			dfs(grid, i, j-1);
		}
		if (j+1 <= grid[i].length-1) {
			dfs(grid, i, j+1);
		}
	}
	
	private static void bfs(char[][] grid, Queue<int[]> queue) {
		/**
		 * 用queue來儲存將要走訪的地點順序
		 */
		while (!queue.isEmpty()) {
			int i = queue.peek()[0];
			int j = queue.peek()[1];
			grid[i][j] = 'f';
			
			if (i-1 >= 0 && grid[i-1][j] == '1') {
				queue.add(new int[] {i-1,j});
			}
			if (i+1 <= grid.length-1 && grid[i+1][j] == '1') {
				queue.add(new int[] {i+1,j});
			}
			if (j-1 >= 0 && grid[i][j-1] == '1') {
				queue.add(new int[] {i,j-1});
			}
			if (j+1 <= grid[i].length-1 && grid[i][j+1] == '1') {
				queue.add(new int[] {i,j+1});
			}
			
			queue.poll();
		}
	}
}

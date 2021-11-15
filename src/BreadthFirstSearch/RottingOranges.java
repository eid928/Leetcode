package BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

	public static void main(String[] args) {
		/**
		 * 給定一個grid
		 * 0: 空的格子
		 * 1: 新鮮的橘子
		 * 2: 壞掉的橘子
		 * 每過一分鐘，壞掉的橘子就會影響周圍，讓上下左右的新鮮橘子壞掉
		 * 請計算需要經過多久，grid中不再有新鮮的橘子
		 * 若是不可能讓所有新鮮橘子壞掉，則回傳-1
		 */
		int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
		int[][] grid2 = {{2,1,1},{0,1,1},{1,0,1}};
		int[][] grid3 = {{0,2}};
		
		System.out.println(orangesRotting(grid)); 
		System.out.println(orangesRotting(grid2)); 
		System.out.println(orangesRotting(grid3)); 
	}

    public static int orangesRotting(int[][] grid) {
    	/**
    	 * 典型的BFS
    	 * 要另外追蹤時間以及新鮮橘子的數量
    	 * 每當把一個grid[i][j]從1變為2，新鮮橘子--
    	 * 若是最後新鮮橘子還有剩，則回傳-1
    	 */
    	Queue<int[]> queue = new LinkedList<>();
    	int freshOrange = 0;
    	int time = -1;
    	
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[i].length; j++) {
    			if (grid[i][j] == 2) {
					int[] pos = new int[] {i,j};
					queue.add(pos);
				}
    			if (grid[i][j] == 1) {
    				freshOrange ++;
    			}
    		}
    	}
    	
    	if (freshOrange == 0) return 0;
    	
    	while (!queue.isEmpty()) {
    		
    		int rottenOrangeAtTheTime = queue.size();
    		for (int i = 0; i < rottenOrangeAtTheTime; i++) {
    			
    			int[] pos = queue.poll();
    			int x = pos[0];
    			int y = pos[1];
    			
    			if (x+1 < grid.length && grid[x+1][y] == 1) {
					grid[x+1][y] = 2;
					freshOrange --;
					queue.add(new int[] {x+1, y});
				}
    			if (x-1 >= 0 && grid[x-1][y] == 1) {
					grid[x-1][y] = 2;
					freshOrange --;
					queue.add(new int[] {x-1, y});
				}
    			if (y+1 < grid[x].length && grid[x][y+1] == 1) {
					grid[x][y+1] = 2;
					freshOrange --;
					queue.add(new int[] {x, y+1});
				}
    			if (y-1 >= 0 && grid[x][y-1] == 1) {
					grid[x][y-1] = 2;
					freshOrange --;
					queue.add(new int[] {x, y-1});
				}
    		}
    		time ++;
    	}
    	
    	if (freshOrange > 0) {
			return -1;
		}
    	
        return time;
    }
}

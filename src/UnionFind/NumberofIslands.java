package UnionFind;

import java.util.ArrayList;
import java.util.List;

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
		
		List<Integer> list = new ArrayList<>();
		list.forEach(i -> System.out.println(i));
	}
	
	private static int[] parents;
	private static int[] ranks;

	public static int numIslands(char[][] grid) {
		/**
		 * union find解法
		 */
		int m = grid.length;
		int n = grid[0].length;
		
		parents = new int[m*n];
		ranks = new int[m*n];
		
		for (int i = 0; i < parents.length; i++) {
    		parents[i] = i; /* 初始化，剛開始每個node的parent都是自己=>獨立set */
    		ranks[i] = 0;
    	}
		
		int lslandCount = 0;
		
		for (int i = 0; i < m; i++) {
			
			for(int j = 0; j < n; j++) {
				
				if (grid[i][j] == '1') { /* 為1的話先加count */
					lslandCount ++;
					/**
					 * 上跟左邊都要union，不可止union一邊就continue
					 * 例如下圖，只union一邊的話最左下角的會union不到
					 * 1,1,1
					 * 0,1,0
					 * 1,1,1
					 */
					if (i >= 1 && grid[i-1][j] == '1') { /* union成功的話count-1 */
						if (union(i*n+j, i*n+j-n)) lslandCount --;
					}
					if (j >= 1 && grid[i][j-1] == '1') {
						if (union(i*n+j, i*n+j-1)) lslandCount --;
					}
				}
			}
		}
		
		return lslandCount;
	}

	private static boolean union(int x, int y) {
		
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX == rootY) {
			return false;
		}
		
		if (ranks[rootX] > ranks[rootY]) {
			parents[rootY] = rootX;
		} else if (ranks[rootX] < ranks[rootY]) {
			parents[rootX] = rootY;
		} else {
			parents[rootY] = rootX;
			ranks[rootX] += 1;
		}
		
		return true;
	}
	
	private static int find(int x) {
		
		while (x != parents[x]) {
    		parents[x] = parents[parents[x]];
    		x = parents[x];
    	}
    	return x;
	}
}

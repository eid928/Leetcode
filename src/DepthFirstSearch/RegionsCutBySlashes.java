package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class RegionsCutBySlashes {

	public static void main(String[] args) {
		/**
		 * 給定一組字串array
		 * 其代表了一個n*n的格子，被斜線切割
		 * n = grid.length = grid[i].length
		 * 所以array裡面的字串長度會和array長度一樣
		 * 請求出這個n*n的格子一共被切成了幾塊
		 */
		String[] grid = {" /","/ "};
		System.out.println(regionsBySlashes(grid));
		String[] grid2 = {"//","//"};
		System.out.println(regionsBySlashes(grid2));
	}
	
	private static List<int[]> directions = new ArrayList<int[]>();
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}

    public static int regionsBySlashes(String[] grid) {
    	/**
    	 * 神煩
    	 * dfs
    	 * 先將原先的String[]轉成純數字表示的matrix
    	 * 長寬為原先的3倍，也就是將每個char轉成九宮格
    	 * 再進行dfs
    	 * 若轉成四格，長寬變為2倍，實作上會很困難
    	 * 因為可能需要走斜向方向，且會難以判斷哪些斜線是一起的
    	 * 例如：
    	 * / /
    	 * / /
    	 * 0 1 0 1
    	 * 1 0 1 0
    	 * 0 1 0 1
    	 * 1 0 1 0
    	 */
        int[][] matrix = new int[grid.length*3][grid[0].length()*3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == '/') {
                	matrix[i * 3][j * 3 + 2] = 1;
                	matrix[i * 3 + 1][j * 3 + 1] = 1;
                	matrix[i * 3 + 2][j * 3] = 1;
                } else if (grid[i].charAt(j) == '\\') {
                	matrix[i * 3][j * 3] = 1;
                	matrix[i * 3 + 1][j * 3 + 1] = 1;
                	matrix[i * 3 + 2][j * 3 + 2] = 1;
                }
            }
        }
        
        int regions = 0;
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
            	if (matrix[i][j] == 0) {
            		dfs(matrix, i, j);
            		regions++;
				}
            	System.out.print(matrix[i][j]+",");
            }
            System.out.println();
        }
    	
    	return regions;
    }

	private static void dfs(int[][] matrix, int x, int y) {
		
		if (x <0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
			return;
		}
		if (matrix[x][y] == 1) {
			return;
		}
		
		matrix[x][y] = 1;
		for (int[] direciton : directions) {
			dfs(matrix, x+direciton[0], y+direciton[1]);
		}
	}
}

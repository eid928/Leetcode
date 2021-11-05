package DepthFirstSearch;
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixII {

	public static void main(String[] args) {
		/**
		 * 給定一正整數n
		 * 回傳n*n的矩陣，並按照順時鐘順序填入1~n^2的數值
		 */
		int[][] matrix = generateMatrix(5);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]+",");
			}
			System.out.println();
		}

	}
	
	private static List<int[]> directions;
	
	static {
		directions = new ArrayList<>();
		directions.add(new int[] {0,1});
		directions.add(new int[] {1,0});
		directions.add(new int[] {0,-1});
		directions.add(new int[] {-1,0});
	}

    public static int[][] generateMatrix(int n) {
    	/**
    	 * dfs
    	 * 時間複雜度為O(n^2)
    	 */
    	int[][] matrix = new int[n][n];
    	dfs(matrix, 0, 0, 1, 0);
    	
        return matrix;
    }

	private static boolean dfs(int[][] matrix, int i, int j, int curNum, int direction) {
		
		if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] != 0) {
			return false;
		}
		
		matrix[i][j] = curNum;
		
		int nextI = i + directions.get(direction % 4)[0];
		int nextJ = j + directions.get(direction % 4)[1];
		
		/**
		 * 總共四個方向輪流
		 * 首先先照原方向前進，若沒問題則回傳true
		 * 若是撞牆了(dfs回傳false)，則將direction+1，輪流到下一個方向進行dfs
		 */
		if (dfs(matrix, nextI, nextJ, curNum+1, direction)) {
			return true;
		} else {
			direction += 1;
			nextI = i + directions.get(direction % 4)[0];
			nextJ = j + directions.get(direction % 4)[1];
			return dfs(matrix, nextI, nextJ, curNum+1, direction);
		}
	}
}

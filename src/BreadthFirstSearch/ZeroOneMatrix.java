package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZeroOneMatrix {
	
	private static List<int[]> directions = new ArrayList<>();
	
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}

	public static void main(String[] args) {
		
		int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
		
		int[][] updateMatrix = updateMatrix(matrix);

		for (int i = 0; i < updateMatrix.length; i++) {
			for (int j = 0; j < updateMatrix[i].length; j++) {
				System.out.print(updateMatrix[i][j]+",");
			}
			System.out.println("");
		}
	}
	
	public static int[][] updateMatrix(int[][] mat) {
		/**
		 * 優化做法
		 * 先遍歷matrix，值為0的加進queue，值為1的將值設為max
		 * 此時，queue裡面存的就是“已經找到最近0的格子”
		 * 之後用queue開始看，每次只看distance為1的四周
		 * 該格若看到鄰居值比自己多2以上的，則將鄰居的值為該格+1
		 * 並將鄰居放入queue，表示這個鄰居已經找到最近的0
		 */
		int m = mat.length;
    	int n = mat[0].length;
    	
    	Queue<int[]> queue = new LinkedList<>();
    	
    	for (int i = 0; i < m; i++) {
    		
			for (int j = 0; j < n; j++) {
				
				if (mat[i][j] == 0) queue.add(new int[] {i,j});
				else mat[i][j] = Integer.MAX_VALUE;
			}
		}
    	
    	while (!queue.isEmpty()) {
    		
    		int[] position = queue.poll();
    		int x = position[0];
    		int y = position[1];
    		
    		for (int[] dir : directions) {
    			
    			int neiborX = x + dir[0];
    			int neiborY = y + dir[1];
    			
    			if (neiborX < 0 || neiborY < 0 || neiborX >= m || neiborY >= n) continue;
    			
    			if (mat[neiborX][neiborY] < mat[x][y] + 1) { /* 0看到0，或是看到跟自己一樣or較小的 */
					continue;
				}
    			/* 該格若看到鄰居值比自己多2以上的，則將鄰居的值為該格+1 */
    			mat[neiborX][neiborY] = mat[x][y] + 1;
    			
    			queue.add(new int[] {neiborX, neiborY});
    		}
    	}
    	
        return mat;
	}

    public static int[][] updateMatrix2(int[][] mat) {
    	/**
    	 * 求最短距離=>直覺式的bfs
    	 * 當看到1的時候進行bfs，找到0的時候計算距離回傳
    	 * 但這個做法會超時
    	 */
    	int m = mat.length;
    	int n = mat[0].length;
    	
    	int[][] updateMatrix = new int[m][n];
    	
    	for (int i = 0; i < updateMatrix.length; i++) {
    		
			for (int j = 0; j < updateMatrix[i].length; j++) {
				
				if (mat[i][j] == 1) updateMatrix[i][j] = bfs(i, j, mat);
			}
			
		}
    	
        return updateMatrix;
    }

	private static int bfs(int i, int j, int[][] mat) {
		
		Queue<int[]> queue = new LinkedList<>();
		
		for (int[] dir : directions) {
			
			queue.add(new int[] {i+dir[0], j+dir[1]});
		}
		
		while (!queue.isEmpty()) {
			
			int[] position = queue.poll();
			int x = position[0];
			int y = position[1];
			
			if (x < 0 || y < 0 || x >= mat.length || y >= mat[0].length) continue;
			
			if (mat[x][y] == 0) {
				return Math.abs(x-i) + Math.abs(y-j);
			}
			
			for (int[] dir : directions) {
				
				queue.add(new int[] {x+dir[0], y+dir[1]});
			}
		}
		
		return 0;
	}
}

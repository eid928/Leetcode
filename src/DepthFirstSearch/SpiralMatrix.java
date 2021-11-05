package DepthFirstSearch;
import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {

	public static void main(String[] args) {
		
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		
		System.out.println(spiralOrder(matrix));
	}
	
	private static String direction; /* track目前的方向 */
	
	public static List<Integer> spiralOrder(int[][] matrix) {
		
		List<Integer> list = new LinkedList<>();
		int m = 0;
		int n = 0;
		direction = "right";
		while (hasNext(matrix, m, n)) {
			list.add(matrix[m][n]);
			int[] nextIndex = getNextIndex(matrix, m, n);
			m = nextIndex[0];
			n = nextIndex[1];
		}
		list.add(matrix[m][n]); /* 最後的元素因為!hasNext不會進入loop，手動加回去 */
		
        return list;
    }
	
	public static boolean hasNext(int[][] matrix, int m, int n) {
		
		int left = n == 0 ? -101 : matrix[m][n-1];
		int right = n == matrix[m].length-1 ? -101 : matrix[m][n+1];
		int up = m == 0 ? -101 : matrix[m-1][n];
		int down = m == matrix.length-1 ? -101 : matrix[m+1][n];
		
		if (left == -101 && right == -101 && up == -101 && down == -101) {
			return false;
		}
		
		return true;
	}
	
	public static int[] getNextIndex(int[][] matrix, int m, int n) {
		
		int nextElement = 0;
		matrix[m][n] = -101;
		
		if (direction.equals("right")) {
			try {
				nextElement = matrix[m][n+1]; /* 超出邊界或是碰到-101都會拋例外 */
				if (nextElement == -101) {
					throw new Exception();
				}
				return new int[] {m, n+1};
				
			} catch (Exception e) { /* 拋例外就換方向 */
				direction = "down";
				return getNextIndex(matrix, m, n);
			}
		}
		
		if (direction.equals("down")) {
			try {
				nextElement = matrix[m+1][n];
				if (nextElement == -101) {
					throw new Exception();
				}
				return new int[] {m+1, n};
				
			} catch (Exception e) {
				direction = "left";
				return getNextIndex(matrix, m, n);
			}
		}
		
		if (direction.equals("left")) {
			try {
				nextElement = matrix[m][n-1];
				if (nextElement == -101) {
					throw new Exception();
				}
				return new int[] {m, n-1};
				
			} catch (Exception e) {
				direction = "up";
				return getNextIndex(matrix, m, n);
			}
		}
		
		if (direction.equals("up")) {
			try {
				nextElement = matrix[m-1][n];
				if (nextElement == -101) {
					throw new Exception();
				}
				return new int[] {m-1, n};
				
			} catch (Exception e) {
				direction = "right";
				return getNextIndex(matrix, m, n);
			}
		}
		
		return null;
	}
}

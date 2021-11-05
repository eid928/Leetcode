package BinarySearch;

public class Searcha2DMatrix {

	public static void main(String[] args) {
		/**
		 * 給定一個sorted二維陣列和目標，回傳目標是否存在
		 */
		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		System.out.println(searchMatrix(matrix, 3));
	}
    public static boolean searchMatrix(int[][] matrix, int target) {
    	
    	/**
    	 * 把二維陣列看作一維，座標轉換，之後使用binary search
    	 */
    	int m = matrix.length;
    	int n = matrix[0].length;
    	/**
    	 * 二維座標i,j 一維坐標k
    	 * i*n + j = k
    	 * k/n = i;
    	 * k%n = j
    	 */
    	int left = 0;
    	int right = m*n-1;
    	
    	while (left <= right) {
    		
    		int mid = (right+left) / 2;
    		
    		int theNumber = matrix[mid/n][mid%n];
			if (theNumber == target) {
				return true;
			}
    		
    		else if (theNumber > target) {
    			right = mid - 1;
    		}
			
    		else {
    			left = mid + 1;
    		}
    	}
    	
        return false;
    }
}

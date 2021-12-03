package Sort;

public class KClosestPointstoOrigin {

	public static void main(String[] args) {
		/**
		 * 給定一組二維array
		 * array中的每個int[2]代表平面上的點座標
		 * 求出array中前k項距離原點最近的座標
		 */
		int[][] points1 = {{1,3},{-2,2}};
		int[][] points2 = {{3,3},{5,-1},{-2,4}};
		int[][] points3 = {{2,2},{2,2},{2,2},{2,2},{2,2},{2,2},{1,1}};
		
		int[][] results1 = kClosest(points1, 1);
		int[][] results2 = kClosest(points2, 2);
		int[][] results3 = kClosest(points3, 1);
		
		for (int[] point : results1) System.out.print("["+point[0]+","+point[1]+"], ");
		System.out.println();
		for (int[] point : results2) System.out.print("["+point[0]+","+point[1]+"], ");
		System.out.println();
		for (int[] point : results3) System.out.print("["+point[0]+","+point[1]+"], ");
	}

    public static int[][] kClosest(int[][] points, int k) {
        /**
         * quick select
         */
    	int left = 0;
    	int right = points.length-1;
    	
    	while (true) {
    		
    		int pivotIndex = partition(points, left, right);
    		if (pivotIndex == k-1) {
				int[][] result = new int[k][2];
				System.arraycopy(points, 0, result, 0, k);
				return result;
			}
    		
    		if (pivotIndex > k-1) {
				right = pivotIndex-1;
			} else {
				left = pivotIndex+1;
			}
    	}
    }

	private static int partition(int[][] points, int left, int right) {
		
		int pivotDistance = getDistance(points, left);
		int i = left+1;
		int j = right;
		
		while (i <= j) {
			
			if (getDistance(points, i) > pivotDistance && getDistance(points, j) < pivotDistance) {
				swap(points, i, j);
				i++;
				j--;
			}
			
			if (getDistance(points, i) <= pivotDistance) {
				i++;
			}
			
			if (getDistance(points, j) >= pivotDistance) {
				j--;
			}
		}
		
		swap(points, left, j);
		
		return j;
	}

	private static int getDistance(int[][] points, int i) {
		int x = points[i][0];
		int y = points[i][1];
		return x*x + y*y;
	}

	private static void swap(int[][] points, int i, int j) {
		
		int[] tmp = points[i];
		points[i] = points[j];
		points[j] = tmp;
	}
}

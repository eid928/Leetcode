package UnionFind;

public class NumberofProvinces {

	private static int[] parents;
	private static int[] ranks;
	private static int count;
	
	public static void main(String[] args) {
		/**
		 * 給定一組n*n的array，表示node間的相連
		 * 例如{1,1,0}表示，1號node和2號node相連，但不與3號node相連
		 * 求出有多少組相連的集合
		 */
		int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
		System.out.println(findCircleNum(isConnected));
		
		isConnected = new int[][] {{1,0,0},{0,1,0},{0,0,1}};
		System.out.println(findCircleNum(isConnected));
	}

    public static int findCircleNum(int[][] isConnected) {
    	/**
    	 * UnionFind
    	 * 用一個static field count來紀錄獨立set數量
    	 * 每次成功union就count--
    	 */
    	parents = new int[isConnected.length+1];
    	ranks = new int[isConnected.length+1];
    	
    	for (int i = 0; i < parents.length; i++) {
    		parents[i] = i;
    		ranks[i] = 0;
    	}
    	
    	count = isConnected.length;
    	
    	for (int i = 0; i < isConnected.length; i++) {
    		
    		for (int j = 0; j < isConnected[i].length; j++) {
    			if (i == j) continue;
    			if (isConnected[i][j] == 1) {
    				
    				union(i+1, j+1);
    			}
    		}
    	}
    	
        return count;
    }

	private static void union(int x, int y) {
		
		int rootX = findRoot(x);
		int rootY = findRoot(y);
		
		if (rootX == rootY) {
			return;
		}
		
		if (ranks[rootX] > ranks[rootY]) {
			parents[rootY] = rootX;
		} else if (ranks[rootX] < ranks[rootY]) {
			parents[rootX] = rootY;
		} else {
			parents[rootX] = rootY;
            ranks[rootY]++;
		}
		
		count --;
	}

	private static int findRoot(int x) {
		
		if (x != parents[x]) {
			parents[x] = findRoot(parents[x]);
		}
		
		return parents[x];
	}
}

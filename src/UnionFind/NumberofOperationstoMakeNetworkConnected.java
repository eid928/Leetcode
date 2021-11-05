package UnionFind;

public class NumberofOperationstoMakeNetworkConnected {

	private static int[] parents;
	private static int[] ranks;
	
	public static void main(String[] args) {
		/**
		 * 總共有n台電腦
		 * 以及目前的纜線connections
		 * 請問要將這些纜線移動幾次，才可將所有電腦連接？
		 */
		int[][] connections = {{0,1},{0,2},{0,3},{1,2},{1,3}};
		int n = 6;
		System.out.println(makeConnected(n, connections));
	}

    public static int makeConnected(int n, int[][] connections) {
    	/**
    	 * UnionFind
    	 * 要將n台電腦相連接，需要n-1條纜線
    	 * 找出目前有幾個cycle
    	 * 再推出最短移掉幾個cycle可達成目標(因為纜線有可能>電腦數-1)
    	 */
    	if (connections.length < n - 1) {
			return -1;
		}
    	
    	parents = new int[n];
    	ranks = new int[n];
    	
    	for (int i = 0; i < parents.length; i++) {
    		parents[i] = i;
    		ranks[i] = 0;
    	}
    	
    	int cycleCount = 0;
    	
    	for (int i = 0; i < connections.length; i++) {
    		boolean result = union(connections[i][0], connections[i][1]);
    		if (!result) cycleCount++;
    	}
    	
    	return cycleCount + n - 1 - connections.length;
    }

	private static boolean union(int x, int y) {
		
		int rootX = findRoot(x);
		int rootY = findRoot(y);
		
		if (rootX == rootY) {
			return false;
		}
		
		if (ranks[rootX] > ranks[rootY]) {
			parents[rootY] = rootX;
		} else if (ranks[rootX] < ranks[rootY]) {
			parents[rootX] = rootY;
		} else {
			parents[rootX] = rootY;
			ranks[rootY]++;
		}
		
		return true;
	}

	private static int findRoot(int x) {
		
		if (x != parents[x]) {
			parents[x] = findRoot(parents[x]);
		}
		
		return parents[x];
	}
}

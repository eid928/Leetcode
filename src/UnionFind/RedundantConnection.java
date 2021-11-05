package UnionFind;

public class RedundantConnection {
	
	private static int[] parent;
	private static int[] rank;

	public static void main(String[] args) {
		/**
		 * 給定一組edges array表示node之間的連接
		 * 這張graph內有cycle，請找出要移除哪個edge才可以使得cycle消失？
		 * 若有多組答案，請回傳在edges中index較大的那組edge
		 */
		int[][] edges = {{1,5},{3,4},{3,5},{4,5},{2,4}};
		int[] edge = findRedundantConnection(edges);
		System.out.println("["+edge[0]+","+edge[1]+"]");
	}

    public static int[] findRedundantConnection(int[][] edges) {
        /**
         * UnionFind
         * 以例題來說，總共五個節點
         * 每個edge代表要將某兩個節點Union在一起
         * 先想像五個節點本是獨立的(處在五個disjoint set中)
         * 接著走訪edges，依照edges的指示開始做union
         * 若走到某個edge發現這兩個節點已在同個disjoint set無法union
         * 這個edge即是答案
         * 流程：
         * initial: (1)(2)(3)(4)(5)
         * edges[0]:(1,2)(3)(4)(5)
         * edges[1]:(1,2,3)(4)(5)
         * edges[2]:(1,2,3,4)(5)
         * edges[3]想要union 1,4但已在同一個set => 答案
         */
    	
    	parent = new int[edges.length+1];
    	rank = new int[edges.length+1];
    	
    	for (int i = 0; i < parent.length; i++) {
    		parent[i] = i; /* 初始化，剛開始每個node的parent都是自己=>獨立set */
    		rank[i] = 0;
    	}
    	
    	for (int i = 0; i < edges.length; i++) {
    		boolean unionResult = union(edges[i][0], edges[i][1]);
    		if (!unionResult) return edges[i];
    	}
    	
    	return new int[] {};
    }
    
    private static int findRoot(int x) {
    	/**
    	 * disjoint set中，root的特性是自己為自己的parent
    	 */
    	while (x != parent[x]) {
    		parent[x] = parent[parent[x]];
    		x = parent[x];
    	}
    	return x;
    }
    
    private static boolean union(int x, int y) {
    	
    	int rootX = findRoot(x);
    	int rootY = findRoot(y);
    	
    	if (rootX == rootY) {
			return false;
		}
    	
    	if (rank[rootX] > rank[rootY]) {
			parent[rootY] = rootX;
		} else if (rank[rootX] < rank[rootY]) {
			parent[rootX] = rootY;
		} else {
			parent[rootX] = rootY;
            rank[rootY]++;
		}
    	
    	return true;
    }
}

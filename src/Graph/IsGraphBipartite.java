package Graph;

public class IsGraphBipartite {
	
	private static boolean isConflict;

	public static void main(String[] args) {
		/**
		 * 給定一個undirected graph
		 * 請判斷它是否為Bipartite
		 * Bipartite: 可切一刀讓所有的edge一次斷成兩半
		 * 即可以把所有的node分成兩個set
		 * 而所有的edge的兩端都分別屬於兩個set
		 */
		int[][] graph1 = {{1,2,3},{0,2},{0,1,3},{0,2}};
		int[][] graph2 = {{1,3},{0,2},{1,3},{0,2}};
		int[][] graph3 = {{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};

		System.out.println(isBipartite(graph1));
		System.out.println(isBipartite(graph2));
		System.out.println(isBipartite(graph3));
	}

    public static boolean isBipartite(int[][] graph) {
    	/**
    	 * dfs著色法
    	 * 將graph塗色，相鄰的兩個node要塗上不同顏色
    	 * 代表其屬於不同的set
    	 * color: 0-未著色, 1-紅色, -1-藍色
    	 * 而若是塗色的過程中發生顏色衝突(相鄰同色)，即代表此graph並非Bipartite
    	 */
    	isConflict = false;
    	int[] colors = new int[graph.length];
    	for (int i = 0; i < graph.length; i++) {
    		if (colors[i] == 0) dfs(graph, i, colors, 1);
    		if (isConflict) {
				return false;
			}
    	}
    	
        return !isConflict;
    }

	private static void dfs(int[][] graph, int vertex, int[] colors, int color) {
		
		if (isConflict) {
			return;
		}
		
		if (colors[vertex] == -color) { /* 本該塗成紅(藍)卻是藍(紅)的=>衝突 */
			isConflict = true;
			return;
		}
		
		if (colors[vertex] == color) {
			return;
		}
		
		colors[vertex] = color;
		for (int i = 0; i < graph[vertex].length; i++) {
			dfs(graph, graph[vertex][i], colors, -color);
		}
	}
}

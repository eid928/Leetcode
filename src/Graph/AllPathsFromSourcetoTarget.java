package Graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourcetoTarget {

	public static void main(String[] args) {
		/**
		 * 給定一組graph，有0~n-1總共n個Node
		 * graph是DAG
		 * 請求出從0到n-1的所有路徑
		 */
		int[][] graph = {{1,2},{3},{3},{}};
		System.out.println(allPathsSourceTarget(graph));
		
		int[][] graph2 = {{4,3,1},{3,2,4},{3},{4},{}};
		System.out.println(allPathsSourceTarget(graph2));
	}

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /**
         * 標準dfs backtracking
         */
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	List<Integer> curPath = new ArrayList<Integer>();
    	
    	dfs(graph, 0, curPath, results);
    	
    	return results;
    }

	private static void dfs(int[][] graph, int node, List<Integer> curPath, List<List<Integer>> results) {
		
		if (node == graph.length-1) {
			
			curPath.add(node);
			results.add(new ArrayList<Integer>(curPath));
			curPath.remove(curPath.size()-1);
			return;
		}
		
		curPath.add(node);
		for (int i = 0; i < graph[node].length; i++) {
			
			int nextNode = graph[node][i];
			dfs(graph, nextNode, curPath, results);
		}
		curPath.remove(curPath.size()-1);
	}
}

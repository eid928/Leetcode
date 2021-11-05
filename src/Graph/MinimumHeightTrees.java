package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinimumHeightTrees {

	private static int minHeight = Integer.MAX_VALUE;
	private static int height;
	
	public static void main(String[] args) {
		/**
		 * 給定一個int n代表node的數量
		 * 給定一組edges代表node之間連結的邊
		 * 求出這張graph所能形成的tree中，高度最小的tree的root
		 */
		int n = 6;
		int[][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
		System.out.println(findMinHeightTrees(n, edges));
	}
	
	public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
		/**
		 * 剝洋蔥法
		 * 若我們把這張graph以放射狀平鋪，處于最中心node們即是答案，而處於中心的node們不會超過2個
		 * 因為若是有3個，則一定能再形成一層
		 * 所以我們從最外圍(leaf)出發，把最外圍的葉子們都拔掉
		 * 例如例題的graph：[[3], [3], [3], [0, 1, 2, 4], [3, 5], [4]]
		 * 先把只有一個連接點的node刪掉，再把往內一圈連接leaf的邊也刪掉，則往內一圈也變成leaf
		 * 一層一層往內剝，直到剩下兩個node以下為止
		 */
		if (n < 2) {
            List<Integer> centroids = new ArrayList<>();
            for (int i = 0; i < n; i++) centroids.add(i);
            return centroids;
        }
		
		List<Set<Integer>> graph = new ArrayList<>();
		/* 用set方便刪除 */
    	for (int i = 0; i < n; i++) {
    		graph.add(new HashSet<>());
    	}
    	for (int[] edge : edges) {
    		
    		int node1 = edge[0];
    		int node2 = edge[1];
    		graph.get(node1).add(node2);
    		graph.get(node2).add(node1);
    	}
    	
    	System.out.println(graph);
    	
    	List<Integer> leaves = new ArrayList<>();
    	
    	for (int i = 0; i < graph.size(); i++) {
    		if (graph.get(i).size() == 1) {
				leaves.add(i);
			}
    	}
    	
    	int remainNodeCount = n;
    	while (remainNodeCount > 2) {
    		
    		remainNodeCount -= leaves.size();
    		
    		List<Integer> newLeaves = new ArrayList<>();
    		
    		for (Integer leave : leaves) {
    			Integer neighborOfLeaf = graph.get(leave).iterator().next();
    			graph.get(neighborOfLeaf).remove(leave);
    			if (graph.get(neighborOfLeaf).size() == 1) {
    				newLeaves.add(neighborOfLeaf);
				}
    		}
    		
    		leaves = newLeaves;
    	}
    	
		return leaves;
	}
	
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) {
    	/**
    	 * 根據題目可以組成一個undirected graph
    	 * 每個node當作root都可以形成一棵tree
    	 * 而樹的高度，即是以這個node為出發點做dfs得出的最遠的長度
    	 * 先形成graph，後針對每個node做dfs，將擁有最小高度的node寫進答案中
    	 * (O(N^2)，此做法在leetcode會超時)
    	 */
    	List<List<Integer>> graph = new ArrayList<>();
    	for (int i = 0; i < n; i++) {
    		graph.add(new ArrayList<>());
    	}
    	for (int[] edge : edges) {
    		
    		int node1 = edge[0];
    		int node2 = edge[1];
    		graph.get(node1).add(node2);
    		graph.get(node2).add(node1);
    	}
    	
    	System.out.println(graph);
    	
    	Map<Integer, List<Integer>> heightNodeMap = new HashMap<>();
    	minHeight = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < n; i ++) {
    		height = 0;
    		int[] visited = new int[n];
    		dfs(i, 0, visited, graph);
    		if (!heightNodeMap.containsKey(height)) {
    			heightNodeMap.put(height, new ArrayList<>());
			}
    		heightNodeMap.get(height).add(i);
    		
    		minHeight = Math.min(minHeight, height);
    	}
    	
        return heightNodeMap.get(minHeight);
    }
    
	private static void dfs(int start, int currHeight, int[] visited, List<List<Integer>> graph) {
		
		visited[start] = -1;
		
		for (int i = 0; i < graph.get(start).size(); i++) {
			if (visited[graph.get(start).get(i)] == -1) {
				continue;
			}
			dfs(graph.get(start).get(i), currHeight+1, visited, graph);
		}
		/**
		 * 走到這裡代表finish了
		 * 可以結算目前的高度
		 */
		height = Math.max(currHeight, height);
	}
}

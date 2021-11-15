package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {

	public static void main(String[] args) {
		/*
		 * 給定一組directed graph
		 * 若一個vertex沒有接往其他vertex的edge，稱其為terminal
		 * 若一個vertex的所有path都可以接到terminal，稱為safe
		 * 若一個vertex的所有path可能進入死循環cycle，稱為unsafe
		 * 請列出所有safe的vertex
		 */
		
		int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
		System.out.println(eventualSafeNodes(graph));
		
		int[][] graph2 = {{},{0,2,3,4},{3},{4},{}};
		System.out.println(eventualSafeNodes(graph2));
	}

    public static List<Integer> eventualSafeNodes(int[][] graph) {
    	/**
    	 * 簡單來說就是要找從那些點出發可能會碰到cycle
    	 * 使用dfs
    	 * status有三種狀態0,1,2
    	 * 0: 完全未訪問
    	 * 1: 曾訪問，路線尚未完成
    	 * 2: 已訪問，已起做為起點是safe
    	 * 降低時間的關鍵就是2
    	 * 若走到一半碰到2的節點，那就不用走下去了後面一定是safe
    	 * 而若一個起點出發發現會碰到cycle，則那條路徑就會保留為1的狀態，別人再碰到就知道有cycle
    	 */
    	List<Integer> result = new ArrayList<>();
    	int[] status = new int[graph.length];
    	
    	for (int i = 0; i < graph.length; i++) {
    		
    		if (dfs(graph, i, status)) {
				result.add(i);
			}
    	}
        
    	return result;
    }

	private static boolean dfs(int[][] graph, int start, int[] status) {
		
		if (status[start] == 1) {
			return false;
		}
		
		if (graph[start].length == 0 || status[start] == 2) {
			return true;
		}
		
		status[start] = 1;
		
		for (int i = 0; i < graph[start].length; i++) {
			
			if(!dfs(graph, graph[start][i], status)) {
				return false;
			}
		}
		
		status[start] = 2;
		return true;
	}
}

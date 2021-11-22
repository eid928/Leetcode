package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ShortestPathwithAlternatingColors {

	public static void main(String[] args) {
		/*
		 * 給定一正整數n，代表有0~n-1共n個節點
		 * 給定兩組單向edges array，分別為紅色邊以及藍色邊
		 * 從0出發到每個節點，途中經過的邊必須紅藍交替出現
		 * 請求出從0號節點開始到各節點的最短距離分別是多少
		 */
		int[][] red = {{0,1},{1,2}};
		int[][] blue = {};
		int[] result = shortestAlternatingPaths(3, red, blue); // [0,1,-1]
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		
		int[][] red2 = {{0,1}};
		int[][] blue2 = {{2,1}};
		result = shortestAlternatingPaths(3, red2, blue2); // [0,1,-1]
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		
		int[][] red3 = {{1,0}};
		int[][] blue3 = {{2,1}};
		result = shortestAlternatingPaths(3, red3, blue3); // [0,-1,-1]
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		
		int[][] red4 = {{0,1}};
		int[][] blue4 = {{1,2}};
		result = shortestAlternatingPaths(3, red4, blue4); // [0,1,2]
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		
		int[][] red5 = {{0,1},{0,2}};
		int[][] blue5 = {{1,0}};
		result = shortestAlternatingPaths(3, red5, blue5); // [0,1,1]
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
	}

    public static int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
    	/**
    	 * bfs
    	 * 但十分麻煩
    	 * graph, queue, visited都要準備紅藍兩組
    	 * queue: 紅色紀錄的是“經由紅色邊抵達的節點”，所以拿出來後要接著看藍色graph，反之亦然
    	 * visited: 紅色紀錄的是“經由紅色邊抵達並結束訪問的節點”
    	 * 就算經由紅色抵達訪問過了，若又能經由藍色抵達則必須再次查看，因為可能會發現未訪問到的節點
    	 */
    	List<List<Integer>> redGraph = new ArrayList<List<Integer>>();
    	List<List<Integer>> blueGraph = new ArrayList<List<Integer>>();
    	
    	for (int i = 0; i < n; i++) {
    		redGraph.add(new ArrayList<Integer>());
    		blueGraph.add(new ArrayList<Integer>());
    	}
    	for (int[] redEdge : red_edges) {
    		int from = redEdge[0];
    		int to = redEdge[1];
    		redGraph.get(from).add(to);
    	}
    	for (int[] blueEdge : blue_edges) {
    		int from = blueEdge[0];
    		int to = blueEdge[1];
    		blueGraph.get(from).add(to);
    	}
    	
    	System.out.println(redGraph);
    	System.out.println(blueGraph);
    	
    	int[] results = new int[n];
    	Arrays.fill(results, -1);
    	
    	Queue<Integer> redQueue = new LinkedList<Integer>();
    	Queue<Integer> blueQueue = new LinkedList<Integer>();
    	Set<Integer> visitedByRed = new HashSet<Integer>();
    	Set<Integer> visitedByBlue = new HashSet<Integer>();
    	redQueue.add(0);
    	blueQueue.add(0);
    	
    	int distance = 0;
    	
    	while (!redQueue.isEmpty() || !blueQueue.isEmpty()) {
    		
    		int redQueueSize = redQueue.size();
    		int blueQueueSize = blueQueue.size();
    		for (int i = 0; i < redQueueSize; i++) {
    			Integer curNode = redQueue.poll();
    			results[curNode] = results[curNode] == -1 ? distance : Math.min(distance, results[curNode]);
    			visitedByRed.add(curNode);
    			List<Integer> neighbors = blueGraph.get(curNode);
    			for (int neighbor : neighbors) {
    				if (!visitedByBlue.contains(neighbor)) blueQueue.add(neighbor);
    			}
    		}
    		for (int i = 0; i < blueQueueSize; i++) {
    			Integer curNode = blueQueue.poll();
    			results[curNode] = results[curNode] == -1 ? distance : Math.min(distance, results[curNode]);
    			visitedByBlue.add(curNode);
    			List<Integer> neighbors = redGraph.get(curNode);
    			for (int neighbor : neighbors) {
    				if (!visitedByRed.contains(neighbor)) redQueue.add(neighbor);
    			}
    		}
    		distance++;
    	}
    	
        return results;
    }
}

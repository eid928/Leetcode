package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PathwithMaximumProbability {

	public static void main(String[] args) {
		/**
		 * 考慮一組undirected edges以及n個節點，且每條edge有其對應的traverse成功機率seccProb
		 * 請問從start到end的所有路徑中，成功機率最高是多少？
		 */
		int[][] edges = {{0,1},{1,2},{0,2}};
		double[] seccProb = {0.5,0.5,0.2};
		int n = 3;
		System.out.println(maxProbability(n, edges, seccProb, 0, 2));
		
		seccProb = new double[] {0.5,0.5,0.3};
		System.out.println(maxProbability(n, edges, seccProb, 0, 2));
	}

	public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        /**
         * 所有機率皆為正數 => Dijkstra Algorithm
         * 詳細可見NetworkDelayTime.java
         */
		List<Map<Integer, Double>> graph = new ArrayList<>();
    	double[] distance = new double[n];
    	
    	for (int i = 0; i < n; i++) {
    		graph.add(new HashMap<>());
    		distance[i] = 0;
    	}
    	
    	for (int i = 0; i < edges.length; i++) {
    		int a = edges[i][0];
    		int b = edges[i][1];
    		double weight = succProb[i];
    		graph.get(a).put(b, weight);
    		graph.get(b).put(a, weight);
    	}
		/**
		 * Comparator需使用comparingDouble，直接減去會有誤差
		 */
    	PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(pair -> -pair[1]));
    	
    	distance[start] = 1;
    	pq.add(new double[] {start, distance[start]});
    	
    	boolean[] visited = new boolean[n+1];
    	
    	while (!pq.isEmpty()) {
    		
    		double[] pair = pq.poll();
    		int source = (int) pair[0];
    		if (visited[source]) continue;
    		System.out.print(source+":"+distance[source]+",");
    		
    		if (source == end) return distance[end];
    		/**
    		 * 重點：因為有給終點，所有當poll到終點時，表示他已經是visited了
    		 * 而Dijkstra可以運作的關鍵在於：任何針對visited(已經被從PQ裡poll出來)節點的relax皆一定會fail
    		 * 所以這時終點已經是最高機率路徑 => 直接回傳可降低時間
    		 */
    		
    		for (int destination : graph.get(source).keySet()) {
    			/* relaxation */
    			double newProb = distance[source] * graph.get(source).get(destination);
    			if (newProb > distance[destination]) {
    				distance[destination] = newProb;
    				pq.add(new double[] {destination, distance[destination]});
    			}
    		}
    		
    		visited[source] = true;
    	}
		
		return 0.0;
    }
}

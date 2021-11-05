package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {
	
	public static void main(String[] args) {
		/**
		 * 有一個含路徑長且有向網路連線傳輸graph
		 * times array中的元素表示為[起點, 終點, 所需時間]
		 * n = 有多少節點
		 * k = 出發點
		 * 請求出從出發點開始傳輸訊息開始計時
		 * 所有節點接收到訊息結束，最快需花多少時間
		 * 即從出發點抵達所有節點的最短路徑中，最長的最短路徑為多長？
		 */
		
		int[][] times = {{2,1,1},{2,3,1},{3,4,1},{2,4,1}};
		System.out.println(networkDelayTime(times, 4, 2));
		
		int[][] times2 = {{1,2,1},{2,1,3}};
		System.out.println(networkDelayTime(times2, 2, 2));
		
		int[][] times3 = {{1,2,1},{2,3,2},{1,3,4}};
		System.out.println(networkDelayTime(times3, 3, 1));
		
		int[][] times4 = {{4,2,76},{1,3,79},{3,1,81},{4,3,30},{2,1,47},{1,5,61},{1,4,99},{3,4,68},{3,5,46},{4,1,6},{5,4,7},{5,3,44},{4,5,19},{2,3,13},{3,2,18},{1,2,0},{5,1,25},{2,5,58},{2,4,77},{5,2,74}};
		System.out.println(networkDelayTime(times4, 5, 3));
	}

    public static int networkDelayTime(int[][] times, int n, int k) {
    	/**
    	 * Dijkstra Algorithm
    	 * 只要所有的edge weight不為負數，即使有cycle，也可使用Dijkstra Algorithm
    	 * Dijkstra Algorithm可以處理Single-Source Shortest Path，也就是單起點到其他所有vertex的最短路徑
    	 * 可以滿足題目需求
    	 */
    	
    	/**
    	 * step1: 先將times轉為正規graph，可快速找出兩vertex間得edge weight
    	 * step2: 建立distance紀錄起點到該vertex的距離，初始化為無限大
    	 */
    	List<Map<Integer, Integer>> graph = new ArrayList<>();
    	int[] distance = new int[n+1];
    	
    	for (int i = 0; i < n+1; i++) {
    		graph.add(new HashMap<>());
    		distance[i] = Integer.MAX_VALUE;
    	}
    	
    	for (int i = 0; i < times.length; i++) {
    		int from = times[i][0];
    		int to = times[i][1];
    		int weight = times[i][2];
    		graph.get(from).put(to, weight);
    	}
    	
    	/**
    	 * step3:
    	 * 最短距離的最終答案都會是一個shortest path tree
    	 * Dijkstra Algorithm為使用min priority queue的Greedy Algorithm(所以weight不可以為負)
    	 * PQ contains all unvisited vertices in order of distance
    	 * 
    	 * from CS61B:
    	 * Insert all vertices into fringe PQ, storing vertices in order of distance from source.
    	 * Repeat: Remove (closest) vertex v from PQ, and relax all edges pointing from v.
    	 * 而Dijkstra可以運作的關鍵在於：任何針對visited(已經被從PQ裡poll出來)節點的relax皆一定會fail => 所以一定每條edge weight >= 0
    	 * 
    	 * 假設起點為0，將距離distance加進pq，初始pq就會是[0:0, 1:max, 2:max, 3:max, ...]
    	 * 每次取出最前面的節點，針對他relax他所有的鄰居
    	 * 假設0可以走到1和3，relax後pq會變成：
    	 * [1:2, 3:4, 2:max, ...]
    	 * 
    	 * 然而，java的PriorityQueue無法做即時更新，像是3的距離更新為4的時候，無法直接更新他的priority
    	 * 所以實作上，一開始pq只加入起點0，並以boolean紀錄這個vertex是否有被走過了
    	 * 若0的鄰居3被relax了，即起點到3的最短距離被更新了(max->4)，把3放進pq中
    	 * 若之後3又被更新為2，並在distance=2的時候執行relax，則標註為visited，不再去訪問第二個3
    	 * 
    	 * 注意：queue中不能只儲存vertex，要連當下的distance一起儲存
    	 * 否則進入for迴圈的source不一定是按照distance小到大的順序
    	 * 導致leetcode最後一個測試案例不會通過
    	 * 這是因為pq在compare是按照當下的distance
    	 * 假設pq中有2個一樣的vertex，更新前更新後，但pq全都會用更新後的distance來看
    	 */
    	PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				return o1[1] - o2[1];
			}
		});
    	distance[k] = 0;
    	queue.add(new int[] {k, distance[k]});
    	boolean[] visited = new boolean[n+1];
    	
    	while (!queue.isEmpty()) {
    		
    		int[] source = queue.poll();
    		
    		if (visited[source[0]]) continue;
    		System.out.print(source[0]+":"+distance[source[0]]+","); /* 檢查進入for迴圈的是否按照ditance小到大的順序 */
    		
    		for (int destination : graph.get(source[0]).keySet()) {
    			/* relaxation */
    			int d = graph.get(source[0]).get(destination) + distance[source[0]];
    			
    			if (d < distance[destination]) {
    				distance[destination] = d;
    				queue.add(new int[] {destination, distance[destination]});
    			}
        	}
    		visited[source[0]] = true;
    	}
    	
    	int maxDistance = 0;
    	for (int i = 1; i < distance.length; i++) {
    		maxDistance = Math.max(maxDistance, distance[i]);
    	}
    	
        return maxDistance == Integer.MAX_VALUE ? -1 : maxDistance;
    }
}

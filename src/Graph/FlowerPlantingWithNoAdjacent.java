package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FlowerPlantingWithNoAdjacent {

	public static void main(String[] args) {
		/**
		 * 共有n座花園，編號為1~n
		 * 有4種花，編號為1~4
		 * paths為表示花園之間的相鄰關係
		 * 請在花園中種花，且相鄰的花園不能種一樣的花
		 */
		int[][] paths = {{1,2},{2,3},{3,1}};
		int[] result = gardenNoAdj(3, paths); // 1,2,3
		for (int f : result) {
			System.out.print(f+",");
		}
		System.out.println();
		int[][] paths2 = {{1,2},{3,4}};
		int[] result2 = gardenNoAdj(4, paths2); // 1,2,1,2
		for (int f : result2) {
			System.out.print(f+",");
		}
		System.out.println();
		int[][] paths3 = {{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}};
		int[] result3 = gardenNoAdj(4, paths3); // 1,2,3,4
		for (int f : result3) {
			System.out.print(f+",");
		}
		System.out.println();
		int[][] paths4 = {{6,4},{6,1},{3,1},{4,5},{2,1},{5,6},{5,2}};
		int[] result4 = gardenNoAdj(6, paths4); // 1,2,2,1,3,2
		for (int f : result4) {
			System.out.print(f+",");
		}
	}

	public static int[] gardenNoAdj(int n, int[][] paths) {
		/**
		 * bfs
		 * 和dfs大同小異
		 */
		List<List<Integer>> graph = new ArrayList<List<Integer>>();
		Queue<Integer> queue = new LinkedList<Integer>();
		
    	for (int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Integer>());
    		if (i != 0) queue.add(i);
    	}
    	for (int i = 0; i < paths.length; i++) {
    		
    		int x = paths[i][0];
    		int y = paths[i][1];
    		graph.get(x).add(y);
    		graph.get(y).add(x);
    	}
    	System.out.println(graph);
    	
    	int[] flowers = new int[n];
    	
    	while (!queue.isEmpty()) {
    		
    		int curGarden = queue.poll();
    		if (flowers[curGarden-1] != 0) {
				continue;
			}
    		
    		List<Integer> neighbors = graph.get(curGarden);
    		int flower = 1;
    		boolean[] used = new boolean[5];
    		for (int garden : neighbors) {
    			if (flowers[garden-1] != 0) {
    				used[flowers[garden-1]] = true;
    			} else {
					queue.add(garden);
				}
    		}
    		for (int i = 1; i <= 4; i++) {
    			if (used[i] && i == flower) {
    				flower++;
    			}
    		}
    		flowers[curGarden-1] = flower;
    	}
    	
		return flowers;
	}
	
    public static int[] gardenNoAdj2(int n, int[][] paths) {
        /**
         * 先建立雙向graph後dfs
         */
    	List<List<Integer>> graph = new ArrayList<List<Integer>>();
    	for (int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	for (int i = 0; i < paths.length; i++) {
    		
    		int x = paths[i][0];
    		int y = paths[i][1];
    		graph.get(x).add(y);
    		graph.get(y).add(x);
    	}
    	System.out.println(graph);
    	
    	int[] flowers = new int[n];
    	for (int i = 1; i <= n; i++) {
    		dfs(graph, flowers, i);
    	}
    	
    	return flowers;
    }

	private static void dfs(List<List<Integer>> graph, int[] flowers, int start) {
		/**
		 * 由於花有四種，去種下一間的時候不能無腦種下一種
		 * 不然很有可能繞一圈後種了跟第一間一樣的花
		 * 所以每次到一間，都要遍歷四周的花園，看看有哪些花是種過的，優先種沒種過且編號小的花
		 */
		if (flowers[start-1] != 0) {
			return;
		}
		List<Integer> neighbors = graph.get(start);
		
		int flower = 1;
		boolean[] used = new boolean[5];
		for (int garden : neighbors) {
			if (flowers[garden-1] != 0) {
				used[flowers[garden-1]] = true;
			}
		}
		for (int i = 1; i <= 4; i++) {
			if (used[i] && i == flower) {
				flower++;
			}
		}
		
		flowers[start-1] = flower;
		
		for (int garden : neighbors) {
			dfs(graph, flowers, garden);
		}
	}
}

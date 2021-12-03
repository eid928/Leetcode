package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumNumberofVerticestoReachAllNodes {

	public static void main(String[] args) {
		/**
		 * 給定一組單向graph
		 * 求出若要能走訪所有節點
		 * 最少需要從哪些節點出發才能辦到？
		 */
		List<List<Integer>> edges = new ArrayList<List<Integer>>();
		edges.add(Arrays.asList(0, 1));
		edges.add(Arrays.asList(0, 2));
		edges.add(Arrays.asList(2, 5));
		edges.add(Arrays.asList(3, 4));
		edges.add(Arrays.asList(4, 2));
		System.out.println(findSmallestSetOfVertices(6, edges)); // [0,3]
		edges = new ArrayList<List<Integer>>();
		edges.add(Arrays.asList(0, 1));
		edges.add(Arrays.asList(2, 1));
		edges.add(Arrays.asList(3, 1));
		edges.add(Arrays.asList(1, 4));
		edges.add(Arrays.asList(2, 4));
		System.out.println(findSmallestSetOfVertices(5, edges)); // [0,2,3]
	}

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        /**
         * 不用dfs, bfs
         * 純粹是看哪些節點的進入degree為0
         */
    	int[] hasIncome = new int[n];
    	
    	for (int i = 0; i < edges.size(); i++) {
    		int to = edges.get(i).get(1);
    		hasIncome[to] += 1;
    	}
    	
    	for (int i : hasIncome) System.out.print(i+",");
    	
    	List<Integer> result = new ArrayList<Integer>();
    	
    	for (int i = 0; i < hasIncome.length; i++) {
    		if (hasIncome[i] == 0) {
				result.add(i);
			}
    	}
    	
    	return result;
    }
}

package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision {

	public static void main(String[] args) {
		/**
		 * 給定一組equation以及其兩兩相除的value
		 * 在給定一組queries
		 * 請求出queries中每個query的解，若無解則回傳-1
		 */
		List<List<String>> equations = new ArrayList<>();
		String[][] tmps1 = {{"a","b"},{"b","c"}};
		for (String[] tmp : tmps1) {
			List<String> equation = new ArrayList<>();
			equation.add(tmp[0]);
			equation.add(tmp[1]);
			equations.add(equation);
		}
		
		double[] values = {2.0,3.0};
		
		List<List<String>> queries = new ArrayList<>();
		String[][] tmps2 = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};
		for (String[] tmp : tmps2) {
			List<String> query = new ArrayList<>();
			query.add(tmp[0]);
			query.add(tmp[1]);
			queries.add(query);
		}
		
		System.out.println(equations);
		System.out.println(queries);
		
		for (double value : calcEquation(equations, values, queries)) {
			System.out.print(value+",");
		}
	}

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /**
         * graph問題
         * 先做成相對應的graph
         * 由於相除有值，此graph為有向且不等距的graph
         * 例如a/b=2，即a到b為2，b到a為1/2，
         */
    	Map<String, Map<String, Double>> graph = new HashMap<>();
    	for (int i = 0; i < values.length; i++) {
    		
    		String first = equations.get(i).get(0);
    		String second = equations.get(i).get(1);
    		
    		Map<String, Double> fromFirstToSecond = graph.getOrDefault(first, new HashMap<>());
    		Map<String, Double> fromSecondToFirst = graph.getOrDefault(second, new HashMap<>());
    		
    		fromFirstToSecond.put(second, values[i]);
    		fromSecondToFirst.put(first, 1/values[i]);
    		
    		graph.put(first, fromFirstToSecond);
    		graph.put(second, fromSecondToFirst);
    	}
    	
    	System.out.println(graph);
    	/**
    	 * 針對每個query做dfs
    	 */
    	double[] results = new double[queries.size()];
    	
    	for (int i = 0; i < queries.size(); i++) {
    		List<String> query = queries.get(i);
    		String start = query.get(0);
    		String end = query.get(1);
    		Map<String, Boolean> visited = new HashMap<>();
    		for (String node : graph.keySet()) visited.put(node, false);
    		dfs(graph, start, end, visited, 1, results, i);
    	}
    	
    	return results;
    }

	private static void dfs(Map<String, Map<String, Double>> graph, String start, String end, Map<String, Boolean> visited, double currResult, double[] results, int index) {
		
		if (!graph.containsKey(start) || !graph.containsKey(end)) { /* 根本無起終點，直接填-1 */
			results[index] = -1;
			return;
		}
		
		if (start.equals(end)) { /* 找到終點了，填入至今的乘積並標示end以造訪 */
			results[index] = currResult;
			visited.put(start, true);
			return;
		}
		
		if (visited.get(start)) { /* 以造訪過的直接回傳 */
			return;
		}
		
		visited.put(start, true);
		
		Map<String, Double> pathFromStart = graph.get(start);
		
		for (String destination : pathFromStart.keySet()) {
			
			dfs(graph, destination, end, visited, currResult*pathFromStart.get(destination), results, index);
		}
		
		if (!visited.get(end)) { /* 若全部訪問完還是沒有找到終點，那表示終點存在，但起點永遠到不了終點 */
			results[index] = -1;
			return;
		}
	}
}

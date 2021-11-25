package DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaystoAddParentheses {

	public static void main(String[] args) {
		
		String s = "2-1-1";
		System.out.println(diffWaysToCompute(s));
		s = "2*3-4*5";
		System.out.println(diffWaysToCompute(s));
	}

    public static List<Integer> diffWaysToCompute(String expression) {
    	
    	Map<String, List<Integer>> dp = new HashMap<String, List<Integer>>();
    	List<Integer> results = dfs(expression, dp);
    	
        return results;
    }

	private static List<Integer> dfs(String expression, Map<String, List<Integer>> dp) {
		
		if (dp.containsKey(expression)) {
			return dp.get(expression);
		}
		
		boolean isPureNum = true;
		List<Integer> results = new ArrayList<Integer>();
		
		for (int i = 0; i < expression.length(); i++) {
			char operation = expression.charAt(i);
			if (!Character.isDigit(operation)) {
				
				isPureNum = false;
				List<Integer> resultForFirst = dfs(expression.substring(0, i), dp);
				List<Integer> resultForSec = dfs(expression.substring(i+1), dp);
				
				for (int f : resultForFirst) {
					for (int s : resultForSec) {
						if (operation == '+') results.add(f+s);
						if (operation == '-') results.add(f-s);
						if (operation == '*') results.add(f*s);
					}
				}
			}
		}
		
		if (isPureNum) {
			results.add(Integer.parseInt(expression));
		}
		
		dp.put(expression, results);
		return results;
	}
}

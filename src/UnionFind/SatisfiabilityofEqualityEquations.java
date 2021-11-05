package UnionFind;

import java.util.HashMap;
import java.util.Map;

public class SatisfiabilityofEqualityEquations {
	
	private static Map<Character, Character> parentMap;
	private static Map<Character, Integer> rank;

	public static void main(String[] args) {
		
		String[] equations = {"a==b","b==c","a==c"};
		String[] equations2 = {"a==b","b!=c","c==a"};

		System.out.println(equationsPossible(equations));
		System.out.println(equationsPossible(equations2));
	}

    public static boolean equationsPossible(String[] equations) {
    	
    	parentMap = new HashMap<>();
    	rank = new HashMap<>();
    	
    	for (String equation : equations) {
    		parentMap.put(equation.charAt(0), equation.charAt(0));
    		parentMap.put(equation.charAt(3), equation.charAt(3));
    		rank.put(equation.charAt(0), 0);
    		rank.put(equation.charAt(3), 0);
    	}
    	
    	for (String equation : equations) {
    		if (equation.charAt(1) == '=') {
				union(equation.charAt(0), equation.charAt(3));
			}
    	}
    	
    	for (String equation : equations) {
    		if (equation.charAt(1) == '!' && findRoot(equation.charAt(0)) == findRoot(equation.charAt(3))) {
				return false;
			}
    	}
    	
        return true;
    }

	private static void union(char char1, char char2) {
		
		char char1Root = findRoot(char1);
		char char2Root = findRoot(char2);
		
		if (char1Root == char2Root) {
			return;
		}
		
		if (rank.get(char1Root) > rank.get(char2Root)) {
			
			parentMap.put(char2Root, char1Root);
			
		} else if (rank.get(char1Root) < rank.get(char2Root)) {
			
			parentMap.put(char1Root, char2Root);
			
		} else {
			
			parentMap.put(char1Root, char2Root);
			rank.put(char2Root, rank.get(char2Root)+1);
			
		}
	}

	private static char findRoot(char char1) {
		
		if (char1 != parentMap.get(char1)) {
			parentMap.put(char1, findRoot(parentMap.get(char1)));
		}
		
		return parentMap.get(char1);
	}
}

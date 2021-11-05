package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	public static void main(String[] args) {
		/**
		 * 給一組字串
		 * 求出所有sub字串組合，且組合中的每個sub字串要為Palindrome(回文)
		 */
		String s = "aab";
		System.out.println(partition(s));

	}

    public static List<List<String>> partition(String s) {
        /**
         * 求所有組合=>dfs
         */
    	List<List<String>> results = new ArrayList<>();
    	List<String> result = new ArrayList<>();
    	
    	dfs(s, 0, result, results);
    	
    	return results;
    }

	private static void dfs(String s, int start, List<String> result, List<List<String>> results) {
		
		if (start >= s.length()) {
			results.add(new ArrayList<>(result));
			return;
		}
		
		for (int i = start; i < s.length(); i++) {
			/**
			 * 從start開始往後看，
			 * 例如a是回文=>dfs a後面，aa也是回文=>dfs aa
			 */
			String curSubString = s.substring(start, i+1);
			
			if (isPalindrome(curSubString)) {
				result.add(curSubString);
				dfs(s, i+1, result, results);
				result.remove(result.size()-1);
			}
		}
	}

	private static boolean isPalindrome(String curSubString) {
		
		int left = 0;
		int right = curSubString.length()-1;
		
		while (left < right) {
			
			if (curSubString.charAt(left) != curSubString.charAt(right)) {
				return false;
			}
			left ++;
			right --;
		}
		
		return true;
	}
}

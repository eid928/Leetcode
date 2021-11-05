package DepthFirstSearch;
import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

	public static void main(String[] args) {
		/**
		 * 給定一個整數n＝括號數量
		 * 回傳全部合法的括號組合
		 */
		System.out.println(generateParenthesis(2));
		System.out.println(generateParenthesis(3));
	}
    public static List<String> generateParenthesis(int n) {
        
    	/* 以dfs recurstion來做 */
    	List<String> ans = new ArrayList<>();
    	dfs(ans, "", 0, 0, n);
    	
    	return ans;
    }
    
	private static void dfs(List<String> ans, String currString, int openCount, int closeCount, int n) {
		
		if (currString.length() == n * 2) { /* 當這條路線的字串已達長度，加進list中 */
			ans.add(currString);
			return;
		}
		
		if (openCount < n) { /* 先加左括號 */
			
			dfs(ans, currString + "(", openCount+1, closeCount, n);
		}
		if (closeCount < openCount) {
			
			dfs(ans, currString + ")", openCount, closeCount+1, n);
		}
	}
}

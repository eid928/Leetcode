package DepthFirstSearch;

import java.util.Arrays;

public class LetterTilePossibilities {

	public static void main(String[] args) {
		/**
		 * 給定一組由大寫字母組成的字串
		 * 請求出所有組合以及其排列
		 */
		System.out.println(numTilePossibilities("AAB")); // 8: "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
		System.out.println(numTilePossibilities("AAABBC"));
	}
	
	private static int result;

    public static int numTilePossibilities(String tiles) {
    	/**
    	 * dfs
    	 * 其實就是有重複的permutation
    	 * 但因為不一定要全部選完，總結果應該會是Pn取1 + Pn取2 + Pn取3 + ... + Pn取n
    	 * 畫成樹狀圖的話
    	 * 就是經過的所有結果都是可以的答案，並非只計算葉節點
    	 */
        
    	boolean[] visited = new boolean[tiles.length()];
    	result = 0;
    	StringBuilder sb = new StringBuilder();
    	char[] charArray = tiles.toCharArray();
    	Arrays.sort(charArray);
    	for (char ch : charArray) {
    		sb.append(ch);
    	}
    	dfs(sb.toString(), "", visited);
    	
    	return result;
    }

	private static void dfs(String tiles, String curString, boolean[] visited) {
		
		if (curString.length() > tiles.length()) {
			return;
		}
		
		if (curString.length() > 0) { // 經過的所有結果都是可以的答案，並非只計算葉節點
			result++;
		}
		
		for (int i = 0; i < tiles.length(); i++) {
			
			char curChar = tiles.charAt(i);
			if (visited[i]) {
				continue;
			}
			if (i-1 >= 0 && curChar == tiles.charAt(i-1) && !visited[i-1]) {
				continue;
			}
			visited[i] = true;
			dfs(tiles, curString+curChar, visited);
			visited[i] = false;
		}
	}
}

package DepthFirstSearch;


public class WorldSearch {

	public static void main(String[] args) {

		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String word = "ABCCED";
		char[][] board2 = {{'A','B'}};
		String word2 = "BA";
		char[][] board3 = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
		String word3 = "AAB";
		System.out.println(exist(board, word));
		System.out.println(exist(board2, word2));
		System.out.println(exist(board3, word3));
	}

	public static boolean exist(char[][] board, String word) {
        
		for (int m = 0; m < board.length; m++) {
			for (int n = 0; n < board[m].length; n++) {
				System.out.println("start DFS");
				if (dfs(board, m, n, word, 0)) {
					return true;
				}
			}
		}
		
		return false;
    }
	
	public static boolean dfs(char[][] board, int m, int n, String word, int i) {
		
		if (m < 0 || n < 0 || m > board.length-1 || n > board[m].length-1) { /* 超出邊界 */
			return false;
		}
		
		if (board[m][n] == word.charAt(i) && i == word.length()-1) { /* 最後一個字元 */
			return true;
		}
		
		if (board[m][n] == word.charAt(i)) {
			board[m][n] = ' '; /* 標注這個地方已經用過 */
			if (dfs(board, m+1, n, word, i+1) || dfs(board, m, n+1, word, i+1) || dfs(board, m-1, n, word, i+1) || dfs(board, m, n-1, word, i+1)) {
				/* 繼續往四個方向DFS */
				return true;
			} else {
				board[m][n] = word.charAt(i); /* 如果DFS後不符合，回復成標注前 */
			}
		}
		
		return false;
	}
}

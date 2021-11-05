package DepthFirstSearch;

public class SurroundedRegions {

	public static void main(String[] args) {
		/**
		 * 給定一個grid，上有X or O
		 * 請將所有被X包圍的O區域翻轉為X
		 * 被包圍的定義為該區域的上下左右四方都有X
		 * 但邊界只有三個邊的不算被包圍
		 */
		char[][] board = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
		solve(board);
		System.out.println();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]+",");
			}
			System.out.println();
		}
	}

    public static void solve(char[][] board) {
        /**
         * 先從邊界找O，找到後進行dfs將相鄰的O全都轉為$
         * 之後剩下的O即是被包圍的，轉成X
         * 而$轉成O
         */
    	int m = board.length;
    	int n = board[0].length;
    	
    	for (int i = 0; i < n; i++) if(board[0][i] == 'O') dfs(board, 0, i);
    	for (int i = 0; i < n; i++) if(board[m-1][i] == 'O') dfs(board, m-1, i);
    	for (int i = 0; i < m; i++) if(board[i][0] == 'O') dfs(board, i, 0);
    	for (int i = 0; i < m; i++) if(board[i][n-1] == 'O') dfs(board, i, n-1);
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
    			if (board[i][j] == '$') {
    				board[i][j] = 'O';
				}
    		}
    	}
    }

	private static void dfs(char[][] board, int i, int j) {
		
		if (i >= board.length || j >= board[0].length || i < 0 || j < 0 || board[i][j] == 'X' || board[i][j] == '$') {
			return;
		}
		
		board[i][j] = '$';
		
		dfs(board, i+1, j);
		dfs(board, i-1, j);
		dfs(board, i, j+1);
		dfs(board, i, j-1);
	}
}

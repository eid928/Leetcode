package Special;

public class ValidSudoku {

	public static void main(String[] args) {
		System.out.println('8' - '1');

	}
    public static boolean isValidSudoku(char[][] board) {
    	
    	boolean[][] rowFlag = new boolean[9][9]; /* 紀錄第i列的數字j是否有出現過，例如rowFlag[0][8]=true表示第一列已經出現過9 */
    	boolean[][] colFlag = new boolean[9][9]; /* 同上，紀錄行 */
    	boolean[][] celFlag = new boolean[9][9]; /* 同上，紀錄九宮格 */
    	
    	int m = board.length;
    	int n = board[0].length;
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			
    			if (board[i][j] == '.') {
					continue;
				}
    			int theNum = board[i][j] - '1';
    			int cell = (i / 3) + (j / 3) * 3;
    			
    			if (rowFlag[i][theNum] || colFlag[j][theNum] || celFlag[cell][theNum]) {
					return false;
				}
    			
    			rowFlag[i][theNum] = true;
    			colFlag[j][theNum] = true;
    			celFlag[cell][theNum] = true;
    		}
    	}
    	
        return true;
    }
}

package Special;

public class SetZeroes {

	public static void main(String[] args) {
		
		int[][] matrix1 = {{1,1,1},{1,0,1},{1,1,1}};
		int[][] matrix2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
		
		setZeroes(matrix1);
		setZeroes(matrix2);
		printMatrix(matrix1);
		printMatrix(matrix2);
	}

	public static void setZeroes(int[][] matrix) {
        /**
         * 空間複雜度constant
         * step1: 掃第一行第一列，有0就在該flag設為true
         * step2: 掃除了第一行第一列的其他部分，若有0，則把對應的第一行第一列設為0（統一把0的資訊儲存在第一行第一列）
         * step3: 再次掃除了第一行第一列的其他部分，若對應的第一行第一列是0，則那格設為0
         * step4: 最後根據flag，把第一行第一列設為0
         */
		int m = matrix.length;
		int n = matrix[0].length;
		
		boolean colFlag = false;
		boolean rowFlag = false;
		
		/* step1 */
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) colFlag = true;
		}
		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) rowFlag = true;
		}
		
		/* step2 */
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		
		/* step3 */
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		
		/* step4 */
		if (colFlag) {
			for (int i = 0; i < m; i++) matrix[i][0] = 0;
		}
		if (rowFlag) {
			for (int i = 0; i < n; i++) matrix[0][i] = 0;
		}
    }
	
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println("");
		}
	}
}

package Special;

public class RotateImage {

	public static void main(String[] args) {

		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		rotate(matrix);
		printMatrix(matrix);
	}

	public static void rotate(int[][] matrix) {
        transpose(matrix); /* 沿著對角線交換 */
        reflect(matrix); /* 左右鏡像交換 */
    }
	
	public static void transpose(int [][] matrix) { /* 沿著對角線交換 */
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
	
	public static void reflect(int [][] matrix) { /* 左右鏡像交換 */
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length/2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][matrix[i].length - 1 - j];
				matrix[i][matrix[i].length - 1 - j] = temp;
			}
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

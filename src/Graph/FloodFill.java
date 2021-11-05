package Graph;

import java.util.ArrayList;
import java.util.List;

public class FloodFill {
	
	private static List<int[]> directions = new ArrayList<>();
	
	static {
		directions.add(new int[] {1,0});
		directions.add(new int[] {-1,0});
		directions.add(new int[] {0,1});
		directions.add(new int[] {0,-1});
	}

	public static void main(String[] args) {
		/**
		 * just similiar to number of island
		 */
		int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
		int sr = 1;
		int sc = 1;
		int newColor = 2;
		
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				System.out.print(image[i][j]+",");
			}
			System.out.println("");
		}
		
		image = floodFill(image, sr, sc, newColor);

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[i].length; j++) {
				System.out.print(image[i][j]+",");
			}
			System.out.println("");
		}
	}

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    	
    	int target = image[sr][sc];
    	dfs(image, sr, sc, newColor, target);
    	
        return image;
    }

	private static void dfs(int[][] image, int x, int y, int newColor, int target) {
		
		if (x < 0 || y < 0 || x >= image.length || y >= image[0].length) return;
		
		if (image[x][y] == newColor) return;
		
		if (image[x][y] != target) return;
		
		image[x][y] = newColor;
		
		for (int[] direction : directions) {
			dfs(image, x+direction[0], y+direction[1], newColor, target);
		}
	}
}

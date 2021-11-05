package Graph;

import java.util.ArrayList;
import java.util.List;

public class PossibleBipartition {

	public static void main(String[] args) {
		/**
		 * 給定一正整數代表總人數
		 * 以及一組array代表互相討厭的組合
		 * 請問是否能將全部人分成兩組，兩組中各自沒有互相討厭的人？
		 * 兩組人數不限
		 */
		int n = 4;
		int[][] dislikes = {{1,2},{1,3},{2,4}};

		System.out.println(possibleBipartition(n, dislikes));
		
		n = 3;
		dislikes = new int[][] {{1,2},{1,3},{2,3}};
		
		System.out.println(possibleBipartition(n, dislikes));
	}
	
	private static boolean isConflict;

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        /**
         * 和Is Graph Bipartite一樣是著色問題
         * 先將dislike建成正規的graph後去做著色dfs
         */
    	isConflict = false;
    	
    	List<List<Integer>> graph = new ArrayList<>();
    	for (int i = 0; i < n+1; i++) {
    		graph.add(new ArrayList<>());
    	}
    	for (int i = 0; i < dislikes.length; i++) {
    		graph.get(dislikes[i][0]).add(dislikes[i][1]);
    		graph.get(dislikes[i][1]).add(dislikes[i][0]);
    	}
    	
    	int[] colors = new int[n+1];
    	
    	for (int i = 1; i < graph.size(); i++) {
    		if (colors[i] == 0) {
				dfs(i, colors, graph, 1);
			}
    		if (isConflict) return false;
    	}
    	
    	return true;
    }

	private static void dfs(int start, int[] colors, List<List<Integer>> graph, int colorToDraw) {
		
		if (isConflict) {
			return;
		}
		
		if (colors[start] == colorToDraw) {
			return;
		}
		
		if (colors[start] == -colorToDraw) {
			isConflict = true;
			return;
		}
		
		colors[start] = colorToDraw;
		for (Integer neighbor : graph.get(start)) {
			dfs(neighbor, colors, graph, -colorToDraw);
		}
	}
}

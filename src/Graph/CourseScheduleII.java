package Graph;

import java.util.ArrayList;
import java.util.List;

public class CourseScheduleII {

	public static void main(String[] args) {
		/**
		 * 重要=>Topological Sort
		 * 跟CourseSchedule一樣
		 * 給定一個int，表示你總共要上的課的數量
		 * 上的課分別以0 ~ int-1表示
		 * 以及一個先修表
		 * 先修表中，一個先修條件{int1, int2}代表要修int1的課你必須先修int2的課
		 * 請回傳修課順序
		 * 若有多種可能，回傳任一種，若不可能完成課程，則回傳空array
		 */
		int numCourses = 4;
		int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
		int[] order = findOrder(numCourses, prerequisites);
		for (int i : order) {
			System.out.print(i+",");
		}
	}
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
    	/**
    	 * 一樣做graph
    	 * 單向graph做dfs
    	 * graph的index儲存其課程能解鎖的課程
    	 * 例如: 0->1->2，graph=[[1], [2], []]
    	 * 基本架構跟CourseSchedule一樣，但要用一個list來紀錄課程order
    	 * 這其實就是Topological Sort
    	 */
    	List<List<Integer>> graph = new ArrayList<>();
    	for (int i = 0; i < numCourses; i++) {
    		graph.add(new ArrayList<>());
    	}
    	for (int i = 0; i < prerequisites.length; i++) {
    		graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
    	}
    	
		int[] visited = new int[numCourses];
    	List<Integer> order = new ArrayList<>();
    	
    	for (int i = 0; i < graph.size(); i++) {
    		if (!dfs(graph, visited, order, i)) return new int[0];
    	}
    	
    	int[] orderArray = new int[order.size()];
    	for (int i = 0; i < orderArray.length; i++) {
			orderArray[i] = order.get(order.size()-1-i);
		}
    	
        return orderArray;
    }
    
	private static boolean dfs(List<List<Integer>> graph, int[] visited, List<Integer> order, int start) {
		
		if (visited[start] == 1) { /* finish */
			return true;
		}
		
		if (visited[start] == -1) { /* visited */
			return false;
		}
		
		visited[start] = -1;
		
		for (int i = 0; i < graph.get(start).size(); i++) {
			if (!dfs(graph, visited, order, graph.get(start).get(i))) return false;
		}
		/**
		 * 重點！！
		 * 該點finished(1)才能加，舉例
		 * 0->1->3
		 * |->2--^
		 * 3需要先修1,2
		 * 若visited的狀態(-1)就先加，2就會在3之後
		 * 而finish的順序是3->1->2->0
		 * 反轉後即topological sort
		 * http://alrightchiu.github.io/SecondRound/graph-li-yong-dfsxun-zhao-dagde-topological-sorttuo-pu-pai-xu.html
		 * topological sort = finish由大到小的順序
		 */
		order.add(start);
		visited[start] = 1;
		
		return true;
	}
}

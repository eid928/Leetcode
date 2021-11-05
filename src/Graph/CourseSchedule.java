package Graph;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {

	public static void main(String[] args) {
		/**
		 * 給定一個int，表示你總共要上的課的數量
		 * 上的課分別以0 ~ int-1表示
		 * 以及一個先修表
		 * 先修表中，一個先修條件{int1, int2}代表要修int1的課你必須先修int2的課
		 * 請計算是否能順利修完課
		 */
		int numCourses = 2;
		int[][] prerequisites = {{1,0},{0,1}};
		int[][] prerequisites2 = {{1,0}};
		
		System.out.println(canFinish(numCourses, prerequisites));
		System.out.println(canFinish(numCourses, prerequisites2));
	}
	
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        
    	/**
    	 * 單向graph做dfs，找有沒有cycle
    	 * graph的index儲存其課程能解鎖的課程
    	 * 例如: 0->1->2，graph=[[1], [2], []]
    	 */
    	List<List<Integer>> graph = new ArrayList<>();
    	for (int i = 0; i < numCourses; i++) {
    		graph.add(new ArrayList<>());
    	}
    	for (int i = 0; i < prerequisites.length; i++) {
    		graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
    	}
    	
    	int[] visited = new int[numCourses];
    	
    	for (int i = 0; i < graph.size(); i++) {
    		/* 以每堂課為出發點去走dfs看有沒有cycle */
    		if(!dfs(graph, visited, i)) return false;
    	}
    	
    	return true;
    }

	private static boolean dfs(List<List<Integer>> graph, int[] visited, int start) {
		
		if (visited[start] == 1) {
			/**
			 * 1代表這堂課已經完整走過dfs沒有問題(finished)，直接return true
			 * 例如1->0->2，我已經從0走完dfs沒問題，則從1出發的時候碰到0，就確保這條路沒問題
			 */
			return true;
		}
		if (visited[start] == -1) {
			/**
			 * -1代表在這次dfs中已經走過，但整條路線尚未結束
			 * 若再次遇到-1代表碰到了cycle
			 */
			return false;
		}
		visited[start] = -1;
		
		for (int i = 0; i < graph.get(start).size(); i++) {
			if(!dfs(graph, visited, graph.get(start).get(i))) {
				return false;
			}
		}
		
		visited[start] = 1;
		
		return true;
	}
}

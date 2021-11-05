package Graph;

import java.util.ArrayList;
import java.util.List;

public class KeysandRooms {

	public static void main(String[] args) {
		/**
		 * 給一個二維list，代表一個個房間，房間內有鑰匙
		 * 鑰匙的數字代表可打開的房間index
		 * 只有第一間(room0)
		 * 請回傳是否能開啟所有房間
		 */
		
		List<Integer> room0 = new ArrayList<>();
		room0.add(1); room0.add(3);
		
		List<Integer> room1 = new ArrayList<>();
		room1.add(3); room1.add(0); room1.add(1);

		List<Integer> room2 = new ArrayList<>();
		room2.add(2);
		
		List<Integer> room3 = new ArrayList<>();
		room3.add(0);
		
		List<List<Integer>> rooms = new ArrayList<>();
		rooms.add(room0);
		rooms.add(room1);
		rooms.add(room2);
		rooms.add(room3);
		
		System.out.println(canVisitAllRooms(rooms));
	}
	
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
    	/**
    	 * 很明顯就是dfs
    	 * 一開始只有0號房可以進去，於是從0作為起點
    	 * 用一個array來紀律是否造訪過
    	 */
    	boolean[] visited = new boolean[rooms.size()];
    	
    	dfs(rooms, visited, 0);
    	
    	for (int i = 0; i < visited.length; i++) {
    		if (!visited[i]) {
				return false;
			}
    	}
    	
        return true;
    }

	private static void dfs(List<List<Integer>> rooms, boolean[] visited, int start) {
		/**
		 * 一進來房間就標註為visited
		 * 之後再根據鑰匙去造訪還沒到過的房間
		 * 若都遭訪過or沒有鑰匙了，則會直接結束完成dfs
		 */
		visited[start] = true;
		
		for (int i = 0; i < rooms.get(start).size(); i++) {
			
			Integer key = rooms.get(start).get(i);
			if (!visited[key]) {
				dfs(rooms, visited, key);
			}
		}
	}
}

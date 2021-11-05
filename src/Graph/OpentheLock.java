package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpentheLock {

	public static void main(String[] args) {
		/**
		 * 有一個四位數的密碼鎖
		 * 現在要從0000開始，將密碼鎖轉成正確的密碼target
		 * 然而有一組deadends array，一旦四位數密碼變成deadends中出現過的樣子，密碼鎖就會鎖住無法使用
		 * 請求出從0000開始到轉成正確的密碼target，至少需要幾步？
		 * 途中不可碰到deadends中的密碼
		 */
		String[] deadends = {"0201","0101","0102","1212","2002"};
		String target = "0202";
		
		System.out.println(openLock(deadends, target));
		
		String[] deadends2 = {"8887","8889","8878","8898","8788","8988","7888","9888"};
		String target2 = "8888";
		
		System.out.println(openLock(deadends2, target2));
	}

    public static int openLock(String[] deadends, String target) {
    	/**
    	 * 此題本質上就是走迷宮
    	 * 與一般走迷宮，一格的下一步為上下左右不同
    	 * 從0000出發，一共有8個方向可供選擇(每個位數各自上下轉動一格)
    	 * 而deadends即為牆壁無法通過
    	 * 求最短路徑=>BFS
    	 */
    	Set<String> visited = new HashSet<>();
    	Set<String> deadSet = new HashSet<>();
    	
    	for (String deadend : deadends) {
    		if (deadend.equals("0000")) {
				return -1;
			}
    		deadSet.add(deadend);
    	}
    	
    	Queue<String> queue = new LinkedList<>();
    	queue.add("0000");
    	visited.add("0000");
    	int path = 0;
    	
    	while (!queue.isEmpty()) {
    		
    		int sizeOfTheLayer = queue.size();
    		/**
    		 * 一次處理同一圈的(相同深度的)
    		 */
    		
    		for (int i = 0; i < sizeOfTheLayer; i++) {
    			
    			String currentString = queue.poll();
    			if (currentString.equals(target)) {
					return path;
				}
    			addNeighbors(currentString, queue, visited, deadSet);
    		}
    		
    		path += 1;
    	}
    	
        return -1;
    }

	public static void addNeighbors(String currentString, Queue<String> queue, Set<String> visited, Set<String> deadSet) {
		/**
		 * 將目前位置的八個鄰居加進queue中並標示為visited
		 */
		for (int i = 0; i < currentString.length(); i++) {
			
			char[] charArray = currentString.toCharArray();
			charArray[i] = currentString.charAt(i) == '9'? '0' : (char) (currentString.charAt(i) + 1);
			StringBuffer sb = new StringBuffer();
			for (char character : charArray) sb.append(character);
			
			if (!visited.contains(sb.toString()) && !deadSet.contains(sb.toString())) {
				queue.add(sb.toString());
				visited.add(sb.toString());
			}
			
			
			charArray = currentString.toCharArray();
			charArray[i] = currentString.charAt(i) == '0'? '9' : (char) (currentString.charAt(i) - 1);
			sb = new StringBuffer();
			for (char character : charArray) sb.append(character);
			
			if (!visited.contains(sb.toString()) && !deadSet.contains(sb.toString())) {
				queue.add(sb.toString());
				visited.add(sb.toString());
			}
		}
	}
}

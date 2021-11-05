package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import datastructure.TreeNode;

public class VerticalOrderTraversalofaBinaryTree {

	public static void main(String[] args) {
		/**
		 * 給定一顆二元樹
		 * 請回傳VerticalOrderTraversal list
		 * 規則：
		 * column由左到右
		 * level由上到下
		 * 相同座標的以值的大小來排序
		 */
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		System.out.println(verticalTraversal(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		System.out.println(verticalTraversal(root));
	}
	
	private static int minColumn;
	
	private static int maxColumn;

	public static List<List<Integer>> verticalTraversal(TreeNode root) {
    	/**
    	 * traverse紀錄座標不難
    	 * 但處理順序十分麻煩
    	 * 首先雖然相同座標的按照值來排序，但仍然要照level排序
    	 * 例如有一個column是
    	 * 1
    	 * |
    	 * 8
    	 * |
    	 * 7,6
    	 * 那這個column是1,8,6,7，所以不能一起sort
    	 * 故在traverse時，同時要紀錄column，也要紀錄level
    	 * 用一個雙層map來做
    	 * map.get(column).get(level) => 同個座標的所有數字(list) => 這個最後要排序
    	 * 
    	 * 再來，要照column以及level的順序
    	 * 有個性質是，coulumn絕對是連續的，
    	 * 如果最小column是-2，最大是2，則column -2~2的整數都存在，所以可以紀錄最大和最小column
    	 * 到時候直接從map裡一個一個取出
    	 * 
    	 * 而level則並非連續，同個column中，level很有可能是亂跳的，且即使是pre-ordertraverse，level也不會照順序加入
    	 * 例如同個column，右邊有個node在較淺的位置，但會較晚才traverse到
    	 * 所以則是取出來做成list後再次sort
    	 * 
    	 * 時間複雜度：
    	 * traverse所有node:O(N)
    	 * 排序同座標：同座標最多只可能有兩個，所以視為constant
    	 * 排序同column中的level，worst case：O(hlogh)?
    	 */
    	List<List<Integer>> results = new ArrayList<>();
    	LinkedHashMap<Integer, Map<Integer, List<Integer>>> cordinationMap = new LinkedHashMap<>();
    	minColumn = Integer.MAX_VALUE;
    	maxColumn = Integer.MIN_VALUE;
    	
    	traverse(root, cordinationMap, 0, 0);
    	
    	for (int column = minColumn; column <= maxColumn; column++) {
    		
    		Map<Integer, List<Integer>> levelMap = cordinationMap.get(column);
    		List<Integer> listOfColumn = new ArrayList<>();
    		
    		List<Integer> levelList = new ArrayList<>(levelMap.keySet());
    		Collections.sort(levelList);
			
    		for (int level : levelList) {
    			List<Integer> list = levelMap.get(level);
    			Collections.sort(list);
    			listOfColumn.addAll(list);
    		}
    		
    		results.add(listOfColumn);
    	}
    	
        return results;
    }

	private static void traverse(TreeNode node, LinkedHashMap<Integer, Map<Integer, List<Integer>>> cordinationMap, int curColumn, int curLevel) {
		
		if (node == null) {
			return;
		}
		
		minColumn = Math.min(minColumn, curColumn);
		maxColumn = Math.max(maxColumn, curColumn);
		
		Map<Integer, List<Integer>> levelMap = cordinationMap.getOrDefault(curColumn, new LinkedHashMap<>());
		List<Integer> cordinationList = levelMap.getOrDefault(curLevel, new ArrayList<>());
		cordinationList.add(node.val);
		levelMap.put(curLevel, cordinationList);
		cordinationMap.put(curColumn, levelMap);
		
		traverse(node.left, cordinationMap, curColumn-1, curLevel+1);
		traverse(node.right, cordinationMap, curColumn+1, curLevel+1);
	}
}

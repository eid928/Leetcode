package Tree;

import java.util.ArrayList;
import java.util.List;

public class NaryTreeLevelOrderTraversal {
	
	public static class Node {
    	
    	public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

	public static void main(String[] args) {
		/**
		 * 給定一多元樹
		 * 求出levelorderlist
		 */
		Node root = new Node(1);
		root.children = new ArrayList<>();
		root.children.add(new Node(3));
		root.children.add(new Node(2));
		root.children.add(new Node(4));
		
		root.children.get(0).children = new ArrayList<>();
		root.children.get(0).children.add(new Node(5));
		root.children.get(0).children.add(new Node(6));
		
		System.out.println(levelOrder(root));
	}

    public static List<List<Integer>> levelOrder(Node root) {
        
    	List<List<Integer>> results = new ArrayList<>();
    	levelOrder(root, 0, results);
    	
    	return results;
    }

	private static void levelOrder(Node root, int level, List<List<Integer>> results) {
		
		if (root ==  null) {
			return;
		}
		if (level >= results.size()) {
			results.add(new ArrayList<>());
		}
		List<Integer> listInThisLevel = results.get(level);
		listInThisLevel.add(root.val);
		
		for (int i = 0; root.children != null && i < root.children.size(); i++) {
			levelOrder(root.children.get(i), level+1, results);
		}
	}
}

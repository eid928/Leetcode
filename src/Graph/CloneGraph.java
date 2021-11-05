package Graph;
import java.util.HashMap;
import java.util.Map;

import datastructure.Node;

public class CloneGraph {

	public static void main(String[] args) {
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		
		node1.neighbors.add(node2);
		node1.neighbors.add(node4);

		node2.neighbors.add(node1);
		node2.neighbors.add(node3);
		
		node3.neighbors.add(node2);
		node3.neighbors.add(node4);

		node4.neighbors.add(node1);
		node4.neighbors.add(node3);
		
		System.out.println(node1);
		System.out.println(node2);
		System.out.println(node3);
		System.out.println(node4);
		
		System.out.println(cloneGraph(node1));
		System.out.println(cloneGraph(node1).neighbors);
	}
    public static Node cloneGraph(Node node) {
    	
    	Map<Node, Node> cloneMap = new HashMap<>();
    	/**
    	 * key: 原始的node，檢查我們是否有複製這個node了
    	 * value：原始node對應的複製node
    	 */
    	Node cloneNode = dfs(node, cloneMap);
    	
        return cloneNode;
    }
    
	private static Node dfs(Node node, Map<Node, Node> cloneMap) {
		
		if (node == null) {
			return null;
		}
		
		if (!cloneMap.containsKey(node)) {
			cloneMap.put(node, new Node(node.val));
		} else {
			return cloneMap.get(node);
		}
		
		for (Node neighborNode : node.neighbors) {
			Node cloneNeighborNode = dfs(neighborNode, cloneMap);
			cloneMap.get(node).neighbors.add(cloneNeighborNode);
		}
		
		return cloneMap.get(node);
	}
}

package DepthFirstSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class SmallestStringWithSwaps {

	public static void main(String[] args) {
		/**
		 * 給定一組字串以及一組pair list
		 * pair list中的每對pair表示字串中的兩個index的字母可以互換，且可以無限使用
		 * 在所有經過交換所組成的字串中，請回傳排序最大的那個
		 */
		List<List<Integer>> pairs = new ArrayList<>();
		List<Integer> pair1 = new ArrayList<>();
		pair1.add(0);
		pair1.add(3);
		List<Integer> pair2 = new ArrayList<>();
		pair2.add(1);
		pair2.add(2);
		pairs.add(pair1);
		pairs.add(pair2);
		System.out.println(pairs);
		System.out.println(smallestStringWithSwaps("dcab", pairs));
		
		List<Integer> pair3 = new ArrayList<>();
		pair3.add(0);
		pair3.add(2);
		pairs.add(pair3);
		System.out.println(pairs);
		System.out.println(smallestStringWithSwaps("dcab", pairs));
		
		pairs = new ArrayList<>();
		pair1 = new ArrayList<>();
		pair1.add(0);
		pair1.add(1);
		pair2 = new ArrayList<>();
		pair2.add(1);
		pair2.add(2);
		pairs.add(pair1);
		pairs.add(pair2);
		System.out.println(pairs);
		System.out.println(smallestStringWithSwaps("cba", pairs));
	}
	
	public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		/**
		 * dfs
		 * 先建立無向graph之後對graph進行遍歷
		 * 找出哪幾個index互相相連，以及那幾個index對應的字母是哪些
		 * 相連的組別，其內部排序後填回對應的位置上，就是該組別能提供排序最大的排法
		 * 將所有組別排完即是答案
		 * 但效率不是很好，可用union find，可見union find package
		 */
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < pairs.size(); i++) {
			int node1 = pairs.get(i).get(0);
			int node2 = pairs.get(i).get(1);
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		Set<Integer> visited = new HashSet<>();
		List<List<Integer>> connectGroups = new ArrayList<>();
		List<List<Character>> connectChars = new ArrayList<>();
		
		for (int i = 0; i < s.length(); i++) {
			List<Integer> connectGroup = new ArrayList<>();
			List<Character> connectChar = new ArrayList<>();
			dfs(graph, i, visited, connectGroup, connectChar, s);
			Collections.sort(connectGroup);
			Collections.sort(connectChar);
			if (!connectGroup.isEmpty()) {
				connectGroups.add(connectGroup);
				connectChars.add(connectChar);
			}
		}
		
		char[] charArr = new char[s.length()];
		
		for (int i = 0; i < connectGroups.size(); i++) {
			for (int j = 0; j < connectGroups.get(i).size(); j++) {
				charArr[connectGroups.get(i).get(j)] = connectChars.get(i).get(j);
			}
		}
		
		StringBuffer sb = new StringBuffer();
		
		for (Character ch : charArr) {
			sb.append(ch);
		}
		
		return sb.toString();
	}
	
	private static void dfs(List<List<Integer>> graph, int start, Set<Integer> visited, List<Integer> connectGroup, List<Character> connectChar, String s) {
		
		if (visited.contains(start)) {
			return;
		}
		
		visited.add(start);
		connectGroup.add(start);
		connectChar.add(s.charAt(start));
		
		for (int neighbor : graph.get(start)) {
			dfs(graph, neighbor, visited, connectGroup, connectChar, s);
		}
	}

	public static String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        /**
         * 暴力遍歷所有可能
         * 超時
         */
    	Set<String> visited = new HashSet<>();
    	PriorityQueue<String> pq = new PriorityQueue<>();
    	dfs(s, pairs, visited, pq);
    	System.out.println(visited);
    	
    	return pq.peek();
    }

	private static void dfs(String s, List<List<Integer>> pairs, Set<String> visited, PriorityQueue<String> pq) {
		
		if (visited.contains(s)) {
			return;
		}
		
		visited.add(s);
		pq.add(s);
		
		for (int i = 0; i < pairs.size(); i++) {
			
			List<Integer> pair = pairs.get(i);
			String newString = swap(s, pair);
			dfs(newString, pairs, visited, pq);
		}
	}

	private static String swap(String s, List<Integer> pair) {
		
		char[] charArr = s.toCharArray();
		char tmp = charArr[pair.get(0)];
		charArr[pair.get(0)] = charArr[pair.get(1)];
		charArr[pair.get(1)] = tmp;
		String newString = "";
		for (char ch : charArr) {
			newString += ch;
		}
		
		return newString;
	}
}

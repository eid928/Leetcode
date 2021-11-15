package UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private static int[] parents;
	private static int[] ranks;
	
	public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		/**
		 * 用並查集分類找出相連的index與其對應的字母
		 * 每一組字母在排序後各自塞回去原本的位置
		 */
		parents = new int[s.length()];
		ranks = new int[s.length()];
		
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		for (List<Integer> pair : pairs) {
			union(pair.get(0), pair.get(1));
		}
		
		Map<Integer, List<Integer>> connectIndexs = new HashMap<>();
		Map<Integer, List<Character>> connectChars = new HashMap<>();
		
		for (int i = 0; i < parents.length; i++) {
			int parent = find(i);
			List<Integer> connectIndex = connectIndexs.getOrDefault(parent, new ArrayList<Integer>());
			List<Character> connectChar = connectChars.getOrDefault(parent, new ArrayList<Character>());
			connectIndex.add(i);
			connectChar.add(s.charAt(i));
			connectIndexs.put(parent, connectIndex);
			connectChars.put(parent, connectChar);
		}
		
		char[] charArr = new char[s.length()];
		
		for (int parent : connectIndexs.keySet()) {
			
			List<Integer> connectIndex = connectIndexs.get(parent);
			List<Character> connectChar = connectChars.get(parent);
			Collections.sort(connectChar);
			for (int i = 0; i < connectIndex.size(); i++) {
				charArr[connectIndex.get(i)] = connectChar.get(i); 
			}
		}
		
		StringBuffer sb = new StringBuffer();
		
		for (Character ch : charArr) {
			sb.append(ch);
		}
		
		return sb.toString();
	}

	private static void union(Integer x, Integer y) {
		
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX == rootY) {
			return;
		}
		
		if (ranks[rootX] > ranks[rootY]) {
			parents[rootY] = rootX;
		} else if (ranks[rootY] > ranks[rootX]) {
			parents[rootX] = rootY;
		} else {
			parents[rootY] = rootX;
			ranks[rootX]++;
		}
	}

	private static int find(Integer x) {
		
		if (parents[x] != x) {
			parents[x] = find(parents[x]);
		}
		
		return parents[x];
	}
}

package UnionFind;

import java.util.HashMap;
import java.util.Map;

public class MinimizeHammingDistanceAfterSwapOperations {

	public static void main(String[] args) {
		/**
		 * 給定三組array: source, target, allowedSwaps
		 * allowedSwaps顯示了哪些index可以隨意交換element
		 * 請計算出
		 * 將source隨意交換後，與target之間最接近的HammingDistance
		 * 例如1,2,3,4 => 2,1,4,3
		 * 2,1,4,3和2,1,4,5之間的different為1即為HammingDistance
		 */
		int[] source = {1,2,3,4};
		int[] target = {2,1,4,5};
		int[][] allowedSwaps = {{0,1},{2,3}};
		
		System.out.println(minimumHammingDistance(source, target, allowedSwaps));
	}
	
	private static int[] parents;
	private static int[] ranks;

    public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
    	/**
    	 * union find
    	 * 分完組後來建立map紀錄每個element的count
    	 * 例如i=0的時候
    	 * source[i]為1
    	 * target[i]為2
    	 * 此時
    	 * 1的count=1
    	 * 2的count=-1 (source欠target一個2)
    	 * 最後再來計算盈餘與積欠的總量/2即是HammingDistance
    	 */
    	parents = new int[source.length];
    	ranks = new int[source.length];
    	
    	for (int i = 0; i < source.length; i++) {
    		parents[i] = i;
    	}
    	
    	for (int[] allowedSwap : allowedSwaps) {
    		int member1 = allowedSwap[0];
    		int member2 = allowedSwap[1];
    		union(member1, member2);
    	}
    	
    	Map<Integer, Map<Integer, Integer>> sourceElementCountGroups = new HashMap<>();
    	
    	for (int i = 0; i < source.length; i++) {
    		
    		int parent = find(i);
    		Map<Integer, Integer> sourceElementCount = sourceElementCountGroups.getOrDefault(parent, new HashMap<>());
    		sourceElementCount.put(source[i], sourceElementCount.getOrDefault(source[i], 0)+1);
    		sourceElementCount.put(target[i], sourceElementCount.getOrDefault(target[i], 0)-1);
    		
    		sourceElementCountGroups.put(parent, sourceElementCount);
    	}
    	
    	System.out.println(sourceElementCountGroups);
    	
    	int diffCount = 0;
    	
    	for (int parent : sourceElementCountGroups.keySet()) {
    		
    		Map<Integer, Integer> sourceElementCount = sourceElementCountGroups.get(parent);
    		for (int element : sourceElementCount.keySet()) {
    			if (sourceElementCount.get(element) != 0) {
					diffCount += Math.abs(sourceElementCount.get(element));
				}
    		}
    	}
    	
        return diffCount/2;
    }

	private static void union(int x, int y) {
		
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX == rootY) return;
		
		if (ranks[rootX] > ranks[rootY]) {
			parents[rootY] = rootX;
		} else if (ranks[rootX] < ranks[rootY]) {
			parents[rootX] = rootY;
		} else {
			parents[rootY] = rootX;
			ranks[rootX]++;
		}
	}

	private static int find(int x) {
		
		if (parents[x] != x) {
			parents[x] = find(parents[x]);
		}
		
		return parents[x];
	}
}

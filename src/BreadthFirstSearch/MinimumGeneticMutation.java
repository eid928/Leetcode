package BreadthFirstSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MinimumGeneticMutation {

	public static void main(String[] args) {
		/**
		 * 基因序列字串，皆由ACGT四種字母組成，一共8個字母
		 * 而基因可以突變，每一次突變會改變其中一個字母
		 * 請求出從start到end，最少得經過幾次的突變？
		 * bank則定義了合法的突變結構，若是不可能從start突變成end，則回傳1
		 */
		String start = "AAAAACCC";
		String end = "AACCCCCC";
		String[] bank = {"AAAACCCC","AAACCCCC","AACCCCCC"};
		
		System.out.println(getNeighbors(start, bank));
		System.out.println(minMutation(start, end, bank));
		
	}

    public static int minMutation(String start, String end, String[] bank) {
    	/**
    	 * 求最少的突變次數 => BFS
    	 * getNeighbor則須從bank中去比對取得目前可以突變的結構
    	 */
    	if (bank.length == 0) {
			return -1;
		}
    	
    	int mutationCount = 0;
    	Queue<String> queue = new LinkedList<>();
    	queue.add(start);
    	Set<String> visited = new HashSet<>();
    	
    	while (!queue.isEmpty()) {
    		
    		int currentSize = queue.size();
    		for (int i = 0; i < currentSize; i++) {
    			
    			String currGene = queue.poll();
    			
    			List<String> neighbors = getNeighbors(currGene, bank);
    			
    			for (String neighbor : neighbors) {
    				
    				if (neighbor.equals(end)) return mutationCount+1;
    				if (visited.contains(neighbor)) continue;
    				queue.add(neighbor);
    			}
    			
    			visited.add(currGene);
    		}
    		mutationCount ++;
    	}
    	
        return -1;
    }

	public static List<String> getNeighbors(String currGene, String[] bank) {
		
		List<String> neighbors = new ArrayList<>();
		
		for (String gene : bank) {
			
			int diff = 0;
			for (int i = 0; i < currGene.length(); i++) {
				
				if (currGene.charAt(i) != gene.charAt(i)) {
					diff ++;
				}
				if (diff > 1) {
					break;
				}
			}
			if (diff == 1) {
				neighbors.add(gene);
			}
		}
		
		return neighbors;
	}
}

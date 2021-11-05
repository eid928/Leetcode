package PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class TopKFrequentElements {

	public static void main(String[] args) {
		
		int[] nums = {1,1,1,1,2,2,3};
		int[] topKFrequent = topKFrequent(nums, 2);
		for (int i = 0; i < topKFrequent.length; i++) {
			System.out.println(topKFrequent[i]);
		}
	}
	
	public static int[] topKFrequent(int[] nums, int k) {
		
		Map<Integer, Integer> countMap = new HashMap<>();
		
		for (int i : nums) {
			countMap.put(i, countMap.getOrDefault(i, 0) + 1);
		}
		
		Queue<Integer> queue = new PriorityQueue<>((num1, num2) -> countMap.get(num1) - countMap.get(num2));
		/* count小 -> 優先度高 -> 排到排頭 */
		
		for (Integer num : countMap.keySet()) {
			
			queue.add(num);
			if (queue.size() > k) {
				/* 當隊伍超過k時，poll會remove排頭 -> count小的會被remove */
				queue.poll();
			}
		}
		
		int[] topK = new int[k];
		for (int i = 0; i < topK.length; i++) {
			topK[i] = queue.poll();
		}
		
        return topK;
    }
}

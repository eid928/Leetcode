package Sort;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {

	public static void main(String[] args) {
		/**
		 * 給定一組array
		 * 求出出現頻率最高的前k種數字
		 */
		int[] nums = {1,1,1,2,2,3};
		int[] result = topKFrequent(nums, 2);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
	}

	public static int[] topKFrequent(int[] nums, int k) {
		/**
		 * quick select
		 * 先用map來儲存每種數字的出現次數
		 * partition的時候用出現次數來作為比較的依據
		 */
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		
		for (int num : nums) {
			countMap.put(num, countMap.getOrDefault(num, 0)+1);
		}
		System.out.println(countMap);
		
		int[] elements = new int[countMap.size()];
		int i = 0;
		for (int element : countMap.keySet()) {
			elements[i] = element;
			i++;
		}
		for (int element : elements) {
			System.out.print(element+",");
		}
		System.out.println();
		int left = 0;
		int right = elements.length-1;
		
		while (true) {
			
			int pivotIndex = partition(elements, left, right, countMap);
			if (pivotIndex == elements.length-k) {
				int[] result = new int[k];
				System.arraycopy(elements, pivotIndex, result, 0, k);
				return result;
			}
			
			if (pivotIndex < elements.length-k) {
				left = pivotIndex+1;
			} else {
				right = pivotIndex-1;
			}
		}
	}

	private static int partition(int[] nums, int left, int right, Map<Integer, Integer> countMap) {
		/**
		 * 相關邊界條件可看kth Largest Element In An Array
		 */
		int pivotVal = nums[left];
		int i = left+1;
		int j = right;
		
		while (i <= j) {
			
			if (countMap.get(nums[i]) > countMap.get(pivotVal) && countMap.get(nums[j]) < countMap.get(pivotVal)) {
				swap(nums, i, j);
				i++;
				j--;
			}
			
			if (countMap.get(nums[i]) <= countMap.get(pivotVal)) {
				i++;
			}
			
			if (countMap.get(nums[j]) >= countMap.get(pivotVal)) {
				j--;
			}
		}
		
		swap(nums, left, j);
		
		return j;
	}

	private static void swap(int[] nums, int i, int j) {
		
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}

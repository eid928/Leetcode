package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayintoFibonacciSequence {

	public static void main(String[] args) {
		/**
		 * 給定一數字組成的字串，將其轉成費式數列的list
		 * 要符合
		 * 1. list[i] + list[i+1] = list[i+2] for all 0 <= i < list.length - 2
		 * 2. 0只能轉成0，不能有leading zero
		 * 3. 完全無符合的話回傳空list
		 */
		String num = "123456579";
		System.out.println(splitIntoFibonacci(num));
		
		String num2 = "11235813";
		System.out.println(splitIntoFibonacci(num2));
		
		String num3 = "112358130";
		System.out.println(splitIntoFibonacci(num3));
		
		String num4 = "0123";
		System.out.println(splitIntoFibonacci(num4));
		
		String num5 = "1101111";
		System.out.println(splitIntoFibonacci(num5));
	}

	private static List<Integer> finalResult;
	private static boolean foundResult;
	
    public static List<Integer> splitIntoFibonacci(String num) {
        /**
         * 字串組合dfs
         * 數字不會重複使用 => 不用visited
         * 用一個Integer target來紀錄目前需要的數字
         * 
         * 時間複雜度：
         * 不考慮所有優化的判斷條件，想像每位數字間都有一個分隔的逗號
         * 窮舉的每個可能答案中，這些逗號就是存在or不存在，兩種狀況
         * 故時間複雜度為2^n
         */
    	List<Integer> result = new ArrayList<>();
    	finalResult = new ArrayList<>();
    	foundResult = false;
    	dfs(num, 0, null, result);
    	
    	return finalResult;
    }

	private static void dfs(String num, int index, Integer target, List<Integer> result) {
		
		if (foundResult) {
			return;
		}
		
		if (index >= num.length()) {
			/**
			 * 當看完字串且result順利產生3組以上即是答案
			 */
			if (result.size() >=3) {
				finalResult = new ArrayList<>(result);
				foundResult = true;
			}
			return;
		}
		
		for (int i = 1; index + i <= num.length(); i++) {
			
			if (foundResult) {
				return;
			}
			
			String substring = num.substring(index, index + i);
			if (substring.startsWith("0") && substring.length() > 1) { // 處理leading zero，直接break
				break;
			}
			
			int curNum;
			try {
				curNum = Integer.parseInt(substring); // 處理轉換 > 2^32次方
			} catch (NumberFormatException e) {
				break;
			}
			
			if (target != null && curNum != target) { // 當有指定target時，字串產生的數字需符合target
				if (curNum > target) { // 數字爆了就不用看下一位數字，否則就繼續看下一個數字
					break;
				}
				continue;
			}
			
			Integer nextTarget;
			if (result.size() >= 1) {
				nextTarget = curNum + result.get(result.size()-1);
			} else {
				nextTarget = null; // 前兩位數字不需要target
			}
			
			result.add(curNum);
			dfs(num, index+i, nextTarget, result);
			result.remove(result.size()-1);
		}
	}
}

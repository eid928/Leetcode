package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestStringChain {

	public static void main(String[] args) {
		/**
		 * 給定一串string array
		 * 請求出最長的Predecessor string chain長度為多少
		 * Predecessor: 若B可以藉由從A中任意位置插入一個字母而形成，其他字母保持不變，則A是B的predecessor
		 * Predecessor string chain: 一個字串list，前一個字串皆為後一個字串的Predecessor
		 */
		String[] words = {"a","b","ba","bca","bda","bdca"}; // 4: "a","ba","bda","bdca"
		System.out.println(longestStrChain(words));

		String[] words2 = {"xbc","pcxbcf","xb","cxbc","pcxbc"}; // 5: "xb", "xbc", "cxbc", "pcxbc", "pcxbcf"
		System.out.println(longestStrChain(words2));
		
		String[] words3 = {"abcd","dbqca"}; // 1: "abcd" or "dbqca"
		System.out.println(longestStrChain(words3));
	}
	
	public static int longestStrChain(String[] words) {
		/**
		 * dp: tabulation
		 * dp[i] = 以words[i]做為結尾的strChain長度
		 * dp[i] = Max(dp[j])+1 where words[j] is Predecessor of words[i]
		 * time: O(n^2)
		 * 因為字串長度<=16，可以先把isPredecessor的判定看作constant time
		 */
		Arrays.sort(words, (s1, s2) -> {
			return s1.length() - s2.length();
		});
		
		int[] dp = new int[words.length];
		dp[0] = 1;
		int largestStrChainLength = 1;
		
		for (int i = 1; i < words.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				
				if (isPredecessor(words[j], words[i]) && dp[j]+1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			largestStrChainLength = Math.max(largestStrChainLength, dp[i]);
		}
		
		return largestStrChainLength;
	}

    public static int longestStrChain2(String[] words) {
    	/**
    	 * dp: dfs + memo
    	 * 首先Predecessor string chain的元素，字串長度一定為遞增，每次+1
    	 * 用樹狀圖來看，一個字串A的下一個可能的選擇，只能是長度為A長度+1的字串
    	 * 所以先建立lengthMap，按照字串長度做分組
    	 * 接著把words中每個字當作起點做dfs
    	 * 因為有做length Map，做isPredecessor的次數較法一少很多
    	 */
    	
    	Map<Integer, List<String>> lengthMap = new HashMap<Integer, List<String>>();
    	for (int i = 0; i < words.length; i++) {
    		
    		String word = words[i];
    		int length = word.length();
    		List<String> sameLengthList = lengthMap.getOrDefault(length, new ArrayList<String>());
    		sameLengthList.add(word);
    		lengthMap.put(length, sameLengthList);
    	}
    	
    	Map<String, Integer> memo = new HashMap<String, Integer>();
    	int longestStrChain = 0;
    	
    	for (int i = 0; i < words.length; i++) {
    		
    		String startWord = words[i];
    		int strChainLength = dfs(startWord, lengthMap, memo);
    		longestStrChain = Math.max(longestStrChain, strChainLength);
    	}
    	System.out.println(memo);
    	
        return longestStrChain;
    }

	private static int dfs(String startWord, Map<Integer, List<String>> lengthMap, Map<String, Integer> memo) {
		
		if (memo.containsKey(startWord)) {
			return memo.get(startWord);
		}
		
		int curLength = startWord.length();
		if (!lengthMap.containsKey(curLength+1)) {
			return 1;
		}
		
		List<String> possibleNextStringList = lengthMap.get(curLength+1);
		int largeststrChainLengthForNext = 0;
		
		for (String possibleNextString : possibleNextStringList) {
			
			if (isPredecessor(startWord, possibleNextString)) {
				int strChainLengthForNext = dfs(possibleNextString, lengthMap, memo);
				largeststrChainLengthForNext = Math.max(strChainLengthForNext, largeststrChainLengthForNext);
			}
		}
		memo.put(startWord, 1+largeststrChainLengthForNext);
		return 1+largeststrChainLengthForNext;
	}

	private static boolean isPredecessor(String wordA, String wordB) {
		
		if (wordB.length() != wordA.length()+1) {
			return false;
		}
		
		int insertCount = 0;
		int i = 0;
		int j = 0;
		while (i < wordA.length() && j < wordB.length()) {
			
			if (wordA.charAt(i) == wordB.charAt(j)) {
				i++;
				j++;
			} else {
				insertCount++;
				j++;
				if (insertCount > 1) {
					return false;
				}
			}
		}
		
		return true;
	}
}

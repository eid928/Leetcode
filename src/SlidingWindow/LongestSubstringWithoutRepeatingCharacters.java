package SlidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		/**
		 * 給定一組字串
		 * 求出最長不含重複字母的substring的長度
		 */
		String s = "abcabcbb";
		System.out.println(lengthOfLongestSubstring(s));
		s = "bbbbb";
		System.out.println(lengthOfLongestSubstring(s));
		s = "pwwkew";
		System.out.println(lengthOfLongestSubstring(s));
		s = "";
		System.out.println(lengthOfLongestSubstring(s));
	}

    public static int lengthOfLongestSubstring(String s) {
    	/**
    	 * sliding window
    	 */
    	if (s.length() == 0) {
			return 0;
		}
        
    	int left = 0;
    	int right = 0;
    	Set<Character> set = new HashSet<Character>();
    	int longestLen = 1;
    	
    	while (right < s.length()) {
    		
    		char leftChar = s.charAt(left);
    		char rightCahr = s.charAt(right);
    		
    		if (!set.contains(rightCahr)) {
				set.add(rightCahr);
				longestLen = Math.max(longestLen, right - left + 1);
				right++;
			} else {
				set.remove(leftChar);
				left++;
			}
    	}
    	
    	return longestLen;
    }
}

package DepthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {

	public static void main(String[] args) {
		/**
		 * 給定一組字串
		 * 請將字串盡量拆分成最多組子字串
		 * 且讓每種字母，在各個子字串只會出現一次
		 * 例如此題就是拆成："ababcbaca", "defegde", "hijhklij"
		 * "ababcbaca", "defegdehijhklij"不符合因為可以拆分成更多組
		 * 回傳拆分後每組字串的長度
		 */
		String s = "ababcbacadefegdehijhklij";
		System.out.println(partitionLabels(s));
	}
	
    public static List<Integer> partitionLabels(String s) {
    	/**
    	 * 特殊解
    	 * 首先，每一組拆分字串的最後一個字母，都是該字母出現的最後一個位置
    	 * 例如第一組ababcbaca，就是斷在a最後出現的地方
    	 * 所以先遍歷一次掌握每個字母最後出現的位置
    	 * 可以得到{a=8, b=5, c=7, d=14, e=15, f=11, g=13, h=19, i=22, j=23, k=20, l=21}
    	 */
    	int start = 0;
    	int last = 0;
    	Map<Character, Integer> theLastPosOfChar = new HashMap<>();
    	List<Integer> result = new ArrayList<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		theLastPosOfChar.put(s.charAt(i), i);
    	}
    	// {a=8, b=5, c=7, d=14, e=15, f=11, g=13, h=19, i=22, j=23, k=20, l=21}
    	for (int i = 0; i < s.length(); i++) {
    		/**
    		 * 遍歷每一個字母
    		 * 例如現在看第一個a，那這個子字串，起碼要斷在8的位置。last=8
    		 * 接著到第二個b，b最後出現在5，所以也能在8的範圍內，不更新last
    		 * 最後當i=last時，表示是最短拆分了，加進result內
    		 */
    		last = Math.max(last, theLastPosOfChar.get(s.charAt(i)));
    		if (i == last) {
				result.add(last - start + 1);
				start = i+1;
			}
    	}
    	
    	return result;
	}
}

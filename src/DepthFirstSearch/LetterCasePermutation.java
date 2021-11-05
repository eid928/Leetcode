package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

	public static void main(String[] args) {
		/**
		 * 給定一組字串，字串包含數字或是英文字母
		 * 英文字母可以任意變化為大小寫
		 * 求出所有大小寫變化的組合
		 * 例如"a1b2" => "a1b2","a1B2","A1b2","A1B2"
		 */
		String s1 = "a1b2";
		String s2 = "3z4";
		String s3 = "12345";
		
		System.out.println(letterCasePermutation(s1));
		System.out.println(letterCasePermutation(s2));
		System.out.println(letterCasePermutation(s3));
	}

    public static List<String> letterCasePermutation(String s) {
        /**
         * 求所有組合 => dfs
         * result採用String，每次都用新的避免共用
         */
    	List<String> results = new ArrayList<>();
    	String result = "";
    	
    	dfs(s, 0, result, results);
    	
    	return results;
    }

	private static void dfs(String s, int index, String result, List<String> results) {
		/**
		 * 畫出樹狀圖就很容易理解
		 */
		if (result.length() == s.length()) {
			results.add(result);
			return;
		}
		
		char currChar = s.charAt(index);
		
		if (Character.isDigit(currChar)) {
			/* 若是數字就只有一條路 */
			dfs(s, index+1, result+currChar, results);
			
		} else {
			/* 若是字母就有兩條路 */
			dfs(s, index+1, result+Character.toLowerCase(currChar), results);
			dfs(s, index+1, result+Character.toUpperCase(currChar), results);
		}
	}
}

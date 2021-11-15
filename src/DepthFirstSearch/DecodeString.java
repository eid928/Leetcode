package DepthFirstSearch;

public class DecodeString {

	public static void main(String[] args) {
		/**
		 * 給定一組encode字串，請將之decode
		 * []內的內容就是要重複前面數字的次數
		 */
		String s = "3[a]2[bc]";
		System.out.println(decodeString(s)); // aaabcbc
		s = "3[a2[c]]";
		System.out.println(decodeString(s)); // accaccacc
		s = "2[abc]3[cd]ef";
		System.out.println(decodeString(s)); // abcabccdcdcdef
		s = "abc3[cd]xyz";
		System.out.println(decodeString(s)); // abccdcdcdxyz
	}

    public static String decodeString(String s) {
        /**
         * 典型dfs
         */
    	StringBuffer sb = new StringBuffer();
    	
    	dfs(s, sb);
    	
    	return sb.toString();
    }

	private static void dfs(String s, StringBuffer sb) {
		/**
		 * dfs
		 */
		for (int i = 0; i < s.length(); i++) {
			
			char curChar = s.charAt(i);
			if (Character.isLetter(curChar)) { /* case1: 普通的字母，直接加進sb */
				sb.append(curChar);
			} else { /* case2: 非字母了，必須得知數字x以及括號內的內容，將括號內的內容送進dfs中x次 */
				
				int j = i;
				while (Character.isDigit(s.charAt(j))) { /* 數字可能不只一位 */
					j++;
				}
				int number = Integer.parseInt(s.substring(i, j)); /* 此時j在[，內容從j+1開始 */
				int stringStart = j+1;
				int bracketCount = 1;
				while (j < s.length() && bracketCount != 0) { /* 計算[的數量，決定內容是哪裡到哪裡 */
					j++;
					if (s.charAt(j) == ']') {
						bracketCount --;
					}
					if (s.charAt(j) == '[') {
						bracketCount ++;
					}
				}
				String newString = s.substring(stringStart, j); /* 取出內容丟進dfs */
				for (int k = 0; k < number; k++) {
					dfs(newString, sb);
				}
				
				i = j;
			}
		}
	}
}

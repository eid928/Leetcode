package DepthFirstSearch;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddresses {

	public static void main(String[] args) {
		/**
		 * 給定一串字串，
		 * 請回傳所有可能形成的ip address
		 * ip address以四個數字組成，數字可為0~255，且除了0之外，不可以0作為開頭
		 */
		System.out.println(restoreIpAddresses("25525511135"));
		System.out.println(restoreIpAddresses("0000"));
		System.out.println(restoreIpAddresses("1111"));
		System.out.println(restoreIpAddresses("010010"));
		System.out.println(restoreIpAddresses("101023"));
	}

    public static List<String> restoreIpAddresses(String s) {
        
    	List<String> results = new ArrayList<>();
    	
    	dfs(s, 0, "", results, 0);
    	
    	return results;
    }

	private static void dfs(String s, int start, String result, List<String> results, int level) {
		/**
		 * 列舉可能 => dfs
		 * 畫成樹狀圖的話更容易理解，其實為三元樹
		 * 從字串的一開始出發，數字可能為1位數，2位數，或是3位數
		 * 以25525511135為例，即：
		 * 2  25  255
		 * 分別檢查這三個節點是否合法
		 * 再從合法的節點出發去查看各自三個子節點
		 */
		if (start >= s.length()) {
			return;
		}
		
		if (level == 3) {
			/**
			 * 當來到最後一層
			 * 就直接取用剩下來的substring
			 */
			String currElement = s.substring(start);
			if (!valid(currElement)) return;
			results.add(result+currElement);
			
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			
			if (start >= s.length() || start+i+1 > s.length()) {
				continue;
			}
			String currElement = s.substring(start, start+i+1);
			
			if (!valid(currElement)) continue;
			dfs(s, start+i+1, result+currElement+".", results, level+1);
		}
	}

	private static boolean valid(String currElement) {
		
		if (!currElement.equals("0") && currElement.startsWith("0")) {
			return false;
		}
		
		int integer;
		try {
			integer = Integer.parseInt(currElement);
			if (integer > 255) {
				return false;
			}
		} catch (NumberFormatException e) { /* 最後一層取剩下的所有字串，可能導致超出2^32次方 => 直接return false */
			return false;
		}
		
		return true;
	}
}

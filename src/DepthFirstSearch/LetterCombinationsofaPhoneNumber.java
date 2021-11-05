package DepthFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsofaPhoneNumber {

	private static Map<Character, List<String>> map = new HashMap<>();
	
	static {
		map.put('2', Arrays.asList(new String[] {"a","b","c"}));
		map.put('3', Arrays.asList(new String[] {"d","e","f"}));
		map.put('4', Arrays.asList(new String[] {"g","h","i"}));
		map.put('5', Arrays.asList(new String[] {"j","k","l"}));
		map.put('6', Arrays.asList(new String[] {"m","n","o"}));
		map.put('7', Arrays.asList(new String[] {"p","q","r","s"}));
		map.put('8', Arrays.asList(new String[] {"t","u","v"}));
		map.put('9', Arrays.asList(new String[] {"w","x","y","z"}));
	}
	
	public static void main(String[] args) {
		/**
		 * 電話號碼encode
		 * 根據上面的map，一個數字可能代表一些char
		 * 給定一個輸入的字串digits，回傳所有可能的字串
		 */
		String digits = "23";
		System.out.println(map);
		System.out.println(letterCombinations(digits));
		System.out.println(letterCombinations2(digits));
	}

	public static List<String> letterCombinations(String digits) {
        
		/**
		 * 遍歷digits，每走到一個char，就會有三四個分支，就像tree -> 用recursion
		 * recursion過程中，index紀錄現在走到哪個char，temp紀錄目前這條路線的字串
		 * 當一條路線遍歷完成，就把完成的字串加入list
		 */
		
		List<String> list = new ArrayList<>();
		if (digits.equals("")) {
			return list;
		}
		
		dfs(list, digits, 0, "");
		
		return list;
    }
	
	public static void dfs(List<String> list, String digits, int index, String temp) {
		
		if (index > digits.length() - 1) {
			list.add(temp);
			return;
		}
		
		char currChar = digits.charAt(index);
		List<String> optionList = map.get(currChar);
		for (String string : optionList) {
			String newTemp = temp + string;
			dfs(list, digits, index+1, newTemp);
		}
	}
	
	public static List<String> letterCombinations2(String digits) {
		
		/**
		 * iterative作法
		 * 遍歷digits，走到某char時
		 * 把之前的所有分支存list中拿出，跟該char對應的字母組裝好存成temp
		 * 之後再把list更新為temp
		 */
		
		List<String> list = new ArrayList<>();
		if (digits.equals("")) {
			return list;
		}
		
		list.add("");
		
		for (int i = 0; i < digits.length(); i++) {
			List<String> optionList = map.get(digits.charAt(i));
			List<String> temp = new ArrayList<>();
			
			for (int j = 0; j < list.size(); j++) {
				
				String currString = list.get(j);
				for (int k = 0; k < optionList.size(); k++) {
					temp.add(currString + optionList.get(k));
				}
			}
			
			list = temp;
		}
		
		return list;
	}
}

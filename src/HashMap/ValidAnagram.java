package HashMap;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";
		System.out.println(isAnagram(s, t));
		s = "rat";
		t = "car";
		System.out.println(isAnagram(s, t));
		s = "aacc";
		t = "ccac";
		System.out.println(isAnagram(s, t));
	}

	public static boolean isAnagram(String s, String t) {
		
		if (s.length() != t.length()) {
			return false;
		}
		Map<Character, Integer> charSet = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char theChar = s.charAt(i);
			if (charSet.containsKey(theChar)) {
				charSet.put(theChar, charSet.get(theChar)+1);
			} else {
				charSet.put(theChar, 1);
			}
		}
		for (int i = 0; i < t.length(); i++) {
			char theChar = t.charAt(i);
			if (!charSet.containsKey(theChar) || charSet.get(theChar) == 0) {
				return false;
			} else {
				charSet.put(theChar, charSet.get(theChar)-1);
			}
		}
		
        return true;
    }
}

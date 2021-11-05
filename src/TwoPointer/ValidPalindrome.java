package TwoPointer;

public class ValidPalindrome {

	public static void main(String[] args) {
		String string1 = "A man, a plan, a canal: Panama";
		String string2 = "race a car";

		System.out.println(isPalindrome(string1));
		System.out.println(isPalindrome(string2));
	}

	public static boolean isPalindrome(String s) {
		
		String pureString = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		int left = 0;
		int right = pureString.length() - 1;
		while (left < right) {
			if (pureString.charAt(left) != pureString.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
    }
}

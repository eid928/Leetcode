package Special;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] input = {"flower","flow","flight"};
		String[] input2 = {"dog","racecar","car"};
		
		System.out.println(longestCommonPrefix(input));
		System.out.println(longestCommonPrefix(input2));
	}
	
    public static String longestCommonPrefix(String[] strs) {
    	
    	String prefix = "";
    	for (int j = 0; j < strs.length; j++) {
    		
    		String string = strs[j];
    		String prefixTemp = "";
			if (j==0) {
				prefixTemp = string;
			}
    		for (int i = 0; i < Math.min(prefix.length(), string.length()); i++) {
    			if (prefix.charAt(i) == string.charAt(i)) {
					prefixTemp += string.charAt(i);
				} else {
					break;
				}
    		}
    		
    		prefix = prefixTemp;
		}
    	
        return prefix;
    }
}

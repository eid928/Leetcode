package HashMap;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

	public static void main(String[] args) {
		
		int[] nums = {2,2,1,1,1,2,2};
		System.out.println(majorityElement(nums));
	}
    public static int majorityElement(int[] nums) {
    	
    	Map<Integer, Integer> countMap = new HashMap<>();
    	int major = nums[0];
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		int element = nums[i];
    		
			if (countMap.containsKey(element)) {
				countMap.put(element, countMap.get(element)+1);
			} else {
				countMap.put(element, 1);
			}
			
			if (countMap.get(element) > countMap.get(major)) {
				major = element;
			}
    	}
    	
        return major;
    }
}

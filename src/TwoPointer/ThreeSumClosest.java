package TwoPointer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreeSumClosest {

	public static void main(String[] args) {
		/**
		 * 回傳3sum離target最近的值
		 */
		int[] nums1 = {-1,2,1,-4};
		int[] nums2 = {0,0,0};
		System.out.println(threeSumClosest(nums1, 1));
		System.out.println(threeSumClosest(nums2, 1));
	}
    public static int threeSumClosest(int[] nums, int target) {
    	
    	/**
    	 * 與3sum類似
    	 * 1. sort
    	 * 2. 遍歷sorted list
    	 * 3. 2sum為目前index之後的左右指針
    	 */
    	
    	List<Integer> list = new ArrayList<>();
    	
    	for (int i : nums) {
			list.add(i);
		}
    	
    	Collections.sort(list);
    	
    	Integer closest = null;
    	
    	for (int i = 0; i < list.size() - 2; i++) {
    		int left = i+1;
    		int right = list.size()-1;
    		while (left < right) {
    			
    			int threeSum = list.get(i) + list.get(left) + list.get(right);
    			
    			if (closest == null) {
					closest = threeSum;
				}
    			
				if (threeSum == target) {
					return target;
				}
    			
    			if (threeSum > target) {
    				if (Math.abs(closest - target) > Math.abs(threeSum - target)) {
						closest = threeSum;
					}
					right--;
				} else {
    				if (Math.abs(closest - target) > Math.abs(threeSum - target)) {
						closest = threeSum;
					}
					left++;
				}
    		}
    	}
    	
        return closest;
    }
}

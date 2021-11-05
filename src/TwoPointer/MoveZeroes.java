package TwoPointer;

public class MoveZeroes {

	public static void main(String[] args) {
		/**
		 * 將0通通移到array後方
		 * 限定in-place
		 */
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);
		
		for (int num : nums) {
			System.out.print(num+",");
		}
	}
	
    public static void moveZeroes(int[] nums) {
        /**
         * two pointer
         * right遍歷，先將非零的通通移到前方(left)
         * 之後一直補0直到尾巴
         */
    	int left = 0;
    	int right = 0;
    	
    	while (right < nums.length) {
    		
    		if (nums[right] != 0) {
				nums[left] = nums[right];
				left++;
				right++;
				continue;
			} else {
				right++;
			}
    	}
    	
    	while (left < nums.length) {
    		nums[left] = 0;
    		left ++;
    	}
    }
}

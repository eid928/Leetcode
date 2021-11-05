package Sort;

public class SortColors {

	public static void main(String[] args) {
		/**
		 * 給定一組array，裡面的元素只有0,1,2三種
		 * 想實作in-place的排序演算法，將其依照0-1-2的順序排好
		 */
		int[] nums = {2,0,2,1,1,0};
		sortColors(nums);
		for (int num : nums) {
			System.out.println(num);
		}
	}

    public static void sortColors(int[] nums) {
        /**
         * 只有三種元素，又要in-place(空間複雜度為constant)
         * => counting sort
         */
    	int[] count = new int[3];
    	for (int i = 0; i < nums.length; i++) {
    		count[nums[i]] += 1;
    	}
    	int currNum = 0;
    	for (int i = 0; i < nums.length; i++) {
    		
    		while (count[currNum] == 0) { /* 現在的數字沒了就往後找 */
    			currNum += 1;
    		}
    		nums[i] = currNum;
    		count[currNum] -= 1;
    	}
    }
}

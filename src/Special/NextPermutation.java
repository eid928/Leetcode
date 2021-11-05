package Special;

public class NextPermutation {

	public static void main(String[] args) {
		/**
		 * 給一組數字array
		 * 求出next permutation(下一組排列)
		 * 將該組數字的所有排列以字典排序後，題目所給的排列的下一個牌列即為所求
		 * 並要求空間複雜度為constant
		 */
		int[] nums = {1,2,7,4,3,1};
		for (int num : nums) System.out.print(num+",");
		System.out.println();
		nextPermutation(nums);
		for (int num : nums) System.out.print(num+",");
		System.out.println();
		int[] nums2 = {3,2,1};
		nextPermutation(nums2);
		for (int num : nums2) System.out.print(num+",");
	}

    public static void nextPermutation(int[] nums) {
        /**
         * 要求空間複雜度，所以不能把所以排列列出
         * 有特殊演算法
         * 以1,2,7,4,3,1為例子
         * 先從尾巴開始看，找到第一個遞減的數字為2
         * 之後再從尾巴開始看，找到第一個>2的數字為3
         * 將2,3交換=>
         * 1,3,7,4,2,1
         * 之後將3之後的array做reverse=>
         * 1,3,1,2,4,7
         */
    	if (nums.length <= 1) {
			return;
		}
    	
    	int firstDecreseFromTail = -1;
    	
    	for (int i = nums.length-2; i >= 0; i--) {
    		
    		if (nums[i] < nums[i+1]) {
    			firstDecreseFromTail = i;
    			break;
			}
    	}
    	
    	if (firstDecreseFromTail == -1) {
    		reverse(firstDecreseFromTail+1, nums);
    		return;
		}
    	
    	int firstLargerThanDecrese = nums.length-1;
    	
    	for (int i = nums.length-1; i >= firstDecreseFromTail; i--) {
    		
    		if (nums[i] > nums[firstDecreseFromTail]) {
				firstLargerThanDecrese = i;
				break;
			}
    	}
    	
    	swap(firstDecreseFromTail, firstLargerThanDecrese, nums);
    	reverse(firstDecreseFromTail+1, nums);
    }

	private static void reverse(int start, int[] nums) {
		
		int left = start;
		int right = nums.length-1;
		
		while (left < right) {
			
			swap(left, right, nums);
			left++;
			right--;
		}
	}

	private static void swap(int left, int right, int[] nums) {
		
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
}

package SlowFastPointer;

public class FindtheDuplicateNumber {

	public static void main(String[] args) {
		/**
		 * 給定一個array，找出其中重複的數字
		 * 最佳解：時間空間=O(n),O(1)
		 */
		int[] nums1 = {1,3,4,2,2};
		int[] nums2 = {3,1,3,4,2};
		System.out.println(findDuplicate(nums1));
		System.out.println(findDuplicate(nums2));
	}

    public static int findDuplicate(int[] nums) {
    	/**
    	 * 用hashTable => 空間複雜度=O(n)
    	 * 先sort => 時間複雜度=O(nlogn)
    	 * 皆非最佳解
    	 * 快慢指針：like Linked List Cycle II
    	 * 想像array是一個linked list，且元素不重複
    	 * array中的元素代表的是指向下一個元素的index
    	 * 如[1,3,4,2,2]
    	 * 1 -> 3 -> 2 -> 4
    	 * 而4又會指向2形成一個cycle
    	 * 則cycle的起點即為重複的數字
    	 * 如何找到起點請見Linked List Cycle II
    	 */
    	
    	int slow = 0;
    	int fast = 0;
    	
    	while (true) {
    		
    		slow = nums[slow];
    		fast = nums[fast];
    		fast = nums[fast];
    		
    		if (nums[slow] == nums[fast]) {
				break;
			}
    	}
    	
    	slow = 0;
    	while (true) {
    		if (nums[slow] == nums[fast]) {
				break;
			}
    		slow = nums[slow];
    		fast = nums[fast];
    	}
    	
        return nums[slow];
    }
}

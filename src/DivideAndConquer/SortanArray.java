package DivideAndConquer;

public class SortanArray {

	public static void main(String[] args) {
		int[] nums1 = {5,2,3,1};
		int[] nums2 = {5,1,1,2,0,0};

		int[] sorted1 = sortArray(nums1);
		int[] sorted2 = sortArray(nums2);
		
		for (int i = 0; i < sorted1.length; i++) System.out.print(sorted1[i] + ",");
		System.out.println("");
		for (int i = 0; i < sorted2.length; i++) System.out.print(sorted2[i] + ",");
	}
	
    public static int[] sortArray(int[] nums) {
    	mergeSort(nums, 0, nums.length-1);
    	
        return nums;
    }
    
    public static void mergeSort(int[] nums, int left, int right) {
    	
    	if (left >= right) {
    		/* 要有等於，因為在最後left+1=right的時候會變成：
    		 * mergeSort(nums, left, left);
    		 * mergeSort(nums, right, right); 
    		 * 有等號才能跳出無窮迴圈 */
			return;
		}
    	
    	int mid = (left + right) / 2;
    	
    	mergeSort(nums, left, mid);
    	mergeSort(nums, mid+1, right);
    	
    	merge(nums, left, mid, right);
    }
    
    public static void merge(int[] nums, int left, int mid, int right) {
    	
    	/* merge兩段：
    	 * 1. left ~ mid
    	 * 2. mid+1 ~ right */
    	int[] tempArray = new int[right - left + 1];
    	
    	int i = left;
    	int j = mid + 1;
    	int k = 0;
    	while (i <= mid || j <= right) {
    		/* 兩邊只剩一邊有剩 */
    		if (i > mid) {
    			tempArray[k] = nums[j];
				j++;
				k++;
				continue;
			}
    		if (j > right) {
    			tempArray[k] = nums[i];
				i++;
				k++;
				continue;
			}
    		/* 兩邊都還有剩 */
    		if (nums[i] < nums[j]) {
				tempArray[k] = nums[i];
				i++;
			} else {
				tempArray[k] = nums[j];
				j++;
			}
    		k++;
    	}
    	
    	for (int l = 0; l < tempArray.length; l++) {
    		nums[left + l] = tempArray[l];
    	}
    }
}

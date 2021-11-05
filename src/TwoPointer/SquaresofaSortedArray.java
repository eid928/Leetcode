package TwoPointer;

public class SquaresofaSortedArray {

	public static void main(String[] args) {
		/**
    	 * nums為一sorted array
    	 * 回傳一個array，各項為為參數array各項的平方，並且要sorted
    	 */
		int[] nums1 = {-4,-1,0,3,10};
		int[] nums2 = {-7,-3,2,3,11};
		
		int[] sortedSquared1 = sortedSquares(nums1);
		int[] sortedSquared2 = sortedSquares(nums2);
		
		for (int i = 0; i < sortedSquared1.length; i++) System.out.print(sortedSquared1[i] + ",");
		System.out.println("");
		for (int i = 0; i < sortedSquared2.length; i++) System.out.print(sortedSquared2[i] + ",");
	}
	
    public static int[] sortedSquares(int[] nums) {
    	/**
    	 * 題目需求時間複雜度O(n)
    	 * 由於正數大和負數小的平方較大，故採用雙指針
    	 */
    	int left = 0;
    	int right = nums.length - 1;
    	int[] sortedSquaredArray = new int[nums.length];
    	int i = nums.length - 1;;
    	
    	while (left <= right) {
    		int squaredLeft = nums[left] * nums[left];
    		int squaredRight = nums[right] * nums[right];
    		
			if (squaredLeft < squaredRight) {
				sortedSquaredArray[i] = squaredRight;
				right--;
			} else {
				sortedSquaredArray[i] = squaredLeft;
				left++;
			}
			i--;
    	}
    	
        return sortedSquaredArray;
    }
}

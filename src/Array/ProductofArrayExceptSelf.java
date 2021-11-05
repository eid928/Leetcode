package Array;

public class ProductofArrayExceptSelf {

	public static void main(String[] args) {
		int[] nums = {2,3,4,5};
		int[] productOfNums = productExceptSelf(nums);
		printArray(productOfNums);
	}

	public static int[] productExceptSelf(int[] nums) {
		
		int[] productArray = new int[nums.length];
		int productBefore = 1; /* 0~i-1的乘積 */
		for (int i = 0; i < nums.length; i++) {
			productArray[i] = productBefore;
			productBefore *= nums[i];
		}
		int productAfter = 1; /* i+1到最後的乘積 */
		for (int i = nums.length - 1; i >= 0; i--) {
			productArray[i] *= productAfter;
			productAfter *= nums[i];
		}
        return productArray;
    }
	
	public static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ", ");
		}
	}
}

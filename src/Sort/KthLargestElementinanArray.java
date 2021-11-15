package Sort;

public class KthLargestElementinanArray {

	public static void main(String[] args) {
		/**
		 * 給定一組array和一正整數k
		 * 求出array中第k大的數字
		 */
		int[] nums = {3,2,1,5,6,4};
		
		System.out.println(findKthLargest(nums, 2)); //5
		
		nums = new int[] {3,2,3,1,2,4,5,5,6};
		
		System.out.println(findKthLargest(nums, 4)); //4
		
		nums = new int[] {7,6,5,4,3,2,1};
		
	}
	
	public static int findKthLargest(int[] nums, int k) {
		/**
		 * 最簡單是直接排序後回傳相對應的數字
		 * 但更有效率的是直接做quick sort
		 * 做到pivot落在nums.length - k這個位置時，表示該位置的數字為答案了
		 * 不用把quick sort全部做完
		 */
		int left = 0;
		int right = nums.length - 1;
		
		while (true) {
			
			int pivotIndex = partition(nums, left, right);
			
			if (pivotIndex == nums.length - k) return nums[pivotIndex];
			
			if (pivotIndex < nums.length - k) {
				left = pivotIndex + 1;
			} else {
				right = pivotIndex - 1;
			}
		}
	}

	private static int partition(int[] nums, int start, int end) {
		/**
		 * partition做完後會回傳pivot的位置
		 * 一開始以start位置的數字作為partition的基準數字
		 */
		int i = start+1;
		int j = end;
		int pivotValue = nums[start];
		
		while (i <= j) {
			/**
			 * 邊界條件的思考
			 * 首先偶數的狀況
			 * 1 9 5 10
			 *   i j
			 * i,j交換後各進一步，i>j，pivot最後再跟j交換
			 * 奇數的狀況
			 * 1 9 8 5 10
			 *   i   j
			 * i,j交換後各進一步，i=j，pivot這時不能跟i=j這個位置直接交換因為不知道8到底是屬於left群組還是right群組
			 * 所有條件要有=
			 * 再次進入while中去判斷pivot要跟j交換還是跟j-1交換
			 */
			if (nums[i] > pivotValue && nums[j] < pivotValue) {
				swap(nums, i, j);
				i++;
				j--;
			}
			
			if (nums[i] <= pivotValue) {
				i++;
			}
			
			if (nums[j] >= pivotValue) {
				j--;
			}
		}
		
		swap(nums, start, j);
		
		return j;
	}
	
	public static void quickSort(int[] input, int start, int end) {
		
		if (end - start < 2) {
			return;
		}
		
		int pivotIndex = partition(input, start, end);
		
		quickSort(input, start, pivotIndex);
		quickSort(input, pivotIndex+1, end);
	}
	
	private static void swap(int[] nums, int i, int j) {
		
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}

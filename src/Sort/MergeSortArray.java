package Sort;

public class MergeSortArray {

	public static void main(String[] args) {
		
		int[] nums = {3,44,17,-34,0,56,111,87};
		mergeSort(nums);
		
		for (int num : nums) {
			System.out.print(num+",");
		}
	}

	public static void mergeSort(int[] nums) {
		
		divideAndMerge(nums, 0, nums.length-1);
	}

	private static void divideAndMerge(int[] nums, int start, int end) {
		
		if (start >= end) {
			return;
		}
		
		int mid = (start+end)/2;
		
		divideAndMerge(nums, start, mid);
		divideAndMerge(nums, mid+1, end);
		
		merge(nums, start, mid, end);
	}

	private static void merge(int[] nums, int start, int mid, int end) {
		
		System.out.println("merge " + start + " to " + mid + " + " + (mid+1) + " to " + end);
		
		int left = start;
		int right = mid+1;
		int[] temp = new int[end-start+1];
		int index = 0;
		
		while (left <= mid || right <= end) {
			
			if (left > mid) { /* 左邊用完了 */
				temp[index] = nums[right];
				right ++;
				index ++;
				continue;
			}
			
			if (right > end) { /* 右邊用完了 */
				temp[index] = nums[left];
				left ++;
				index ++;
				continue;
			}
			
			if (nums[left] < nums[right]) { /* 右>左 */
				temp[index] = nums[left];
				left ++;
			} else { /* 右<左 */
				temp[index] = nums[right];
				right ++;
			}
			index++;
		}
		
		System.arraycopy(temp, 0, nums, start, end-start+1);
	}
}

package Sort;

public class QuickSortArray {

	public static void main(String[] args) {
		int[] array = {20, 55, -15, 7, 35, 1, -22};
		
		quickSort(array);
		
		for (int i : array) {
			System.out.print(i+",");
		}
	}
	
	public static void quickSort(int[] array) {
		quickSort(array, 0, array.length-1);
	}

	private static void quickSort(int[] array, int start, int end) {
		
		if (start >= end) {
			return;
		}
		
		int pivotIndex = partition(array, start, end);
		
		quickSort(array, start, pivotIndex-1);
		quickSort(array, pivotIndex+1, end);
	}

	private static int partition(int[] nums, int start, int end) {
		
		int i = start+1;
		int j = end;
		int pivotValue = nums[start];
		
		while (i <= j) {
			
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
    
    private static void swap(int[] nums, int i, int j) {
		
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}

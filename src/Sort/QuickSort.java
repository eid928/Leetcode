package Sort;

public class QuickSort {

	public static void main(String[] args) {

		int[] intArray = {20, 55, -15, 7, 35, 1, -22};
		
		quickSort(intArray, 0, intArray.length);
		
		for (int i : intArray) {
			System.out.println(i);
		}
	}
	
	public static void quickSort(int[] input, int start, int end) {
		
		if (end - start < 2) {
			return;
		}
		
		int pivotIndex = partition(input, start, end);
		System.out.println("pivotIndex= " + pivotIndex + "\n");
		
		quickSort(input, start, pivotIndex);
		quickSort(input, pivotIndex+1, end);
	}
	
	// will return the pivot index after partition
	private static int partition(int[] input, int start, int end) {
		
		int pivotIndex = start;
		
		int pivotValue = input[pivotIndex];
		int i = start;
		int j = end;
		while (i < j) {
			
			// empty loop to find the j which smaller than pivot
			while (i < j && input[--j] >= pivotValue);
			if (i < j) {
				input[i] = input[j];
			}
			// empty loop to find the i which bigger than pivot
			while (i < j && input[++i] <= pivotValue);
			if (i < j) {
				input[j] = input[i];
			}
		}
//		System.out.println("i=" + i);
//		System.out.println("j=" + j);
		input[i] = pivotValue;
		for (int element : input) {
			System.out.print(element + ",");
		}
		
		return i;
	}
}

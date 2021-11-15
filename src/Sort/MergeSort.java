package Sort;

public class MergeSort {
	
	public static void main (String[] args) {
		
		int[] intArray = {20, 55, -15, 7, 35, 1, -22};
		int[] example = {-15, 20, 55, -22, 1, 7, 35};
		merge(example, 0, 3, 7);
		mergeSort(intArray, 0, intArray.length);
		
		for (int i : intArray) {
			System.out.println(i);
		}
	}
	
	// {20, 35, -15, 7, 55, 1, -22}
	public static void mergeSort (int[] input, int start, int end) {
		
		if (end - start < 2) {
			return;
		}
		
		int mid = (start + end) / 2;
		mergeSort(input, start, mid); // 回傳已經排好的left side
		mergeSort(input, mid, end); // 回傳已經排好的right side
		
		merge(input, start, mid, end); // 將排好的左右兩邊merge
	}
	
	// {20, 35, -15, 7, 55, 1, -22}
	public static void merge(int[] input, int start, int mid, int end) {
		
		// {-15, 20, 55, -22, 1, 7, 35}
		if (input[mid-1] <= input[mid]) { // 因為左右邊都排好了，如果左邊最大<=右邊最小，就不用排
			return;
		}
		
		int i = start;
		int j = mid;
		int[] tempArray = new int[end - start];
		int tempIndex = 0;
		while (i < mid && j < end) {
			tempArray[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
			// array[i++] = 0，實際上執行: array[i] = 0; i++
		}
//		System.out.println(tempIndex);
//		System.out.println(i);
//		for (int k : tempArray) {
//			System.out.println(k);
//		}
		// tempArray would be {-22, -15, 1, 7, 20, 35, 0}
		// tempIndex would be 6
		// i would be 3
		
		System.arraycopy(input, i, input, start+tempIndex, mid-i); // 如果剩下還沒排完的在左邊(較大)，則要將左邊沒排完的複製到最右邊(55移到最右)
		System.arraycopy(tempArray, 0, input, start, tempIndex);
	}
}

package Sort;

public class InsertionSort {
	
	public static void main(String[] args) {
		
		int[] intArray = {20, 35, -15, 7, 55, 1, -22};
		
		insertionSort(intArray);
		
		for (int i = 0; i < intArray.length; i++) {
			System.out.println(intArray[i]);
		}
	}

	public static void insertionSort(int[] array) {
		
		for (int unsortedIndex = 1; unsortedIndex < array.length; unsortedIndex ++) {
			
			int curNum = array[unsortedIndex];
			int i;
			for (i = unsortedIndex; i > 0 && array[i - 1] > curNum; i --) {
				array[i] = array[i - 1];
			}
			array[i] = curNum;
		}
	}
}

package PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestElementinaSortedMatrix {

	public static void main(String[] args) {
		/**
		 * 有一組matrix，其行與列皆為非降序的
		 * 求出排序中第k項數字
		 */
		int[][] martix = {{1,5,9},{10,11,13},{12,13,15}};
		System.out.println(kthSmallest(martix, 8));
	}
	
	public static int kthSmallest(int[][] matrix, int k) {
		
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x,y) -> y - x);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				priorityQueue.add(matrix[i][j]);
				if (priorityQueue.size() > k) {
					priorityQueue.poll();
				}
			}
		}
		
		return priorityQueue.peek();
	}

    public static int kthSmallest2(int[][] matrix, int k) {
    	
    	List<Integer> mergeList = dividAndMerge(matrix, 0, matrix.length-1);
    	
        return mergeList.get(k-1);
    }

	private static List<Integer> dividAndMerge(int[][] matrix, int start, int end) {
		
		if (start >= end) {
			List<Integer> list = new ArrayList<>();
			for (int element : matrix[start]) list.add(element);
			return list;
		}
		
		int mid = (start+end)/2;
		
		List<Integer> list1 = dividAndMerge(matrix, start, mid);
		List<Integer> list2 = dividAndMerge(matrix, mid+1, end);
		
		return merge(list1, list2);
	}

	private static List<Integer> merge(List<Integer> mergeList1, List<Integer> mergeList2) {
		
		List<Integer> mergedList = new ArrayList<>();
		int i = 0;
		int j = 0;
		
		while (i < mergeList1.size() || j < mergeList2.size()) {
			
			if (i >= mergeList1.size()) {
				mergedList.add(mergeList2.get(j));
				j++;
				continue;
			}
			
			if (j >= mergeList2.size()) {
				mergedList.add(mergeList1.get(i));
				i++;
				continue;
			}
			
			int element1 = mergeList1.get(i);
			int element2 = mergeList2.get(j);
			
			if (element1 < element2) {
				mergedList.add(element1);
				i++;
			} else {
				mergedList.add(element2);
				j++;
			}
		}
		
		return mergedList;
	}
}

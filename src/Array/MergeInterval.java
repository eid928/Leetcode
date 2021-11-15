package Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class MergeInterval {

	public static void main(String[] args) {
		
		
		int[] nums = {5,7,3,6,1,8,3,4,19};
		Arrays.sort(nums);
		Arrays.sort(null, null);
		Arrays.sort(args);
		Collections.sort(null);
		/**
		 * test push
		 */
		int[][] input = new int[][] {{1,3},{2,6},{8,10},{15,18}};
		int[][] merged = merge(input);
		for (int[] is : merged) {
			System.out.print("[");
			System.out.print(is[0] + ", " + is[1]);
			System.out.print("] ");
		}
	}

	public static int[][] merge(int[][] intervals) {
		
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
		LinkedList<int[]> mergedList = new LinkedList<>();
		
		for (int[] interval : intervals) {
			if (mergedList.isEmpty() || !couldBeMerged(mergedList.getLast(), interval)) {
				mergedList.add(interval);
			} else {
				int[] mergeInterval = mergeInterval(mergedList.getLast(), interval);
				mergedList.getLast()[0] = mergeInterval[0];
				mergedList.getLast()[1] = mergeInterval[1];
			}
		}
		
        return mergedList.toArray(new int[mergedList.size()][]);
    }
	
	public static int[] mergeInterval(int[] interval1, int[] interval2) {
		
		int start = Math.min(interval1[0], interval2[0]);
		int end = Math.max(interval1[1], interval2[1]);
		
		return new int[] {start, end};
	}
	
	public static boolean couldBeMerged(int[] interval1, int[] interval2) {
		
		return interval1[1] >= interval2[0];
	}
}

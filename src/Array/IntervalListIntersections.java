package Array;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IntervalListIntersections {

	public static void main(String[] args) {
		/**
		 * 給定兩組interval array
		 * 請求出這兩組之間所有的重疊interval
		 */
		int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
		int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
		
		int[][] result = intervalIntersection(firstList, secondList);
		for (int[] r : result) {
			System.out.println("["+r[0]+","+r[1]+"], ");
		}
	}

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        /**
         * merge sort
         * 兩組array都是已經sorted
         * 所以作法類似merge sort
         * 每次查看兩組間interval[x,y]，x值比較小的interval => curInterval
         * 一起插入同一個軸上
         * stack儲存目前軸上最後一段interval
         * 若目前軸上最後一段與curInterval有重疊，則merge後加回去stack並紀錄intersection
         * 若無重疊則直接把curInterval加進stack
         */
    	if (firstList.length == 0 || secondList.length == 0) {
    		int[][] result = new int[0][2];
			return result;
		}
    	
    	int i = 0;
    	int j = 0;
    	Stack<int[]> stack = new Stack<int[]>();
    	List<int[]> result = new LinkedList<int[]>();
    	
    	while (i < firstList.length || j < secondList.length) {
    		
    		int[] lastInterval = stack.isEmpty() ? null : stack.peek();
    		
    		if (i >= firstList.length) {
				
				int[] mergedInterval = merge(lastInterval, secondList[j], result);
				stack.add(mergedInterval);
				j++;
				continue;
			}
    		if (j >= secondList.length || firstList[i][0] < secondList[j][0]) {
				
				int[] mergedInterval = merge(lastInterval, firstList[i], result);
				stack.add(mergedInterval);
				i++;
				continue;
			}
    		
    		if (firstList[i][0] >= secondList[j][0]) {
    			int[] mergedInterval = merge(lastInterval, secondList[j], result);
				stack.add(mergedInterval);
				j++;
			} else {
				int[] mergedInterval = merge(lastInterval, firstList[i], result);
				stack.add(mergedInterval);
				i++;
			}
    	}
    	return result.toArray(new int[result.size()][2]);
    }

	private static int[] merge(int[] lastInterval, int[] newInterval, List<int[]> result) {
		
		int[] mergedInterval = new int[2];
		int[] intersection = new int[2];
		
		if (lastInterval == null) {
			return newInterval;
		}
		
		if (newInterval[0] <= lastInterval[1]) {
			mergedInterval[0] = lastInterval[0];
			mergedInterval[1] = Math.max(newInterval[1], lastInterval[1]);
			
			intersection[0] = newInterval[0];
			intersection[1] = Math.min(newInterval[1], lastInterval[1]);
			result.add(intersection);
		} else {
			mergedInterval = newInterval;
		}
		return mergedInterval;
	}
}

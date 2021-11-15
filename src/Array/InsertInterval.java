package Array;

import java.util.ArrayList;

import java.util.List;

public class InsertInterval {

	public static void main(String[] args) {
		InsertInterval test = new InsertInterval();
		
		int[][] input = {{1,3},{6,9}};
		int[] newInterval = {2,5};
		int[][] answer = test.insert(input, newInterval);
		for(int i = 0; i < answer.length; i ++) {
			System.out.print("[" + answer[i][0] + ", " + answer[i][1] + "], ");
		}
	}
	
	public int[][] insert(int[][] intervals, int[] newInterval) {
		// Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
		// Output: [[1,2],[3,10],[12,16]]
		// Input: intervals = [], newInterval = [5,7]
		// Output: [[5,7]]
		// Input: intervals = [[1,5]], newInterval = [2,3]
		// Output: [[1,5]]
		// Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
		// Output: [[1,5],[6,9]]
		// Input: [[1,5]]], newInterval = [6,8]
		// Output: [[1,5],[6,8]]
		
		int[] startIndex = {0, 0};
		boolean startIndexFound = false;
		int[] endIndex = {0, 0};
		boolean endIndexFound = false;
		
		if (intervals.length == 0) {
			return new int[][] {newInterval};
		}
		
		for (int i = 0; i < intervals.length; i++) {  // 找到第一個大於newInterval兩個端點的index: startIndex, endIndex
			for (int j = 0; j < 2; j++) {
				if (newInterval[0] <= intervals[i][j] && !startIndexFound) {
					startIndex[0] = i;
					startIndex[1] = j;
					startIndexFound = true;
				}
				if (newInterval[1] < intervals[i][j] && !endIndexFound) {
					endIndex[0] = i;
					endIndex[1] = j;
					endIndexFound = true;
				}
			}
		}
		System.out.println("startindex = " + startIndex[0] + ", " + startIndex[1]);
		System.out.println("endindex = " + endIndex[0] + ", " + endIndex[1]);
		
		
		int[] combinedInterval = new int[2];
		
		if (startIndex[1] == 1) { // 新interval開始於interval中
			combinedInterval[0] = intervals[startIndex[0]][0];
		} else { // 新interval開始於interval之間
			combinedInterval[0] = newInterval[0];
		}
		
		if (endIndex[1] == 1) { // 新interval結束於interval中
			combinedInterval[1] = intervals[endIndex[0]][1];
		} else { // 新interval結束於interval之間
			combinedInterval[1] = newInterval[1];
		}
		
		System.out.println("combinedInterval = " + combinedInterval[0] + ", " + combinedInterval[1]);
		
		int insertIndex = 0;
		
		List<int[]> answerList = new ArrayList<>();
		for (int i = 0; i < intervals.length; i++) {
			
			if (combinedInterval[0] > intervals[i][0]) {
				insertIndex ++;
			}
			
			if (intervals[i][1] < combinedInterval[0] || intervals[i][0] > combinedInterval[1]) {
				answerList.add(intervals[i]);
			}
		}
		
		System.out.println(answerList);
		answerList.add(insertIndex, combinedInterval);
		System.out.println(answerList);
		
		
		return answerList.toArray(new int[answerList.size()][2]);
        
    }
	
}

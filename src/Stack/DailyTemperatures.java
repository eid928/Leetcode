package Stack;

import java.util.Stack;

public class DailyTemperatures {

	public static void main(String[] args) {
		/**
		 * 給定一組每日氣溫的array
		 * 求出每一天在最短幾天後可以得到更高的氣溫
		 * 以array回傳
		 */
		int[] temps = {73,74,75,71,69,72,76,73};
		int[] results = dailyTemperatures(temps);
		for (int result : results) {
			System.out.print(result+",");
		}
		
		temps = new int[] {30,40,50,60};
		results = dailyTemperatures(temps);
		for (int result : results) {
			System.out.print(result+",");
		}
	}

    public static int[] dailyTemperatures(int[] temperatures) {
    	/**
    	 * 遞減stack
    	 * stack中儲存temperatures的index
    	 * 且其中將逐步遞減，stack peak為stack中最低的氣溫
    	 * 當遍歷到比stack peak高的氣溫時，才去更新stack中的每一天
    	 * 例如有一段遞減的氣溫
    	 * 75,71,69，這三天都還沒找到更暖的氣溫，於是都加進stack但先不更新result
    	 * 直到找到了72 > stack peak(69)，於是逐步更新result中69,71這兩天
    	 * 再將72加進stack，變成75,72
    	 */
    	int[] results = new int[temperatures.length];
    	Stack<Integer> stack = new Stack<>();
    	
    	for (int i = 0; i < temperatures.length; i++) {
    		
    		while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
    			
    			int index = stack.pop();
    			results[index] = i - index;
    		}
    		
    		stack.push(i);
    	}
    	
        return results;
    }
}

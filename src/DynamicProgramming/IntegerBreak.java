package DynamicProgramming;

import java.util.Arrays;

public class IntegerBreak {

	public static void main(String[] args) {
		/**
		 * 給一個正整數
		 * 將這個正整數拆成數個正整數的總和，再將這數個正整數相乘得到值
		 * 求出這個值最大為多少
		 */
		System.out.println(integerBreak(2)); // 1, 2 = 1 + 1, 1 × 1 = 1.
		System.out.println(integerBreak(10)); // 36, 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
	}

    public static int integerBreak(int n) {
    	/**
    	 * 好像不算dp，純數學
    	 * 若要一個數字拆成k份，則要盡量平分成k個數字，其乘積才會是最大
    	 * 例如10若指定要拆成4份，則平分的拆法2,2,3,3，其乘積絕對會大於其他拆法，例如3,4,2,1
    	 * 則要解這題，就試著把n拆成2~n份，看拆成幾分的時候結果最大
    	 */
    	int max = 0;
    	
    	for (int i = 2; i <= n; i++) { /* 拆成i份 */
    		
    		int[] breakArr = new int[i];
    		int smallPiece = n / i; /* 要平分，每一份的基本盤 */
    		Arrays.fill(breakArr, smallPiece);
    		
    		int reamin = n % i; /* 餘數，加回去 */
    		for (int j = 0; j < reamin; j++) {
    			breakArr[j] += 1;
    		}
    		
    		int product = 1;
    		for (int j = 0; j < breakArr.length; j++) {
    			
    			System.out.print(breakArr[j]+",");
    			product *= breakArr[j];
    		}
    		System.out.println();
    		max = Math.max(max, product);
    	}
    	
        return max;
    }
}

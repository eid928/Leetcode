package Special;

public class SingleNumber {

	public static void main(String[] args) {
		/**
		 * 給定一組整數array
		 * array中的數字皆會出現兩次
		 * 只有一個數字只會出現一次
		 * 請找出只出現一次的數字
		 * 時間複雜度:O(n), 空間複雜度:O(1)
		 */
		int[] nums = {4,1,2,1,2};
		
		System.out.println(singleNumber(nums));
	}

    public static int singleNumber(int[] nums) {
    	/**
    	 * 要求空間複雜度為constant，所以無法使用hash
    	 * 先看以下XOR的
    	 */
    	System.out.println(0^0);
		System.out.println(0^1);
		System.out.println(1^0);
		System.out.println(1^1);
		
		System.out.println(2^2);
		System.out.println(4^2);
		System.out.println(4^2^2);
		/**
		 * 當兩個整數一樣時，XOR為0
		 * 不同時，XOR為相加
		 * 而多個整數XOR時，一樣的兩個數會XOR為0
		 * 最合的結果則是唯一單獨的數
		 */
		
    	int result = 0;
    	
    	for (int num : nums) result ^= num;
    	
        return result;
    }
}

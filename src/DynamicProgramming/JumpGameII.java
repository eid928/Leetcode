package DynamicProgramming;

public class JumpGameII {

	public static void main(String[] args) {
		/**
		 * 給定一array，array中的數字表示能往右跳的最大距離
		 * 請計算要跳到最後一格，最少需要幾步？
		 * 假設每組array都能跳到最後
		 */
		int[] nums = {2,3,1,1,4};
		int[] nums2 = {2,3,0,1,4};
		int[] nums3 = {2,1,1,1,1};
		System.out.println(jump(nums));
		System.out.println(jump(nums2));
		System.out.println(jump(nums3));
	}

    public static int jump(int[] nums) {
    	/**
    	 * dp，但解法很特殊，非常靠腰
    	 * 遍歷array，但很不直覺的跟jumpGame不一樣，從最前面遍歷
    	 * curfarthest紀錄目前最遠能到達的範圍
    	 * 這題實際上是讓每次都跳最遠的greedy作法
    	 * 
    	 * last是目前跳到的位置
    	 * 當i超過last的時候，我就跳到目前最遠
    	 * 並更新curfarthest
    	 * 
    	 * 以2,3,0,1,4為例
    	 * 當i在2的時候
    	 * 最遠能到0，last=2
    	 * 
    	 * 當i在3的時候，超過last了，必須要跳躍
    	 * 此時直接讓last跳到最遠，也就是跳到0
    	 * 並更新在3的時候能跳到最遠為4
    	 * 
    	 * 我雖然跳到0了，但能想像下一次跳的時候，我最遠也能跳到4（反正2,3,0都是勢力範圍，回到3再跳的感覺），是符合流程的
    	 * 
    	 */
    	int curfarthest = 0;
    	int last = 0;
    	int jumpCount = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		
    		if (i > last) {
				last = curfarthest;
				jumpCount++;
			}
    		curfarthest = Math.max(curfarthest, i+nums[i]);
    	}
    	
    	return jumpCount;
	}
}

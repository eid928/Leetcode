package DynamicProgramming;

public class ClimbingStairs {
	public int climbStairs(int n) {
        
		if (n == 1) {
            return 1;
        }
		if (n == 2) {
			return 2;
		}
		
		int[] everyStair = new int[n];
		everyStair[0] = 1;
		everyStair[1] = 2;
		for (int i = 2; i < everyStair.length; i ++) {
			everyStair[i] = everyStair[i-2] + everyStair[i-1];
		}
		
        return everyStair[n-1];
    }
}

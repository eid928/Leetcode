package DynamicProgramming;

public class CanJump {
	
    public static boolean canJumpMethod(int[] nums) {
        // O(n^2)
        int goal = nums.length - 1;
        while(goal > 0) {
            goal = smallestIndexToJump(nums, goal);
        }
        return goal == 0;
    }
    public static int smallestIndexToJump(int[] nums, int to) {
        for (int i = 0; i < to; i += 1) {
            if(i + nums[i] >= to) {
                return i;
            }
        }
        return -1;
    }
    public static boolean canJump2(int[] nums) {
        // O(n)
        int goal = nums.length - 1;
        for (int i = goal; i >= 0; i -= 1) {
            if(i + nums[i] >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
    public static void main(String[] args) {
        int [] nums = {3,2,1,0,4};
        System.out.println(smallestIndexToJump(nums, 4));
        System.out.println(canJumpMethod(nums));
    }
}

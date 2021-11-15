package BinarySearch;

public class FindFirstandLastPositionofElementinSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        int right = nums.length - 1;
        int left = 0;
        int[] ans = {-1, -1};

        while(left < right) {
            int index = (left + right)/2;
            System.out.println("left = " + left + ", right = " + right + ", index = " + index);

            if(nums[index] == target || nums[index] > target) {
                right = index;
            }
            else {
                left = index + 1;
            }
        }
        System.out.println("left = " + left + ", right = " + right + ", =lefthand");
        ans[0] = left;
        right = nums.length - 1;
        left = 0;
        while(left < right) {
            int index = (left + right + 1)/2;
            System.out.println("left = " + left + ", right = " + right + ", index = " + index);
            if(nums[index] == target || nums[index] < target) {
                left = index;
            }
            else {
                right = index - 1;
            }
        }
        System.out.println("left = " + left + ", right = " + right + ", =righthand");
        ans[1] = right;
        if(ans[0] > ans[1] || nums[ans[0]] != target) {
            return new int[] {-1, -1};
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] b = {};
        int target = 99;
        /*for(int i = 0; i < searchRange(a, target).length; i ++) {
            System.out.println(searchRange(a, target)[i]);
        }*/
        int[] ans = searchRange(b, target);
        for(int i = 0; i < ans.length; i ++) {
            System.out.println(ans[i]);
        }
    }
}

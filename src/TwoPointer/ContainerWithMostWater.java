package TwoPointer;

public class ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int ans = 0;
        int thisArea;
        int i = 0;
        int j = height.length - 1;
        while(i != j) {
            thisArea = Math.min(height[i], height[j]) * (j - i);
            System.out.println("i = " + i + ", j = " + j + ", thisArea = " + thisArea);
            ans = Math.max(ans, thisArea);
            if(height[i] < height[j]) {
                i ++;
            }
            else {
                j --;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] a = {1,3,2,5,25,24,5};
        System.out.println(maxArea(a));
    }
}

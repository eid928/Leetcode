package Array;

public class MergeSortedArray {

	public static void main(String[] args) {
		/**
		 * 給定兩sorted array，要將他們合併至nums1內
		 * nums1的大小會是m+n，後面的0不算是element
		 */
		
		int[] nums1 = {1,2,3,0,0,0};
		int m = 3;
		int[] nums2 = {2,5,6};
		int n = 3;
		
		merge(nums1, m, nums2, n);
		for (int i = 0; i < nums1.length; i++) {
			System.out.print(nums1[i] + ",");
		}
	}
	
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
    	/**
    	 * 由於nums1後面的0都是空間
    	 * 所以從後面開始排可以減少時間
    	 * 從m-1, n-1開始往前遍歷
    	 * 大的就塞到最後(last)
    	 */
        int last = nums1.length-1;
        int i = m-1;
        int j = n-1;
        while (i >= 0 || j >= 0) {
        	
        	if (i < 0) {
        		nums1[last] = nums2[j];
				last--;
				j--;
				continue;
			}
        	
        	if (j < 0) {
        		nums1[last] = nums1[i];
				last--;
				i--;
				continue;
			}
        	
        	if (nums1[i] > nums2[j]) {
				nums1[last] = nums1[i];
				last--;
				i--;
			} else {
				nums1[last] = nums2[j];
				last--;
				j--;
			}
        }
    }
}

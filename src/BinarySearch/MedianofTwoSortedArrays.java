package BinarySearch;

public class MedianofTwoSortedArrays {

	public static void main(String[] args) {
		
		int[] nums1 = {1,3};
		int[] nums2 = {2,4,5};
		double ans = findMedianSortedArrays(nums1, nums2);
		System.out.println(ans);
	}
	
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	/**
    	 * 題目要求時間複雜度為O(log (m+n))
    	 * 所以不可以直接將兩個array merge，會超出時間
    	 * 用binary search的概念來做
    	 * 首先，假設merge起來的array，要找到中位數
    	 * 不管是奇數還是偶數，其中位數都等於
    	 * 第(m+n+1)/2的數字 和
    	 * 第(m+n+2)/2的數字 的平均
    	 * 此時我們需要寫一個funciton來求出兩sorted array第k個數字
    	 */
    	int m = nums1.length;
    	int n = nums2.length;
    	
    	int left = (m+n+1)/2;
    	int right = (m+n+2)/2;
    	
        return (findKthElement(nums1, 0, nums2, 0, left) + findKthElement(nums1, 0, nums2, 0, right)) / 2.0;
    }
    
	private static int findKthElement(int[] nums1, int i, int[] nums2, int j, int k) {
		/**
		 * 此function求出兩sorted array第k個數字
		 * 而i,j分別為指向nums1,nums2的起始位置
		 * 
		 * 先看general的例子
		 * 例如有兩個sorted array長度為20，需要找到第20個數字
		 * 此時起點都是0，i=0,j=0
		 * arr1 = [..............]
		 * arr2 = [..............]
		 * 則分別找從起點數來第k/2個數字，即i+k/2-1, j+k/2-1
		 * arr1 = [......a.......]
		 *               ^
		 * arr2 = [......b.......]
		 *               ^
		 * 若a < b
		 * 則目標的第20個數字一定不會出現在arr1起點到a的區間
		 * 因為就算合併起來之後，a較b小，一定不會是第20個
		 * 所以起點到a的區間就可以“淘汰”掉，i就從0跑到i+k/2
		 * arr1 = [...淘汰........]
		 *                i=10
		 * arr2 = [..............]
		 *         j=0
		 * 而第一輪淘汰了k/2=10個數字
		 * 所以下一輪就變成arr1（10~最後）和 arr2（0~最後）取 第10個數字 => 遞迴
		 */
		if (i >= nums1.length) { /* nums1被通通淘汰了 */
			return nums2[j + k -1];
		}
		if (j >= nums2.length) { /* nums2被通通淘汰了 */
			return nums1[i + k -1];
		}
		if (k == 1) {
			return Math.min(nums1[i], nums2[j]);
		}
		
		int midVal1 = Integer.MAX_VALUE;
		int midVal2 = Integer.MAX_VALUE;;
		
		if (i + k/2 - 1 < nums1.length) {
			midVal1 = nums1[i + k/2 - 1];
		}
		if (j + k/2 - 1 < nums2.length) {
			midVal2 = nums2[j + k/2 - 1];
		}
		
		if (midVal1 < midVal2) {
			
			return findKthElement(nums1, i + k/2, nums2, j, k - k/2);
		} else {
			
			return findKthElement(nums1, i, nums2, j + k/2, k - k/2);
		}
	}
}

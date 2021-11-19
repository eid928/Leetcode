package Tree;

import datastructure.TreeNode;

public class DistributeCoinsinBinaryTree {

	public static void main(String[] args) {
		/**
		 * 給定一棵tree，節點的數值代表該節點擁有多少硬幣
		 * 請問，若要將所有硬幣平均分配給所有節點，則硬幣們最少需要移動的量為多少
		 * 硬幣的數量會剛好等於節點數
		 */
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		
		System.out.println(distributeCoins(root));
		
		root = new TreeNode(0);
		root.left = new TreeNode(3);
		root.right = new TreeNode(0);
		
		System.out.println(distributeCoins(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(0);
		root.right = new TreeNode(2);
		
		System.out.println(distributeCoins(root));
		
		root = new TreeNode(1);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.left.right = new TreeNode(3);
		
		System.out.println(distributeCoins(root));
	}
	
	private static int path;

    public static int distributeCoins(TreeNode root) {
        
    	path = 0;
    	dfs(root);
    	
    	return path;
    }

	private static int dfs(TreeNode node) {
		/**
		 * 從根節點不好思考，可以從葉節點來思考
		 * 首先考慮這樣的樹:
		 *   0
		 *  /\
		 * 2  0
		 * 從1的角度看
		 * 左節點可以提供1塊硬幣，path+1
		 * 右節點可以提供-1塊硬幣，path+1 (需要一塊硬幣)
		 * 左節點剛好能彌補右節點，總共path=2
		 * 但自己也需要一塊硬幣，於是要回傳1+(-1)+(-1)=-1給自己的父節點
		 * 
		 * 今天假如左節點提供的1塊硬幣是給自己，則path=1
		 * 但右節點需要的-1，還是需要跟父節點要，父節點給的硬幣經過時path還是+1，所以也沒差
		 * 
		 * 回傳: 這個節點總結算後可提供的硬幣
		 * path: 左右節點能提供硬幣的絕對值總和
		 */
		if (node == null) {
			return 0;
		}
		
		int leftChildOffer = dfs(node.left);
		int rightChildOffer = dfs(node.right);
		int thisOffer = node.val - 1;
		
		path += Math.abs(rightChildOffer) + Math.abs(leftChildOffer);
		
		System.out.println(node.val+", offer: "+(leftChildOffer + rightChildOffer + thisOffer));
		return leftChildOffer + rightChildOffer + thisOffer;
	}
}

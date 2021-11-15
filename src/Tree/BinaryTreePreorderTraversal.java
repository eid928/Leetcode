package Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        traversal(root, ans);
        return ans;
    }

    public static void traversal(TreeNode node, List<Integer> ans) {
        if(node == null) {
            return;
        }
        ans.add(node.val);
        traversal(node.left, ans);
        traversal(node.right, ans);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(3);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(2);
        TreeNode d = null;
        a.left = b;
        a.right = c;
        System.out.println(preorderTraversal(d));
        System.out.println(preorderTraversal(a));
        System.out.println(preorderTraversal(d));
    }
}

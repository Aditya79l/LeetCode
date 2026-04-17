/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> l;
    public List<Integer> postorderTraversal(TreeNode root) {
        l = new ArrayList<>();
        inorder(root);
        return l;
    }

    public void inorder(TreeNode root){

        if(root == null)
        return;

        inorder(root.left);
        inorder(root.right);
        l.add(root.val);
    }
}
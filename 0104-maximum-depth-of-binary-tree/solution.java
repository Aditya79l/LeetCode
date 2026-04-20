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
    int max;
    public int maxDepth(TreeNode root) {
        max = 0;
        depth(root,0);
        return max;
        
    }

    public void depth(TreeNode root, int dist){

        if(root == null){
            max = Math.max(max,dist);
            return;
        }

        depth(root.left,dist+1);
        depth(root.right,dist+1);
     }
}
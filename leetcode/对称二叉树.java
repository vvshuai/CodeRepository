/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return judge(root.left, root.right);
    }
    public static boolean judge(TreeNode t1, TreeNode t2){
        if(t1==null && t2==null){
            return true;
        }
        if(t1==null || t2==null){
            return false;
        }
        boolean x = t1.val==t2.val;
        if(x==true){
            return judge(t1.left, t2.right)&&judge(t1.right, t2.left);
        }
        return false;
    }
}

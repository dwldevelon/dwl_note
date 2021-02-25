package dwl.TreeNodeSolution;

/**
 * @author wenlong.ding
 * @date 2021/2/19 18:48
 */
/**
 * Definition for a binary tree node.
 **/

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }


    class Solution {
        private class ResultType {

            boolean found;  // 是否找到

            int val;  // 节点数目

            ResultType(boolean found, int val) {
                this.found = found;
                this.val = val;
            }
        }

        public int kthSmallest(TreeNode root, int k) {
            return kthSmallestHelper(root, k).val;
        }

        private ResultType kthSmallestHelper(TreeNode root, int k) {
            if (root == null) {
                return new ResultType(false, 0);
            }

            ResultType left = kthSmallestHelper(root.left, k);

            // 左子树找到，直接返回
            if (left.found) {
                return new ResultType(true, left.val);
            }

            // 左子树的节点数目 = K-1，结果为 root 的值
            if (k - left.val == 1) {
                return new ResultType(true, root.val);
            }

            // 右子树寻找
            ResultType right = kthSmallestHelper(root.right, k - left.val - 1);
            if (right.found) {
                return new ResultType(true, right.val);
            }

            // 没找到，返回节点总数
            return new ResultType(false, left.val + 1 + right.val);
        }
    }

}


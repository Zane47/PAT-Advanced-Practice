package pat.advanced.tree.binarytraversal;

import java.io.IOException;
import java.util.*;

/**
 * all the keys in a binary tree are distinct positive integers.
 * 都是唯一的val
 * 已知postorder和inorder，输出 level order
 * <p>
 * 7
 * 2 3 1 5 7 6 4
 * 1 2 3 4 5 6 7
 * <p>
 * 4 1 6 3 5 7 2
 */

public class TreeTraversals_1020 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] postOrder = new int[N];
        for (int i = 0; i < N; i++) {
            postOrder[i] = sc.nextInt();
        }
        int[] inOrder = new int[N];
        for (int i = 0; i < N; i++) {
            inOrder[i] = sc.nextInt();
        }

        // 通过后序遍历和中序遍历来建树
        // 1. postorder的最后一个元素是根元素
        // 2. 通过根元素在inorder中的位置来划分左右子树
        // 3. 对于inorder和postorder中的左右子树递归调用
        TreeNode root = buildTree(postOrder, inOrder);


        // level order, 层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.val);
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            if (!queue.isEmpty()) {
                System.out.print(" ");
            }
        }
    }

    private static TreeNode buildTree(int[] postOrder, int[] inOrder) {
        if (postOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        // postorder的最后一个元素就是根元素
        int tempRoot = postOrder[postOrder.length - 1];

        // 因为每个元素都是唯一的, 那么查找root在inOrder中的位置
        int indexOfTempRoot = 0;
        for (; indexOfTempRoot < inOrder.length; indexOfTempRoot++) {
            if (inOrder[indexOfTempRoot] == tempRoot) {
                break;
            }
        }

        TreeNode treeNode = new TreeNode(tempRoot);
        // 切分数组, 左闭右开
        treeNode.left = buildTree(
                slice(postOrder, 0, indexOfTempRoot),
                slice(inOrder, 0, indexOfTempRoot));
        treeNode.right = buildTree(
                slice(postOrder, indexOfTempRoot, postOrder.length - 1),
                slice(inOrder, indexOfTempRoot + 1, inOrder.length));


        return treeNode;
    }

    /**
     * 数组切片
     * 左闭右开
     */
    private static int[] slice(int[] array, int left, int right) {
        return Arrays.copyOfRange(array, left, right);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}

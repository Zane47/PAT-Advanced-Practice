package pat.advanced.tree.binarytraversal;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**ac
 * 中序遍历树可以由非递归的方式实现(使用stacak栈),
 * Your task is to give the postorder traversal sequence of this tree.
 * <p>
 * 6
 * Push 1
 * Push 2
 * Push 3
 * Pop
 * Pop
 * Push 4
 * Pop
 * Pop
 * Push 5
 * Push 6
 * Pop
 * Pop
 * <p>
 * ->
 * 3 4 2 6 5 1
 * <p>
 * 重点是, push的循序就是preOrder, pop的顺序就是inOrder
 * 转换成已知preOrder和inOrder求postOrder
 * <p>
 * push顺序: 123456
 * pop顺序:324165
 */
public class TreeTraversalsAgain_1086 {

    private static int N;
    // 输出的个数
    private static int __num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // <=30, the total number of nodes in a tree (and hence the nodes are numbered from 1 to N
        N = sc.nextInt();

        // Then 2N lines follow, each describes a stack operation in the format:
        // "Push X" where X is the index of the node being pushed onto the stack;
        // or "Pop" meaning to pop one node from the stack.

        int[] preOrder = new int[N];
        int preOrderIndex = 0;
        int[] inOrder = new int[N];
        int inOrderIndex = 0;

        Stack<Integer> stack = new Stack<>();

        // 通过input的栈操作建树, 然后再后续遍历
        for (int i = 0; i < 2 * N; i++) {
            String input = sc.nextLine();
            if (input.contains("Push")) {
                int nodeValue = Integer.parseInt(input.split(" ")[1]);
                stack.push(nodeValue);
                preOrder[preOrderIndex++] = nodeValue;
            } else if (input.contains("Pop")) {
                Integer nodeValue = stack.pop();
                inOrder[inOrderIndex++] = nodeValue;
            }
        }

        while (!stack.isEmpty()) {
            inOrder[inOrderIndex++] = stack.pop();
        }

        TreeNode root = createTree(preOrder, inOrder);


        // output:
        // For each test case,
        // print the postorder traversal sequence of the corresponding tree in one line.
        // A solution is guaranteed to exist.
        // All the numbers must be separated by exactly one space,
        // and there must be no extra space at the end of the line.
        postOrder(root);

    }

    /**
     * 根据先序序列和中序序列, 构造树
     *
     * @param preOrder
     * @param inOrder
     * @return
     */
    private static TreeNode createTree(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }
        // preOrder第一个元素是根节点
        int tempRootVal = preOrder[0];

        // 找这个tempRoot在inOrder的位置, 分成左右子树
        int indexOfInOrder = 0;

        for (indexOfInOrder = 0; indexOfInOrder < inOrder.length; indexOfInOrder++) {
            if (inOrder[indexOfInOrder] == tempRootVal) {
                break;
            }
        }

        TreeNode treeNode = new TreeNode(tempRootVal);

        // 划分区间, 统一前开后闭
        // 左子树postOrder: [1, indexOfInOrder] -> [1, indexOfInOrder+1)
        // 左子树inOrder:[0, indexOfInOrder)
        treeNode.leftChild = createTree(
                slice(preOrder, 1, indexOfInOrder + 1),
                slice(inOrder, 0, indexOfInOrder));

        // 右子树postOrder:[indexOfInOrder+1, preOrder.length)
        // 右子树inOrder:[indexOfInOrder+1, inOrder.length)
        treeNode.rightChild = createTree(
                slice(preOrder, indexOfInOrder + 1, preOrder.length),
                slice(inOrder, indexOfInOrder + 1, inOrder.length));


        return treeNode;
    }

    /**
     * 数组切片, 前开后闭
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private static int[] slice(int[] nums, int left, int right) {
       return Arrays.copyOfRange(nums, left, right);
    }


    /**
     * 后序遍历
     *
     * @param root
     */
    private static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 左右根
        postOrder(root.leftChild);
        postOrder(root.rightChild);
        System.out.print(root.value);
        __num++;
        if (__num < N) {
            System.out.print(" ");
        }
    }


    /**
     * TreeNode class
     */
    static class TreeNode {
        int value;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(int val) {
            this.value = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.value = val;
            this.leftChild = left;
            this.rightChild = right;
        }

    }

}

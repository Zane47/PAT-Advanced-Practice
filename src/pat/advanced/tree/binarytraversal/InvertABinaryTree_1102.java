package pat.advanced.tree.binarytraversal;


import java.util.*;

/**ac
 * 重点:
 * 后序遍历翻转左右子树
 *
 * 颠倒二叉树
 * <p>
 * 8
 * 1 -
 * - -
 * 0 -
 * 2 7
 * - -
 * - -
 * 5 -
 * 4 6
 * ->
 * 3 7 2 6 4 0 5 1
 * 6 5 7 4 3 2 0 1
 * <p>
 * print in the first line the level-order,
 * and then in the second line the in-order traversal sequences of the inverted tree.
 * There must be exactly one space between any adjacent numbers,
 * and no extra space at the end of the line.
 * <p>
 * <p>
 * 可以看出, 题目直接给的是点左右结点的编号关系, 所以直接使用静态的方式
 */
public class InvertABinaryTree_1102 {

    // private static final int MAXN = 110;

    private static int N;

    // 用来控制print的时候最后一位不要输出空格
    private static int __num = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N<=10
        N = sc.nextInt();

        // 初始化静态二叉树
        StaticTreeNode[] treeNodes = new StaticTreeNode[N];
        for (int i = 0; i < N; i++) {
            StaticTreeNode node = new StaticTreeNode();
            treeNodes[i] = node;
        }

        // 输入的数字中, 没有输入的那个数字, 不是其他结点的子节点的, 那么就是更接地那
        boolean[] notRoot = new boolean[N];
        Arrays.fill(notRoot, false);

        // null用-1
        for (int i = 0; i < N; i++) {
            String next1 = sc.next();
            String next2 = sc.next();
            if ("-".equals(next1)) {
                treeNodes[i].left = -1;
            } else {
                treeNodes[i].left = Integer.parseInt(next1);
                notRoot[treeNodes[i].left] = true;
            }
            if ("-".equals(next2)) {
                treeNodes[i].right = -1;
            } else {
                treeNodes[i].right = Integer.parseInt(next2);
                notRoot[treeNodes[i].right] = true;
            }
        }

        // 根节点编号
        int root = searchRoot(treeNodes, notRoot);

        // reverse
        invertBinaryTreeByPostOrder(treeNodes, root);

        // levelOrderPrint
        levelOrder(treeNodes, root);

        __num = 0;
        System.out.println();

        // in-order
        inOrder(treeNodes, root);
    }

    /**
     * 翻转invert, 后序遍历翻转
     *
     * @param treeNodes
     * @param root
     */
    private static void invertBinaryTreeByPostOrder(StaticTreeNode[] treeNodes, int root) {

        if (root == -1) {
            return;
        }

        invertBinaryTreeByPostOrder(treeNodes, treeNodes[root].right);
        invertBinaryTreeByPostOrder(treeNodes, treeNodes[root].left);
        swapLeftRight(treeNodes, root);
    }



    /**
     * 查找根节点编号
     *
     * @param treeNodes
     * @param notRoot
     * @return
     */
    private static int searchRoot(StaticTreeNode[] treeNodes, boolean[] notRoot) {
        for (int i = 0; i < treeNodes.length; i++) {
            if (!notRoot[i]) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 层次遍历
     *
     * @param treeNodes
     * @param root
     */
    private static void levelOrder(StaticTreeNode[] treeNodes, int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            System.out.print(now);
            __num++;
            if (__num < N) {
                System.out.print(" ");
            }
            if (treeNodes[now].left != -1) {
                queue.add(treeNodes[now].left);
            }
            if (treeNodes[now].right != -1) {
                queue.add(treeNodes[now].right);
            }
        }

    }

    /**
     * 中序遍历
     *
     * @param treeNodes
     * @param root
     */
    private static void inOrder(StaticTreeNode[] treeNodes, int root) {
        if (root == -1) {
            return;
        }
        // 左根右
        inOrder(treeNodes, treeNodes[root].left);

        System.out.print(root);
        __num++;
        if (__num < N) {
            System.out.print(" ");
        }

        inOrder(treeNodes, treeNodes[root].right);
    }

    private static void swapLeftRight(StaticTreeNode[] treeNodes, int root) {
        int temp = treeNodes[root].left;
        treeNodes[root].left = treeNodes[root].right;
        treeNodes[root].right = temp;
    }

    static class StaticTreeNode {
        int left;
        int right;
    }

}

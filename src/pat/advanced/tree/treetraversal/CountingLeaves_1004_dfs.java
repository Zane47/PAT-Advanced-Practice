package pat.advanced.tree.treetraversal;


import java.util.*;

/**ac
 * count those family members who have no child.
 * <p>
 * 每一层有多少个leafNode
 * <p>
 * 2 1
 * 01 1 02
 * ->
 * 0 1
 * <p>
 * 用1094的测试数据来测试
 * 23 13
 * 21 1 23
 * 01 4 03 02 04 05
 * 03 3 06 07 08
 * 06 2 12 13
 * 13 1 21
 * 08 2 15 16
 * 02 2 09 10
 * 11 2 19 20
 * 17 1 22
 * 05 1 11
 * 07 1 14
 * 09 1 17
 * 10 1 18
 * -> 0 1 0 7 1 1
 */
public class CountingLeaves_1004_dfs {

    private static int[] __leaf;

    private static int __maxHeight = 0;

    public static void main(String[] args) {
        // input
        Scanner sc = new Scanner(System.in);

        // 0<N<100, the number of nodes in a tree,
        int N = sc.nextInt();
        // and M (<N), the number of non-leaf nodes
        int M = sc.nextInt();

        // root ID to be 01.
        TreeNode[] treeNodes = new TreeNode[N + 1];
        for (int i = 0; i <= N; i++) {
            treeNodes[i] = new TreeNode();
            // list在构造函数中初始化
        }

        for (int m = 0; m < M; m++) {
            int nowNode = sc.nextInt();
            int K = sc.nextInt();
            for (int k = 0; k < K; k++) {
                int child = sc.nextInt();
                treeNodes[nowNode].children.add(child);
            }
        }

        __leaf = new int[N];

        dfs(treeNodes, 1, 0);

        for (int i = 0; i <= __maxHeight; i++) {
            System.out.print(__leaf[i]);
            if (i != __maxHeight) {
                System.out.print(" ");
            }
        }

    }

    private static void dfs(TreeNode[] treeNodes, int nowNode, int depth) {
        // leafNode, 记录
        if (treeNodes[nowNode].children.size() == 0) {
            if (depth > __maxHeight) {
                __maxHeight = depth;
            }
            __leaf[depth]++;
            return;
        }

        for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
            Integer nextNode = treeNodes[nowNode].children.get(i);
            dfs(treeNodes, nextNode, depth + 1);
        }

    }


    static class TreeNode {
        int level;
        List<Integer> children;

        TreeNode() {
            children = new ArrayList<>();
        }
    }

}

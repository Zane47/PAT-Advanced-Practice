package pat.advanced.tree;


import java.util.*;

/**todo: 测试点错误
 * count those family members who have no child.
 * <p>
 * 每一层有多少个leafNode
 *
 * 2 1
 * 01 1 02
 * ->
 * 0 1
 *
 *
 */
public class CountingLeaves_1004_bfs {


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
        // [1, N]
        int[] leaf = new int[N + 1];
        Arrays.fill(leaf, 0);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        treeNodes[1].level = 0;
        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            // leafNode
            if (treeNodes[now].children.size() == 0) {
                leaf[now]++;
            }

            for (int i = 0; i < treeNodes[now].children.size(); i++) {
                int child = treeNodes[now].children.get(i);
                treeNodes[child].level = treeNodes[now].level + 1;
                queue.add(child);
            }
        }

        // output
        for (int i = 1; i <= N; i++) {
            System.out.print(leaf[i]);
            if (i != N) {
                System.out.print(" ");
            }
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

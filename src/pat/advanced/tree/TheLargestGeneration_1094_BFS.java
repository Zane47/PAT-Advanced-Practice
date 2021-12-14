package pat.advanced.tree;

import java.util.*;

/**ac
 * find the generation with the largest population
 * 结点最多的一层, level order
 */
public class TheLargestGeneration_1094_BFS {

    private static int __maxPopulation = 0;
    private static int __depth = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // the total # of family members in the tree , < 100
        // all the members are numbered from 01 to N
        int N = sc.nextInt();

        // M (<N) which is the # of family members who have children
        int M = sc.nextInt();

        // 编号从1到N, the root ID = 01
        TreeNode[] treeNodes = new TreeNode[N + 1];

        for (int i = 0; i <= N; i++) {
            treeNodes[i] = new TreeNode();
            // 初始化1
            treeNodes[i].level = 1;
            treeNodes[i].children = new ArrayList<>();
        }

        for (int m = 0; m < M; m++) {
            int id = sc.nextInt();
            int K = sc.nextInt();
            for (int k = 0; k < K; k++) {
                treeNodes[id].children.add(sc.nextInt());
            }
        }


        // 层次遍历
        levelOrder(treeNodes);

        int[] hashTable = new int[N + 1];
        Arrays.fill(hashTable, 0);

        for (int i = 1; i <= N; i++) {
            hashTable[treeNodes[i].level]++;
        }

        for (int i = 1; i < hashTable.length; i++) {
            if (hashTable[i] > __maxPopulation) {
                __maxPopulation = hashTable[i];
                __depth = i;
            }
        }


        // output
        // print in one line the largest population number
        // and the level of the corresponding generation.
        // It is assumed that such a generation is unique, and the root level is defined to be 1.

        System.out.printf("%d %d", __maxPopulation, __depth);
    }

    /**
     * 层次遍历
     *
     * @param treeNodes
     */
    private static void levelOrder(TreeNode[] treeNodes) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
                int child = treeNodes[nowNode].children.get(i);
                treeNodes[child].level = treeNodes[nowNode].level + 1;
                queue.add(child);
            }
        }
    }

    static class TreeNode {
        int level;
        List<Integer> children;
    }
}


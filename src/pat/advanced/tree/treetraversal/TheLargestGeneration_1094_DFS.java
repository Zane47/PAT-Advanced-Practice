package pat.advanced.tree.treetraversal;

import java.util.*;

/**
 * ac
 * find the generation with the largest population
 * 结点最多的一层, level order
 */
public class TheLargestGeneration_1094_DFS {

    private static int __maxPopulation = 0;
    private static int __depth = 1;

    // level和对应的数量
    private static int[] __hashTable;


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

        __hashTable = new int[N + 1];
        Arrays.fill(__hashTable, 0);

        dfs(treeNodes, 1, 1);

        // output
        // print in one line the largest population number
        // and the level of the corresponding generation.
        // It is assumed that such a generation is unique, and the root level is defined to be 1.


        for (int i = 1; i < __hashTable.length; i++) {
            if (__hashTable[i] > __maxPopulation) {
                __maxPopulation = __hashTable[i];
                __depth = i;
            }
        }

        System.out.printf("%d %d", __maxPopulation, __depth);
    }

    /**
     * dfs
     *
     * @param treeNodes
     */
    private static void dfs(TreeNode[] treeNodes, int nowNode, int depth) {
        __hashTable[depth]++;
        for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
            int nextNode = treeNodes[nowNode].children.get(i);
            dfs(treeNodes, nextNode, depth + 1);
        }
    }

    static class TreeNode {
        int level;
        List<Integer> children;
    }
}


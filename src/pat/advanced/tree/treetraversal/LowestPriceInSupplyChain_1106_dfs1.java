package pat.advanced.tree.treetraversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**测试点23567超时
 * 给出一棵销售供应的树，树根唯一。在树根处货物的价格为P，
 * 然后从根结点开始每往子结点走一层，该层的货物价格将会在父亲结点的价格上增加r%。
 * 求叶子结点处能获得的最低价格以及能提供最低价格的叶子结点的个数
 */
public class LowestPriceInSupplyChain_1106_dfs1 {

    private static int[] __hashTable;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // <=10^5, the total number of the members in the supply chain
        // ID are numbered from 0 to N−1, the root supplier's ID is 0
        int N = sc.nextInt();
        // the price given by the root supplier
        double P = sc.nextDouble();
        // the percentage rate of price increment for each distributor or retailer.
        double r = sc.nextDouble();
        r /= 100;

        TreeNode[] treeNodes = new TreeNode[N];

        for (int i = 0; i < N; i++) {
            treeNodes[i] = new TreeNode();
            treeNodes[i].children = new ArrayList<>();

            int K = sc.nextInt();
            for (int k = 0; k < K; k++) {
                int child = sc.nextInt();
                treeNodes[i].children.add(child);
            }
        }

        __hashTable = new int[N];
        Arrays.fill(__hashTable, 0);

        dfs(treeNodes, 0, 0);

        // output:
        // the lowest price we can expect from some retailers, accurate up to 4 decimal places
        // the number of retailers that sell at the lowest price.

        // 最高层叶子结点的depth(.4f), 和该层叶子结点的个数
        int highestLeftNodeNum = 0;
        int highestDepth = 0;
        // hashTable = [0, 0, 2, 2, 0, 0, 0, 0, 0, 0]
        // 遍历到第一个不是0的就可以了
        for (int i = 0; i < __hashTable.length; i++) {
            if (__hashTable[i] != 0) {
                highestDepth = i;
                highestLeftNodeNum = __hashTable[i];
                break;
            }
        }

        System.out.printf("%.4f %d", P * Math.pow(1 + r, highestDepth), highestLeftNodeNum);


    }

    /**
     * @param treeNodes
     * @param nowNode
     * @param depth
     */
    private static void dfs(TreeNode[] treeNodes, int nowNode, int depth) {
        if (treeNodes[nowNode].children.size() == 0) {
            __hashTable[depth]++;
        }

        for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
            int nextNode = treeNodes[nowNode].children.get(i);
            dfs(treeNodes, nextNode, depth + 1);
        }
    }

    static class TreeNode {
        List<Integer> children;
    }

}

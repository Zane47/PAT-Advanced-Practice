package pat.advanced.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**测试点2356超时
 * -> HighestPriceInSupplyChain_1090的基础上增加了货物量(点权)
 */
public class TotalSalesOfSupplyChain_1079 {

    private static double __result = 0;

    private static double P;

    private static double r;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // the total number of the members in the supply chain
        // ID's are numbered from 0 to N−1, and the root supplier's ID is 0
        int N = sc.nextInt();

        // the unit price given by the root supplier
        P = sc.nextDouble();

        // the percentage rate of price increment for each distributor or retailer.
        r = sc.nextDouble();
        r = r / 100;

        TreeNode[] treeNodes = new TreeNode[N];


        // each describes a distributor or retailer in the following format:
        for (int i = 0; i < N; i++) {
            treeNodes[i] = new TreeNode();
            treeNodes[i].children = new ArrayList<>();

            int K = sc.nextInt();
            // 叶子结点, data赋值
            if (K == 0) {
                treeNodes[i].data = sc.nextDouble();
            } else {
                for (int j = 0; j < K; j++) {
                    int child = sc.nextInt();
                    treeNodes[i].children.add(child);
                }
            }
        }

        // 遍历
        dfs(treeNodes, 0, 0);

        System.out.printf("%.1f", __result);
    }

    /**
     * @param treeNodes
     * @param nowNode
     * @param depth
     */
    private static void dfs(TreeNode[] treeNodes, int nowNode, int depth) {
        // 叶子结点
        if (treeNodes[nowNode].children.size() == 0) {

            __result += P * Math.pow(1+r, depth) * treeNodes[nowNode].data;

            return;
        }

        for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
            int nextNode = treeNodes[nowNode].children.get(i);
            dfs(treeNodes, nextNode, depth + 1);
        }
    }


    static class TreeNode {
        double data;
        List<Integer> children;
    }
}

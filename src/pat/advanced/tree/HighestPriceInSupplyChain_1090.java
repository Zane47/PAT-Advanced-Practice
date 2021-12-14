package pat.advanced.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**测试点1246超时
 * -> 增加点权, TotalSalesOfSupplyChain_1079
 * It is assumed that each member in the supply chain has exactly one supplier except the root supplier, and there is no supply cycle
 * 说明是树
 * <p>
 * you are supposed to tell the highest price we can expect from some retailers.
 * <p>
 * print:
 * total sales we can expect from all the retailers, accurate up to 2 decimal place.
 * # of retailers that sell at the highest price, 最深层叶子结点个数
 * <p>
 * <p>
 * 9 1.80 1.00
 * 1 5 4 4 -1 4 5 3 6
 * <p>
 * 1.85 2
 */
public class HighestPriceInSupplyChain_1090 {
    // 最大深度
    private static int __maxDepth = 0;

    // 最大深度叶子结点的个数
    private static int __leafNodeNum = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //  the total number of the members in the supply chain
        //  they are numbered from 0 to N−1;
        int N = sc.nextInt();

        // the unit price given by the root supplier
        double P = sc.nextDouble();

        // the percentage rate of price increment for each distributor or retailer.
        // 注意时候百分号, ((0.01 * r + 1) ^ level) * P
        double r = sc.nextDouble();
        r = r / 100;
        int root = 0;


        TreeNode[] treeNodes = new TreeNode[N];
        // 初始化
        for (int i = 0; i < N; i++) {
            treeNodes[i] = new TreeNode();
            treeNodes[i].children = new ArrayList<>();
        }

        // Si is the index of the supplier for the i-th member.
        // Sroot for the root supplier is defined to be −1.
        // All the numbers in a line are separated by a space.
        for (int i = 0; i < N; i++) {
            int father = sc.nextInt();
            if (father != -1) {
                treeNodes[father].children.add(i);
            } else {
                root = i;
            }
        }

        dfs(treeNodes, root, 0);

        System.out.printf("%.2f %d", P * Math.pow((double)1 + r, __maxDepth), __leafNodeNum);

    }

    /**
     * 深度遍历, 求出最大深度和最大深度的叶子结点个数
     *
     * @param treeNodes
     * @param nowNode
     * @param depth     该层的深度
     */
    private static void dfs(TreeNode[] treeNodes, int nowNode, int depth) {

        // 到达叶子结点
        if (treeNodes[nowNode].children.size() == 0) {

            if (depth > __maxDepth) {
                __maxDepth = depth;
                __leafNodeNum = 1;
            } else if (depth == __maxDepth) {
                __leafNodeNum++;
            }

            return;
        }

        for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
            int nextNode = treeNodes[nowNode].children.get(i);
            dfs(treeNodes, nextNode, depth + 1);
        }
    }

    static class TreeNode {
        double value;
        List<Integer> children;
    }

}

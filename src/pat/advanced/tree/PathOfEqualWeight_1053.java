package pat.advanced.tree;

import java.util.*;

/**ac
 * The weight of a path from R to L is defined to be
 * the sum of the weights of all the nodes along the path from R to any leaf node L.
 * <p>
 * find all the paths with their weights equal to a given number.
 * <p>
 * <p>
 * print all the paths with weight S in non-increasing order.
 * 序列与序列之间是倒序
 * <p>
 * <p>
 * 注意点:
 * 1. 后面要按照权值从大到小排序, 那么在输入的时候, 也先按照每个结点的权值, 从大到小入list
 * 2. 树的遍历不需要对nowNode判空
 */
public class PathOfEqualWeight_1053 {

    private static List<Integer> __tempList;
    private static List<List<Integer>> __resultList;

    private static int S;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // # of nodes in a tree, (0,100]
        int N = sc.nextInt();
        // the number of non-leaf nodes, < N
        int M = sc.nextInt();
        // he given weight number. (0, 2^30)
        S = sc.nextInt();

        // 初始化树
        TreeNode[] treeNodes = new TreeNode[N];

        for (int i = 0; i < N; i++) {
            treeNodes[i] = new TreeNode();
            treeNodes[i].weight = sc.nextInt();
            treeNodes[i].children = new ArrayList<>();
        }

        // 读取的时候, 初始化list, 后面判断的时候, 根据null和size ||判断
        for (int i = 0; i < M; i++) {
            int node = sc.nextInt();
            int childrenNum = sc.nextInt();
            for (int j = 0; j < childrenNum; j++) {
                treeNodes[node].children.add(sc.nextInt());
            }
            // 最后要按照权值从大到小输出, 输入的时候就给他预先排列好
            treeNodes[node].children.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return treeNodes[o2].weight - treeNodes[o1].weight;
                }
            });
        }

        // 凑weight = S
        // dfs遍历这个树, List<LIst<Integer>> 中存储所有的路径
        // tempList存储每一次的路径, 如果成功的话, 就放入resultList中
        //
        __tempList = new ArrayList<>();
        __resultList = new ArrayList<>();

        // 先把根节点加进去
        __tempList.add(0);

        dfs(treeNodes, 0, treeNodes[0].weight);

        for (List<Integer> list : __resultList) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(treeNodes[list.get(i)].weight);
                if (i != list.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    /**
     * dfs遍历树, 找到到叶子结点的路径, weight=S
     * 递归过程:
     * 路径和为sum
     * 1. sum > S, 超过, 直接return
     * 2. sum == S, 如果是叶子结点, 那么就是结果之一, 放入result; 如果不是叶子结点, return
     * 3. sum < S, 进入下一层递归
     *
     * @param treeNodes
     * @param nowNode   现在遍历到的node
     * @param sum       总和
     */
    private static void dfs(TreeNode[] treeNodes, int nowNode, int sum) {
        // dfs遍历树的时候不需要判空
        if (sum > S) {
            return;
        }

        // 查看是否是叶子结点
        if (sum == S) {
            if (treeNodes[nowNode].children.size() != 0) {
                return;
            } else {
                // 一个结果, 记录下来
                List<Integer> list = new ArrayList<>(__tempList);
                __resultList.add(list);
            }
        }

        // 下一层
        for (int i = 0; i < treeNodes[nowNode].children.size(); i++) {
            int nextNode = treeNodes[nowNode].children.get(i);
            __tempList.add(nextNode);

            dfs(treeNodes,
                    nextNode,
                    sum + treeNodes[nextNode].weight);
            // 回溯
            __tempList.remove(__tempList.size() - 1);
        }


    }

    /**
     * 静态方式声明树
     */
    static class TreeNode {
        int weight;
        List<Integer> children;
    }

}

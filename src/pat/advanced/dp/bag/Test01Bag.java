package pat.advanced.dp.bag;

import java.util.Arrays;

/**
 * todo: 有问题 以leetcode的为准
 * 01背包例题
 * 有n个物品, 重量分别为weight[i], 每个的价值是value[i]. 现有一个容量V的背包, 问如何选取物品放入背包, 可以让背包内物品的总价值最大
 * 其中每种物品都只有一件
 * 5 8       // n=5, V = 8
 * 3 5 1 2 2 // weight
 * 4 5 2 1 3 // value
 * <p>
 * dp[i][v]: 前i个物品恰好装入容量为v的背包中的所能获得的最大价值
 */
public class Test01Bag {
    public static void main(String[] args) {
        /*int n = 5;
        int V = 8;
        int[] weight = new int[]{3, 5, 1, 2, 2};
        int[] value = new int[]{4, 5, 2, 1, 3};*/

        int n = 3;
        int V = 4;
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};

        // 二维dp
        solution1(n, V, weight, value);


        // 一维, 更小的空间复杂度
        // solution2(n, V, weight, value);


    }

    private static void solution2(int n, int V, int[] weight, int[] value) {
        int[] dp = new int[V + 1];

        // 边界
        Arrays.fill(dp, 0);

        for (int i = 0; i < n; i++) {
            for (int v = V; v >= weight[i]; v--) {
                //逆序枚举v
                dp[v] = Math.max(dp[v], dp[v - weight[i]] + value[i]);
            }
        }

        //寻找dp[0...V]中最大的即为答案
        int max = 0;
        for (int v = 0; v <= V; v++) {
            if (dp[V] > max) {
                max = dp[v];
            }
        }
        System.out.println(max);
    }

    /**
     * @param n
     * @param V
     * @param weight
     * @param value
     */
    private static void solution1(int n, int V, int[] weight, int[] value) {
        int[][] dp = new int[n + 1][V + 1];

        // 初始化dp[0][]: 放入0个物品, 价值是0
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 0);
        }

        for (int i = 1; i <= n; i++) {
            for (int v = weight[i - 1]; v <= V; v++) {
                dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i - 1]] + value[i - 1]);
            }
        }

        // 打印dp数组
        for (int i = 0; i <= weight.length; i++) {
            for (int j = 0; j <= V; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        int max = 0;
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= V; v++) {
                if (dp[i][v] > max) {
                    max = dp[i][v];
                }
            }
        }
        System.out.println(max);
    }

}

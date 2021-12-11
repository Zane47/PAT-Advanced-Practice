package pat.advanced.dp.bag;

import java.util.Arrays;

/**
 * 一维dp数组
 * 空间复杂度 O(V)
 */
public class Test01Bag_my2 {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int V = 4;
        testWeightBagProblem3(weight.length, weight, value, V);
    }

    /**
     * dp[j]表示背包容量为j时，能获得的最大价值
     */
    private static void testWeightBagProblem3(int n, int[] weight, int[] value, int V) {
        // 定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[V + 1];

        //遍历顺序：先遍历物品，再遍历背包容量
        // [0, n), weight和value就是第i个
        for (int i = 0; i < n; i++) {
            for (int v = V; v >= weight[i]; v--) {
                // 此时dp[j]有两个选择，
                // 1.取自己dp[j] 相当于 二维dp数组中的dp[i-1][j]，即不放物品i，
                // 2.取dp[j - weight[i]] + value[i]，即放物品i，
                dp[v] = Math.max(dp[v], dp[v - weight[i]] + value[i]);
            }

            // 打印dp数组
            System.out.println(i + ":" + Arrays.toString(dp));
        }


    }
}

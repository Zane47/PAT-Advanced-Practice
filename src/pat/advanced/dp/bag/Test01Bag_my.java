package pat.advanced.dp.bag;

public class Test01Bag_my {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem2(weight.length, weight, value, bagSize);
    }

    private static void testWeightBagProblem2(int n, int[] weight, int[] value, int bagSize) {

        // 前i个物品可以装进容量为v的背包的最大价值
        int[][] dp = new int[n + 1][bagSize + 1];

        // 第0行, 前0件物品, 那么就没有价值
        for (int v = 0; v <= bagSize; v++) {
            dp[0][v] = 0;
        }

        // 第0列, 背包容量为0, 价值为0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        // 第一行第一列开始
        // 前i个物品, [0, i-1],下标是i-1
        for (int i = 1; i <= n; i++) {
            for (int v = 1; v <= bagSize; v++) {
                if (weight[i - 1] > v) {
                    dp[i][v] = dp[i - 1][v];
                } else {
                    dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i - 1]] + value[i - 1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= bagSize; v++) {
                System.out.print(dp[i][v] + " ");
            }
            System.out.println();
        }

        int max = -1;
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= bagSize; v++) {
                if (dp[i][v] > max) {
                    max = dp[i][v];
                }
            }
        }
        System.out.println(max);

    }
}

package pat.advanced.dp.bag;

import java.util.Arrays;
import java.util.Scanner;

/**todo: 运行错误
 * http://codeup.hustoj.com/problem.php?cid=100000631&pid=0
 * 【问题描述】
 * 有一个箱子的容量为V（V为正整数，且满足0≤V≤20000），同时有n件物品（0的体积值为正整数。
 * 要求从n件物品中，选取若干装入箱内，使箱子的剩余空间最小。
 * 输入：1行整数，第1个数表示箱子的容量，第2个数表示有n件物品，后面n个数分别表示这n件
 * 物品各自的体积。
 * 输出：1个整数，表示箱子剩余空间。
 * 【输入输出样例】
 * 输入：
 * 24 6 8 3 12 7 9 7
 * 输出：
 * 0
 */
public class Box_CodeUp631A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int n = sc.nextInt();
        // 占用体积
        int[] volume = new int[n];
        for (int i = 0; i < n; i++) {
            volume[i] = sc.nextInt();
        }


        // 前i件物品能放入体积v的背包中最大占用体积
        int[][] dp = new int[n + 1][V + 1];

        Arrays.fill(dp[0], 0);

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int v = 1; v <= V; v++) {
                if (volume[i - 1] > v) {
                    dp[i][v] = dp[i - 1][v];
                } else {
                    dp[i][v] = Math.max(
                            dp[i - 1][v],
                            dp[i - 1][v - volume[i - 1]] + volume[i - 1]);
                }
            }
        }

        int max = -1;
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= V; v++) {
                if (dp[i][v] > max) {
                    max = dp[i][v];
                }
            }
        }

        System.out.println(V - max);
    }
}

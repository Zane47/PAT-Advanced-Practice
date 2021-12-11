package pat.advanced.dp.bag;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/** 测试点3 4 超时
 * 与1048的区别, 1048中用exactly two coins来进行支付, 这里是some coins, 个数未知
 * 10^4个硬币
 * <p>
 * 有N枚硬币，给出每枚硬币的价值，现在要用这些硬币去支付价值为M的东西，
 * 问是否可以找到这样的方案，使得选择用来支付的硬币的价值之和恰好为M。
 * 如果不存在，输出No Solution；如果存在，从小到大输出选择用来支付的硬币的价值，
 * 如果有多种方案，则输出字典序最小的那个。
 * 所谓的字典序小是指：有两种方案分别为{A[1],A[2],…}与{B[1]，B[2]，…}，
 * 如果存在k≥1，使得对任意i < k都有A[i]=B[i]，而A[k] < B[k]成立，那么就称方案A的字典序比方案B小。
 * <p>
 * 背包问题
 * N为物品数目, M要凑的数目 为背包容量
 * 硬币的价值就是重量,
 * <p>
 * 输出字典序小的方法:
 * 排序, 用数组记录放入背包的硬币面值, 做输出
 */
public class FindMoreCoins_1068 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // the total number of coins, <= 10^4
        int N = sc.nextInt();
        // the amount of money Eva has to pay, <=10^2
        int M = sc.nextInt();

        // 硬币面值
        Integer[] values = new Integer[N];
        for (int i = 0; i < values.length; i++) {
            values[i] = sc.nextInt();
        }

        // 选择情况, 记录计算dp[i][v]的时是选择了哪个策略
        int[][] choice = new int[N + 1][M + 1];

        // 倒序, todo: 为什么?
        Arrays.sort(values, Collections.reverseOrder());

        // 容量为v时候, 所有硬币的价值
        int[] dp = new int[M + 1];

        for (int i = 0; i < N; i++) {
            for (int v = M; v >= values[i]; v--) {
                // 等于的时候也要, 倒序, -> 字典序最小
                if (dp[v] <= dp[v - values[i]] + values[i]) {
                    dp[v] = dp[v - values[i]] + values[i];
                    // 在硬币总和v的情况下, 放入第i个硬币
                    choice[i][v] = 1;
                } else {
                    // 在硬币总和v的情况下, 不放入第i个硬币
                    choice[i][v] = 0;
                }
            }
        }

        // 打印choice数组
        /*for (int[] ints : choice) {
            System.out.println(Arrays.toString(ints));
        }*/


        // 标记放入背包的硬币
        // 这样flag数组就记录了得到最大价值的方案中各件物品的选取情况
        // （注意区分choice数组与flag数组的作用）。
        boolean[] flag = new boolean[N];
        Arrays.fill(flag, false);

        // 1. 先求出dp[n][o…V]中最大的dp[n][v]，下面需要用到这个v值。
        // 2. 从第n件物品开始倒着查看每一件物品是否放入背包，代码如下：
        if (dp[M] != M) {
            System.out.println("No Solution");
        } else {
            // 记录最优路径
            int num = 0;
            int v = M;

            // 从后往前遍历所有的硬币
            for (int k = N - 1; k >= 0; k--) {
                // 计算dp[k][v]的时候选择了该硬币
                if (choice[k][v] == 1) {
                    flag[k] = true;
                    v -= values[k];
                    num++;
                } else if (choice[k][v] == 0) {
                    flag[k] = false;
                }
            }


            // 输出方案
            for (int i = N - 1; i >= 0; i--) {
                if (flag[i]) {
                    System.out.print(values[i]);
                    num--;
                    if (num > 0) {
                        System.out.print(" ");
                    }
                }
            }

        }


    }
}

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
 *
 * 8 9
 * 5 9 8 7 2 3 4 1
 * ->
 * 1 3 5
 *
 * 4 8
 * 7 2 4 3
 * ->
 * No Solution
 *
 * 1.可以将每个coin都看成value和weight都相同的物品
 * 2.要求所付的钱刚刚好，相当于要求背包必须刚好塞满，且价值最大。（限制背包体积相当于限制coin的总和不能超过所要付的钱，在此条件下求coin组合的最大值，如果这个最大值刚好等于要付的钱，则有解，此时背包也刚好处于塞满状态，否则无解）
 * 3.最后要求从小到大输出coin的组合，且有多解时输出最小的组合。这是此题的难点所在，我们应该将coin从大到小排序，在放进背包时也从大到小逐个检查物品，更新背包价值的条件是在加入一个新的物品后，价值>=原价值，注意此时等号的意义，由于物品是从大到小排序的，如果一个新的物品的加入可以保证价值和原来相同，则此时一定是发现了更小的组合。
 *
 * 作者：cheerss
 * 链接：https://www.jianshu.com/p/20dac38241a5
 * 来源：简书
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
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
                // 等号必须取到，否则输出的解是最大的sequence
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


        // 沿着选出解的路径，反着走回去，就找到了所有被选择的数字。

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
            // 记录最优路径个数
            int count = 0;
            int v = M;

            // 从后往前遍历所有的硬币
            for (int k = N - 1; k >= 0; k--) {
                // 计算dp[k][v]的时候选择了该硬币
                // 硬币综合为v的时候, 有么有选择第k枚硬币
                if (choice[k][v] == 1) {
                    flag[k] = true;
                    v -= values[k];
                    count++;
                } else if (choice[k][v] == 0) {
                    flag[k] = false;
                }
            }


            // 输出方案
            for (int i = N - 1; i >= 0; i--) {
                if (flag[i]) {
                    System.out.print(values[i]);
                    count--;
                    if (count > 0) {
                        System.out.print(" ");
                    }
                }
            }

        }


    }
}

package pat.advanced.dp.bag;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 与1048的区别, 1048中用exactly two coins来进行支付, 这里是some coins, 个数未知
 * 10^4个硬币
 *
 * 有N枚硬币，给出每枚硬币的价值，现在要用这些硬币去支付价值为M的东西，
 * 问是否可以找到这样的方案，使得选择用来支付的硬币的价值之和恰好为M。
 * 如果不存在，输出No Solution；如果存在，从小到大输出选择用来支付的硬币的价值，
 * 如果有多种方案，则输出字典序最小的那个。
 * 所谓的字典序小是指：有两种方案分别为{A[1],A[2],…}与{B[1]，B[2]，…}，
 * 如果存在k≥1，使得对任意i < k都有A[i]=B[i]，而A[k] < B[k]成立，那么就称方案A的字典序比方案B小。
 *
 * 背包问题
 * N为物品数目, M要凑的数目 为背包容量
 * 硬币的价值就是重量,
 *
 * 输出字典序小的方法:
 * 排序, 用数组记录放入背包的硬币面值, 做输出
 *
 *
 */
public class FindMoreCoins_1068 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // the total number of coins, <= 10^4
        int N = sc.nextInt();
        // the amount of money Eva has to pay, <=10^2
        int M = sc.nextInt();

        // 硬币面值
        int[] values = new int[N];
        for (int i = 0; i < values.length; i++) {
            values[i] = sc.nextInt();
        }

        // 放入背包的硬币
        boolean[] flag = new boolean[N];
        Arrays.fill(flag, false);

        // 因为要字典序最小
        Arrays.sort(values);





    }
}

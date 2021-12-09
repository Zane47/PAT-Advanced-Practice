package pat.advanced.dp.bag;

import java.util.Scanner;

/**
 * 与1048的区别, 1048中用exactly two coins来进行支付, 这里是some coins, 个数未知
 * 10^4个硬币
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





    }
}

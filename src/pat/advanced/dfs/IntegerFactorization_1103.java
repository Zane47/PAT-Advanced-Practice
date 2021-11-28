package pat.advanced.dfs;

import java.util.Scanner;

public class IntegerFactorization_1103 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N <= 400
        int N = sc.nextInt();
        // K <= N
        int K = sc.nextInt();
        // (1, 7] -> [2, 7]
        int P = sc.nextInt();

        // N = 以K个数字为底, 指数为P的和. 这K个数字非递减, 可以相等
        // 如果有多个: 第一标尺: 底数和最大. 如果还相等, 第二标尺: 底数序列字典和最大







    }
}

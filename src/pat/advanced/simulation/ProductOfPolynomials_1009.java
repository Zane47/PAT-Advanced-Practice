package pat.advanced.simulation;

import java.util.Scanner;

/**
 * 精确小数点后一位
 * [0, 1000], 1001位
 *
 * PAT全过, 牛客的有四个过不了, 精度问题
 * 5 716530.2
 * 5 716530.3
 *
 */
public class ProductOfPolynomials_1009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] num = new double[1001];
        double[] result = new double[2001];

        int K1 = sc.nextInt();

        for (int i = 0; i < K1; i++) {
            int index = sc.nextInt();
            num[index] = sc.nextDouble();
        }

        int K2 = sc.nextInt();
        for (int i = 0; i < K2; i++) {
            int exponent = sc.nextInt();
            double coefficient = sc.nextDouble();

            // 与第一组数据的每一个数据做乘法
            for (int j = 0; j < 1001; j++) {
                if (num[j] != 0) {
                    int newExponent = j + exponent;
                    double newCoefficient = num[j] * coefficient;
                    result[newExponent] += newCoefficient;
                }
            }
        }

        // 有多少位的系数 >0 的
        int count = 0;
        for (int i = 0; i < 2001; i++) {
            if (result[i] != 0) {
                count++;
            }
        }
        System.out.print(count);

        // 输出后面的结果
        for (int i = 2000; i >= 0; i--) {
            if (result[i] != 0 ) {
                System.out.printf(" %d %.1f", i, result[i]);
            }
        }

    }
}

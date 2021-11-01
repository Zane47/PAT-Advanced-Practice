package pat.advanced.rational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Sample Input 1:
 * 5
 * 2/5 4/15 1/30 -2/60 8/3
 * Sample Output 1:
 * 3 1/3
 * <p>
 * Sample Output 2:
 * 2
 * 4/3 2/3
 * output:
 * 2
 * <p>
 * <p>
 * Sample Output 3:
 * 3
 * 1/3 -1/6 1/8
 * output:
 * 7/24
 * <p>
 * 4
 * -1/2 -1/2 -1/2 -1/2
 * -2
 * <p>
 * 0的情况
 * <p>
 * 找到分母的最大公约数GCD，Greatest Common Divisor(GCD)
 * GCD(a,b) is the smallest positive linear combination of a and b
 */

/**
 * 0的情况
 * <p>
 * 找到分母的最大公约数GCD，Greatest Common Divisor(GCD)
 * GCD(a,b) is the smallest positive linear combination of a and b
 */

/**
 * 需要注意得是分母为0的情况
 */

public class RationalSum_1081 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String inputStr = br.readLine();
        // 空格为分割
        String[] allNumber = inputStr.split(" ");
        // 第一组数据先拿出来作为基数, 后面进行运算
        String firstNumberString = allNumber[0];
        String[] split = firstNumberString.split("/");
        long a = Long.parseLong(split[0]);
        long b = Long.parseLong(split[1]);

        // numerator/denominator
        // 从第二个数字开始
        for (int i = 1; i < N; i++) {
            String[] curStr = allNumber[i].split("/");
            long curNumerator = Long.parseLong(curStr[0]);
            long curDenominator = Long.parseLong(curStr[1]);
            // 两个分式做加法, 分母相乘, 分子乘对方的分母然后相加
            // 2/5 + 4/15 = (2*15 + 4*5) / (5 * 15), 先计算, 然后在做约分
            a = a * curDenominator + curNumerator * b;
            b = b * curDenominator;

            // 做约分, 首先求出分子分母的最大公因数, 然后分子分母分别除以这个数字
            int c = maxCommonDivisor(a, b);
            // 可能有负数的情况
            c = Math.abs(c);
            a = a / c;
            b = b / c;
        }

        // 求出的结果进行处理
        if (a == 0 || b == 0) {
            System.out.print(0);
            return;
        }
        if (b == 1) {
            System.out.print(a);
            return;
        }
        // 分子大于分母
        if (Math.abs(a) > Math.abs(b)) {
            if (a > 0) {
                // 10 / 3 -> 3 + (10 - (10 / 3)*3)
                System.out.printf("%d %d/%d", a / b, a - ((a / b) * b), b);
            } else {
                a = -a;
                // 正负的符号是在分子的
                System.out.printf("-%d %d/%d", a / b, a - ((a / b) * b), b);
            }
        } else if (Math.abs(a) == Math.abs(b)) {
            int temp = a > 0 ? 1 : -1;
            System.out.print(temp);
        } else {
            System.out.printf("%d/%d", a, b);
        }
    }

    /**
     * 求v1 v2的最大公因数
     */
    private static int maxCommonDivisor(long v1, long v2) {
        v1 = Math.abs(v1);
        v2 = Math.abs(v2);

        int temp = (int) (Math.min(v1, v2));
        for (int i = temp; i > 0; i--) {
            if (v1 % i == 0 && v2 % i == 0) {
                return i;
            }
        }
        return 1;


    }

    private static int maxCommonDivisor2(long v1, long v2) {
        if (v1 == 0 || v2 == 0) {
            return 1;
        }
        // 大的数字在前面
        if (v1 < v2) {
            long temp = v2;
            v2 = v1;
            v1 = temp;
        }

        int v110 = (int) (v1 % v2);
        if (v110 == 0) {
            return (int) v2;
        }
        if (v110 == 1) {
            return 1;
        }


        return 0;
    }
}



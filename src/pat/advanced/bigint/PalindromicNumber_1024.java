package pat.advanced.bigint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 10^10, 可能超过int, 大数字
 * <p>
 * 回文数的计算,
 * First, the non-palindromic number is reversed and
 * the result is added to the original number.
 * <p>
 * If the result is not a palindromic number,
 * this is repeated until it gives a palindromic number.
 * <p>
 * For example, if we start from 67, we can obtain a palindromic number in 2 steps:
 * 67 + 76 = 143, and 143 + 341 = 484.
 * <p>
 * Given any positive integer N,
 * you are supposed to find its paired palindromic number
 * and the number of steps taken to find it.
 */
public class PalindromicNumber_1024 {
    public static void main(String[] args) {
        // 用例输入为:7359915502 100
        // 用例输出为:1778753106860665710878660211957977917467157602357787 100
        // 你的输出为:17032879156711642703565832756540723720666237932961 100
        // 有几个测试点过不了

        // 与solution2对比后发现, 自己写的reverse方法有问题

        // solution1();


        // 其实完全不用自己写判断, Stringbuilder.reverse
        Scanner sc = new Scanner(System.in);

        String inputStr = sc.next();
        BigDecimal N = new BigDecimal(inputStr);
        int K = sc.nextInt();
        String reverseStr = new StringBuilder(inputStr).reverse().toString();
        // String reverseStr = palindromic(N).toString();

        int i = 0;
        while (i < K && !reverseStr.equals(inputStr)) {
            N = N.add(new BigDecimal(reverseStr));
            inputStr = N.toString();
            reverseStr = new StringBuilder(inputStr).reverse().toString();
            // reverseStr = palindromic(N).toString();
            i++;
        }
        System.out.println(N);
        System.out.println(i);
    }

    private static void solution1() {
        Scanner sc = new Scanner(System.in);

        BigDecimal N = new BigDecimal(sc.next());

        //  maximum number of steps.
        int K = sc.nextInt();
        int i = 0;

        while (i <= K) {
            if (!isPalindromic(N)) {
                N = N.add(palindromic(N));
            } else {
                break;
            }
            i++;
        }


        System.out.println(N);
        System.out.println(i);
    }

    /**
     * 输出回文数字
     * 有问题
     */
    private static BigDecimal palindromic(BigDecimal num) {
        // bigDecimal会精确到小数点后一位, 141.0, 这样子str就是5位了
        // String str = num.toString().split("\\.")[0];
        String str = num.toString();
        BigDecimal result = new BigDecimal("0");
        for (int i = 0; i < str.length(); i++) {
            BigDecimal temp = new BigDecimal(str.charAt(i) - '0');
            temp = temp.multiply(BigDecimal.valueOf(Math.pow(10, i)));
            result = result.add(temp);
        }

        return result.setScale(0, RoundingMode.FLOOR);
    }

    /**
     * 判断是否是回文
     */
    private static boolean isPalindromic(BigDecimal num) {
        // String str = num.toString().split("\\.")[0];
        String str = num.toString();
        int v1 = 0;
        int v2 = str.length() - 1;
        while (v1 < v2) {
            if (str.charAt(v1) != str.charAt(v2)) {
                return false;
            }
            v1++;
            v2--;
        }
        return true;
    }
}

package pat.advanced.radixconvert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 这道题目最重要的就是要用long来装
 * The last number "radix" is the radix of N1 if "tag" is 1, or of N2 if "tag" is 2.
 */
public class Radix_1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strList = br.readLine().split(" ");

        String N1 = strList[0];
        String N2 = strList[1];
        int tag = Integer.parseInt(strList[2]);
        long radix = Long.parseLong(strList[3]);
        long target = 0;
        long result = 0;
        if (tag == 1) {
            target = convertToTenRadix(N1, radix);
            result = findRadix(N2, target);
        } else if (tag ==   2) {
            target = convertToTenRadix(N2, radix);
            result = findRadix(N1, target);
        }
        System.out.println(result == -1L ? "Impossible" : result);
    }

    /**
     * 二分查找进制
     */
    private static long findRadix(String num, long target) {
        // 最小的进制: 输入num各位数字的最大的那个
        long lowest = findLow(num);
        // 最大的进制:
        // todo: 为什么
        long highest = Math.max(lowest, target);

        while (lowest <= highest) {
            long mid = (lowest + highest) / 2;
            long temp = convertToTenRadix(num, mid);
            if (temp < target) {
                lowest = mid + 1;
            } else if (temp == target) {
                return mid;
            } else {
                highest = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 输入数字各位中最大的数字
     */
    private static int findLow(String num) {
        int result = 0;
        for (int i = 0; i < num.length(); i++) {
            if (result < num.charAt(i)) {
                result = num.charAt(i);
            }
        }
        return result > 'a' ? result - 'a' + 10 : result - '0';
    }

    /**
     * 输入一个数字和进制
     * 输出对应的10进制数
     */
    private static long convertToTenRadix(String num, long radix) {
        // 结果
        long result = 0;
        // 指数
        int pow = 0;

        for (int i = num.length() - 1; i >= 0; i--) {
            int temp = 0;
            if (num.charAt(i) >= 'a') {
                temp = num.charAt(i) - 'a' + 10;
            } else {
                temp = num.charAt(i) - '0';
            }
            result += temp * Math.pow(radix, pow++);
        }

        return result;
    }

}

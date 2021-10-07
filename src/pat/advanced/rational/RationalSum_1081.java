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
 *
 * Sample Output 2:
 * 2
 * 4/3 2/3
 * output:
 * 2
 *
 *
 * Sample Output 3:
 * 3
 * 1/3 -1/6 1/8
 * output:
 * 7/24
 *
 *
 */

/**
 * 0的情况
 *
 * 找到分母的最大公约数GCD，Greatest Common Divisor(GCD)
 * GCD(a,b) is the smallest positive linear combination of a and b
 *
 */

public class RationalSum_1081 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String inputStr = br.readLine();
        String[] allNumber = inputStr.split(" ");
        Long result = 0L;
        // 第一组数据先拿出来作为基数, 后面进行运算
        String firstNumberString = allNumber[0];
        String[] split = firstNumberString.split("/");
        long a = Long.parseLong(split[0]);
        long b = Long.parseLong(split[1]);

        // numerator/denominator
        // 从第二个数字开始
        for (int i = 1; i < N; i++) {
            String[] curStr = allNumber[i].split("/");
            Long curNumerator = Long.parseLong(curStr[0]);
            Long curDenominator = Long.parseLong(curStr[1]);








        }






    }
}

package pat.advanced.rational;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

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
        String[] v1 = inputStr.split(" ");
        Long result = 0l;
        // numerator/denominator
        for (int i = 0; i < N; i++) {
            String[] v2 = v1[i].split("/");
            Long numerator = Long.parseLong(v2[0]);
            Long denominator = Long.parseLong(v2[1]);






        }






    }
}

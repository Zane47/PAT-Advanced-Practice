package pat.advanced;

import java.util.Scanner;

/**
 * 不超过九位的数字, 用中文习惯读出来
 * 注意0也需要哪找
 * zero ("ling") must be handled correctly according to the Chinese tradition
 *
 * input: -123456789
 * output: Fu yi Yi er Qian san Bai si Shi wu Wan liu Qian qi Bai ba Shi jiu
 *
 * input: 100800
 * output: yi Shi Wan ling ba Bai
 *
 */
public class ReadNumberInChinese_1082 {
    // 单位, 开头的""就是个位不用说出来
    private static final String[] str = {"", "Shi", "Bai" ,"Qian" ,"Wan", "Shi", "Bai", "Qian", "Yi"};
    // 数字
    private static final String[] num = {"ling","yi", "er", "san", "si", "wu", "liu", "qi", "ba", "jiu"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 负数的情况
        String str = String.valueOf(n);
        if (n < 0) {
            System.out.print("Fu ");
            str = str.substring(1);
        }

        int length = str.length();
        for (int i = 0; i < str.length(); i++) {
            // 读取到的数字
            int i1 = str.charAt(i) - '0';
            // 把数字本身念出来
            System.out.print(num[i1]);
            System.out.print(" ");


        }


    }
}

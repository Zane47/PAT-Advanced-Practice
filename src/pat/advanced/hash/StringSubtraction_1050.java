package pat.advanced.hash;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * the remaining string after taking all the characters in S2 from S1
 * String length <= 10^4
 * <p>
 * input:
 * They are students.
 * aeiou
 * output:
 * Thy r stdnts.
 *
 * ascii码128个
 * 直接用hash
 * boolen数组table[128]
 *
 */
public class StringSubtraction_1050 {
    public static void main(String[] args) {


        // 都超时
        solution1();

        // 仍然超时
        // 使用hash的方式, 这样子直接可以得到truefalse
        // Ascii128个, boolean数组
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        // ASCII一共128个字符
        boolean[] table = new boolean[128];
        for (int i = 0; i < str2.length(); i++) {
            table[str2.charAt(i)] = true;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (!table[str1.charAt(i)]) {
                System.out.print(str1.charAt(i));
            }
        }
    }

    /**
     * 用set, 都超时
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str2.length(); i++) {
            set.add(str2.charAt(i));
        }
        for (int i = 0; i < str1.length(); i++) {
            if (!set.contains(str1.charAt(i))) {
                System.out.print(str1.charAt(i));
            }
        }
    }
}

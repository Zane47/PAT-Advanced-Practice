package pat.advanced.bigint;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * 123456789
 * 123456789 is a 9-digit number consisting exactly the numbers from 1 to 9, with no duplication.
 * Double it we will obtain 246913578,
 * which happens to be another 9-digit number consisting exactly the numbers from 1 to 9,
 * only in a different permutation
 *
 * double a given number with k digits, you are to tell
 * if the resulting number consists of only a permutation of the digits in the original number.
 *
 * input:
 * no more than 20 digits., long 19位 -> !!!用BigDecimal
 *
 * Yes
 * 2469135798
 *
 * input和doubled都要包含一样的数字, 只是个数不一样, 从1-9
 * 这里用数组0-9来表示, 如果存在则这个num[i]++, 看是不是每一位的个数都相等
 * 例如:
 * 1234567899 -> [0, 1, 1, 1, 1, 1, 1, 1, 1, 2]
 * 2469135798 -> [0, 1, 1, 1, 1, 1, 1, 1, 1, 2]
 */

public class HaveFunwithNumbers_1023 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BigDecimal inputNumber = new BigDecimal(sc.next());
        BigDecimal doubledNumber = inputNumber.multiply(new BigDecimal(2));

        if (isOnlyPermutationWithInputNumber(inputNumber, doubledNumber)) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }

        System.out.println(doubledNumber);
    }

    private static boolean isOnlyPermutationWithInputNumber(BigDecimal inputNumber, BigDecimal doubledNumber) {
        int[] v1 = getArray(inputNumber);
        int[] v2 = getArray(doubledNumber);


        for (int i = 0; i < 10; i++) {
            if (v1[i] != v2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] getArray(BigDecimal number) {
        String inputStr = number.toString();
        int[] array = new int[10];
        for (int i = 0; i < inputStr.length(); i++) {
            array[Integer.parseInt(String.valueOf(inputStr.charAt(i)))]++;
        }

        return array;
    }


}

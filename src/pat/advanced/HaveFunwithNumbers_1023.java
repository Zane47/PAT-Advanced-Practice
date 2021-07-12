package pat.advanced;

import java.util.Scanner;

/**
 * 1234567899
 *
 * Yes
 * 2469135798
 *
 *
 */

// 并不能全过，why？

public class HaveFunwithNumbers_1023 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Long inputNumber = sc.nextLong();
        Long doubledNumber = inputNumber * 2;

        if (isOnlyPermutationWithInputNumber(inputNumber, doubledNumber)) {
            System.out.println("Yes");
            System.out.println(doubledNumber);
        }
        else {
            System.out.println("No");
        }

    }

    private static boolean isOnlyPermutationWithInputNumber(Long inputNumber, Long doubledNumber) {
        int[] v1 = getArray(inputNumber);
        int[] v2 = getArray(doubledNumber);


        for (int i = 0; i < 10; i++) {
            if (v1[i] != v2[i]) {
                return false;
            }
        }
        return true;
    }

    private static int[] getArray(Long number) {
        String inputStr = number.toString();
        int[] array = new int[10];
        for (int i = 0; i < inputStr.length(); i++) {
            array[Integer.parseInt(String.valueOf(inputStr.charAt(i)))]++;
        }

        return array;
    }


}

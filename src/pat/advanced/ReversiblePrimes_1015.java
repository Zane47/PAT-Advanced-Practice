package pat.advanced;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 73 10
 * 23 2    23:10111 -> 11101:29
 * 23 10
 * -2
 *
 *
 * Yes
 * Yes
 * No
 *
 * Now given any two positive integers N (<10^5 )
 * and D (1<D≤10), you are supposed to tell if N is a reversible prime with radix D.
 */
public class ReversiblePrimes_1015 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            int number = sc.nextInt();
            if (number < 0) {
                return;
            }
            int radix = sc.nextInt();

            if (isPrime(number) && isPrime(radixTrans(number, radix))) {
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
        }

    }

    private static Boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        for (int i = 2; i < number / 2 + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int radixTrans(int number, int radix) {
        if (number == 0) {
            return number;
        }
        ArrayList<Integer> temp = new ArrayList<>();
        while (number != 0) {
            temp.add(number % radix);
            number = number / radix;
        }

        int result = 0;
        // list已经反了过来了， 转成10进制
        // 23 10
        // [3, 2] -> 32
        for (int i = 0; i < temp.size(); i++) {
            result += temp.get(temp.size() - i - 1) * Math.pow(radix, i);
        }



        return result;
    }

}

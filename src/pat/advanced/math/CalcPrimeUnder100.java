package pat.advanced.math;

/**
 * 求100以内素数
 */
public class CalcPrimeUnder100 {

    private static int[] __prime;
    private static boolean[] __hasPirme;

    private static int __pNum = 0;

    public static void main(String[] args) {
        int n = 101;
        __prime = new int[n];
        __hasPirme = new boolean[n];

        findPrime(n);

        for (int i = 0; i < __pNum; i++) {
            System.out.println(__prime[i]);
        }

    }

    private static void findPrime(int n) {
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                __prime[__pNum++] = i;
                __hasPirme[i] = true;
            }
        }
    }

    private static boolean isPrime(int index) {
        if (index <= 1) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(index); i++) {
            if (index % i == 0) {
                return false;
            }
        }

        return true;
    }


}

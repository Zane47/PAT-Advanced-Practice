package pat.advanced.math;

/**
 * 筛选的方法, 更小的复杂度,
 * 求100以内素数
 * <p>
 * < 算法笔记P162 >
 */
public class CalcPrimeUnder100ByScreening {

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

    /**
     * 筛选的方法
     * @param n
     */
    private static void findPrime(int n) {
        // 务必从2开始,
        for (int i = 2; i < n; i++) {
            if (!__hasPirme[i]) {
                __prime[__pNum++] = i;
                for (int j = i + i; j < n; j += i) {
                    __hasPirme[j] = true;
                }
            }
        }
    }


}

package pat.advanced.rational;

/**
 * 最大公约数
 */
public class GCD {
    public static void main(String[] args) {
        int a = 1;
        int b = 1;

        solution1(a, b);

    }

    /**
     * 暴力穷举法的思路：
     * 从两个数之间找最小的数，然后用这个数往下减，若是两个数都能够被整除，那个数就是最大公约数
     */
    private static int solution1(int m, int n) {
        int temp = m > n ? n : m;
        for (int i = temp; i > 0; i--) {
            if (m % i == 0 && n % i == 0) {
                return i;
            }
        }
        return 0;
    }
}

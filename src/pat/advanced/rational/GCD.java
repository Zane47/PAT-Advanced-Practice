package pat.advanced.rational;

/**
 * 最大公约数
 */
public class GCD {
    public static void main(String[] args) {
        int a = 28;
        int b = 12;

        // 暴力法
        int s1 = solution1(a, b);

        // 递归法
        int s2 = solution2(47, 100);

        // 欧几里得法,辗转相除法
        // https://blog.csdn.net/afei__/article/details/80216247
        int s3 = solution3(a, b);
    }

    /**
     * 欧几里得法,辗转相除法
     */
    private static int solution3(int m, int n) {
        /*if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }*/
        while (n > 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    /**
     * 递归的方法
     */
    private static int solution2(int m, int n) {
        // 保证m > n
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {
            return n;
        } else {
            return solution2(n, m % n);
        }


    }

    /**
     * 暴力穷举法的思路：
     * 从两个数之间找最小的数，然后用这个数往下减，若是两个数都能够被整除，那个数就是最大公约数
     */
    private static int solution1(int m, int n) {
        int temp = Math.min(m, n);
        for (int i = temp; i > 0; i--) {
            if (m % i == 0 && n % i == 0) {
                return i;
            }
        }
        return 0;
    }
}

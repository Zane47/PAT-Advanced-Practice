package pat.advanced.rational;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
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
 * :2 -2/3 1/3
 * -1/3
 */

public class PAT1081 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String n = reader.readLine();
        String[] s1 = reader.readLine().split(" ");
        String[] s2 = s1[0].split("/");
        int a = Integer.parseInt(s2[0]);
        int b = Integer.parseInt(s2[1]);
        int sum = 0;
        for (int i = 1; i < Integer.parseInt(n); i++) {
            String[] s = s1[i].split("/");
            int atemp = Integer.parseInt(s[0]);
            int btemp = Integer.parseInt(s[1]);
            a = a * btemp + atemp * b;
            b = b * btemp;
            // 这里的GCD有可能为负数, 如果是负数, 那么负号就会转移到分母上
            int c = GCD(a, b);
            c = Math.abs(c);
            a = a / c;
            b = b / c;
        }
        if (a % b == 0) {
            System.out.print(a / b);
        } else {
            if (Math.abs(a) > Math.abs(b)) {
                if (a > 0) {
                    System.out.printf("%d %d/%d", a / b, a - a / b * b, b);
                } else {
                    a = -a;
                    System.out.printf("-%d %d/%d", a / b, a - a / b * b, b);
                }
            } else {
                System.out.printf("%d/%d", a - a / b * b, b);
            }
        }
    }

    private static int GCD(int m, int n) {
        return n == 0 ? m : GCD(n, m % n);
    }
}

package pat.advanced.hash;


import java.util.Arrays;
import java.util.Scanner;

/**
 * for each bill, she could only use exactly two coins to pay the exact amount.
 * Since she has as many as 10^5 coins with her,
 * tell her, for any given amount of money,
 * whether or not she can find two coins to pay for it.
 * <p>
 * 注意点: 这里的hashtable不能只开500, 因为m-num[i]可能大于500
 */
public class FindCoins_1048 {
    public static void main(String[] args) {

        // 测试点3, 4超时
        solution1();


        /*Scanner sc = new Scanner(System.in);
        // the total number of coins, <=10^5
        int N = sc.nextInt();
        // the amount of money Eva has to pay, <=10^3
        int M = sc.nextInt();
        // N face values of the coins, which are all positive numbers no more than 500
        int[] coins = new int[N];

        // hashtable
        int[] table = new int[501];

        for (int i = 0; i < N;i++) {
            coins[i] = sc.nextInt();
            table[coins[i]]++;
        }*/
    }

    /**
     * 两个硬币
     * 用hashtable来表示总价减去现在的硬币的另外一个硬币是否存在
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        // the total number of coins, <=10^5
        int N = sc.nextInt();
        // the amount of money Eva has to pay, <=10^3
        int M = sc.nextInt();
        // N face values of the coins, which are all positive numbers no more than 500
        int[] coins = new int[N];

        // hashtable
        int[] table = new int[10001];

        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
            table[coins[i]]++;
        }

        Arrays.sort(coins);

        for (int i : coins) {
            /*table[coin]--;
            int rest = M - coin;
            if (rest >= 0 && table[rest] != 0) {
                System.out.print(coin + " " + rest);
                return;
            }*/

            if (table[i] >= 0 && table[M - i] >= 0) {
                if ((M - i == i) && table[i] <= 1) {
                    continue;
                }
                System.out.print(i + " " + (M - i));
                return;
            }

        }
        System.out.print("No Solution");


    }
}

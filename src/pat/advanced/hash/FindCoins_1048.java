package pat.advanced.hash;


import java.util.Arrays;
import java.util.Scanner;

/**进阶到背包问题 -> 1048
 * for each bill, she could only use exactly two coins to pay the exact amount.
 * Since she has as many as 10^5 coins with her,
 * tell her, for any given amount of money,
 * whether or not she can find two coins to pay for it.
 * <p>
 * 注意点: 这里的hashtable不能只开500, 因为m-num[i]可能大于500
 * 这里有个坑，哈希表尽量做两倍以上，因为coin面额限制在500以内，但目标M在1000以内，
 * 它们做差，可能出现1000-1=999的情况，所以数组hashtable要做到1000的大小。
 */
public class FindCoins_1048 {
    public static void main(String[] args) {

        // 哈希表, 测试点3, 4超时
        // solution1();

        // 双指针, 测试点3, 4超时
        // solution2();

        // liu chuo, java下测试点3, 4仍然超时, C++版本不超时
        Scanner sc = new Scanner(System.in);
        // the total number of coins, <=10^5
        int N = sc.nextInt();
        // the amount of money Eva has to pay, <=10^3
        int M = sc.nextInt();
        // N face values of the coins, which are all positive numbers no more than 500
        int[] coins = new int[N];

        int[] hashtable = new int[1001];

        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
            hashtable[coins[i]]++;
        }
        // 从小到大遍历所有1000个硬币
        for (int i = 0; i < 1001; i++) {
            if (hashtable[i] != 0) {
                hashtable[i]--;
                // coin的金额小于总金额 && 剩下的金额coin也存在
                if (M > i && hashtable[M - i] != 0) {
                    System.out.print(i + " " + (M - i));
                    return;
                }
            }
        }
        System.out.print("No Solution");

    }


    /**
     * 双指针
     */
    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        // the total number of coins, <=10^5
        int N = sc.nextInt();
        // the amount of money Eva has to pay, <=10^3
        int M = sc.nextInt();
        // N face values of the coins, which are all positive numbers no more than 500
        int[] coins = new int[N];

        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
        }

        // 排序
        Arrays.sort(coins);

        // 双指正
        int v1 = 0;
        int v2 = coins.length - 1;

        while (v1 < v2) {
            int sum = coins[v1] + coins[v2];
            if (sum > M) {
                v2--;
            } else if (sum < M) {
                v1++;
            } else {
                System.out.print(coins[v1] + " " + coins[v2]);
                return;
            }
        }
        System.out.print("No Solution");
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
        int[] table = new int[1001];

        for (int i = 0; i < N; i++) {
            coins[i] = sc.nextInt();
            table[coins[i]]++;
        }

        Arrays.sort(coins);

        for (int coin : coins) {
            table[coin]--;
            int rest = M - coin;
            if (rest >= 0 && table[rest] != 0) {
                System.out.print(coin + " " + rest);
                return;
            }
        }
        System.out.print("No Solution");


    }
}

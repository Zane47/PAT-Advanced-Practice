package pat.advanced.dp.bag;


import java.util.Arrays;
import java.util.Scanner;

/**
 * ac
 */

public class Box_CodeUp631A_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int n = sc.nextInt();
        // 占用体积
        int[] volume = new int[n];
        for (int i = 0; i < n; i++) {
            volume[i] = sc.nextInt();
        }
        // 容量为j的背包，所背的物品价值可以最大为dp[j]
        int[] dp = new int[V + 1];

        Arrays.fill(dp, 0);

        for (int i = 0; i < n; i++) {
            for (int v = V; v >= volume[i]; v--) {
                dp[v] = Math.max(dp[v], dp[v - volume[i]] + volume[i]);
            }
        }

        int max = -1;
        for (int v = 0; v <= V; v++) {
            if (dp[v] > max) {
                max = dp[v];
            }
        }


        System.out.println(V - max);

    }
}

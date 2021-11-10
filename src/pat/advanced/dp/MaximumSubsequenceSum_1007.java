package pat.advanced.dp;

import java.util.Scanner;

/**
 * 最大子序列和
 * -> lc53, 输出的不一样, 体会不同的地方
 * <p>
 * <p>
 * 10
 * -10 1 2 3 4 -5 -23 3 7 -21
 * <p>
 * 10 1 4
 */
public class MaximumSubsequenceSum_1007 {
    public static void main(String[] args) {
        // 1.想到用暴力， 求所有子序列， 然后求和，对比所有的，这也是笔试中自己尝用的，费时费力

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        // 2.dp
        // dp：以这个位置为结尾的数字的最大子序列的和
        // 因为这里要记录start和end，所以就不用dp数组了
        // 使用temp来记录i-1时刻的值，再lc53中我们使用了一整个数组，
        // 其实是不需要的，直接可以用一个数字来代表dp[i-1]即可，因为我们只需要max




    }


}

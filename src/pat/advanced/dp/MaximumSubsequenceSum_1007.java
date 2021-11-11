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
 *
 * 测试点7超时, 测试点4错误
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
        /**
         * 1.dp：以这个位置为结尾的数字的最大子序列的和
         * 因为这里要记录start和end，所以就不用dp数组了
         * 使用temp来记录i-1时刻的值，再lc53中我们使用了一整个数组，
         * 其实是不需要的，直接可以用一个数字来代表dp[i-1]即可，因为我们只需要max
         *
         * 2.记录max开始的位置start和结束end的位置，
         *
         * 3.与lc53不同的地方：
         * If all the K numbers are negative,
         * then its maximum sum is defined to be 0,
         * and you are supposed to output the first and the last numbers of the whole sequence.
         */
        boolean allNegativeFlag = true;
        for (int num : nums) {
            if (num > 0) {
                allNegativeFlag = false;
                break;
            }
        }
        if (allNegativeFlag) {
            System.out.printf("%d %d %d", 0, nums[0], nums[nums.length - 1]);
        } else {
            int maxSum = Integer.MIN_VALUE;
            // dp[i-1]
            int subSum = 0;
            // endIndex: 我们是知道的, 就是如果遍历的时候加入了nums[i], 那就是i
            // startIndex: 我们需要记录上一次的,
            // 如果纳入了dp[i-1]那就是dp[i-1]的起点, 如果没有纳入dp[i-1]那就是这个数字
            int tempStartIndex = 0, startIndex = 0, endIndex = 0;

            for (int i = 0; i < nums.length; i++) {
                subSum = subSum + nums[i];
                if (subSum < 0) {
                    // 负数, 那么就+1
                    subSum = 0;
                    tempStartIndex = i + 1;
                } else if (subSum > maxSum) {
                    maxSum = subSum;
                    startIndex = tempStartIndex;
                    endIndex = i;
                }

            }
            System.out.printf("%d %d %d", maxSum, nums[startIndex], nums[endIndex]);
        }

    }


}

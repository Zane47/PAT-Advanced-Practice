package pat.advanced.binary;

import java.util.Arrays;
import java.util.Scanner;

/** 测试点3错误, 4超时
 * 二分的方法 O(logn)
 * -> 双指针方法
 * 证明见 < 算法笔记上机 > p164
 * <p>
 * -> 在一个递增数列中, i < j, a[j] <= a[i]*p 成立的所有i,j中, j - i 最大的那个
 * 遍历序列, 对于每个a[i], 计算第一个超过a[i]*p 的a[j], 然后所有的j-i, 求最大
 * (因为这里是"超过", 所以是j-i, 不需要+1)
 * <p>
 * 注意点:
 * 10^9, 要用long, 范围:[-2^63, 2^63-1]。占用8个字节 (19位数字)
 */
public class PerfectSequence_1085 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // # of integers in the sequence, <= 10^5
        int N = sc.nextInt();
        // the parameter <=10^9
        int p = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        Arrays.sort(nums);

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            // [i+1, n-1]之间找第一个大于a[i]*p的位置
            int j = binarySearch(nums, i, N - 1, (long) nums[i] * p);
            result = Math.max(result, j - i);
        }
        System.out.println(result);
    }

    private static int binarySearch(int[] nums, int left, int right, long target) {
        // 最大的数字都小于target, 返回n
        if (nums[right] < target) {
            return right + 1;
        }

        while (left < right) {
            int mid = (left + right) / 2;
            // mid位置的数字大于target, 说明第一个大于target的数字, 还在左边
            // right收缩到mid
            if (nums[mid] > target) {
                right = mid;
            } else {
                // nums[mid] <= target
                // 说明超过target的数字只可能在mid右边
                left = mid+1;
            }
        }

        // while 结束的时候left == right, 返回哪个都可以
        return left;
    }
}

package pat.advanced.twopoints;

import java.util.Arrays;
import java.util.Scanner;

/** O(n)
 * M <= m×p
 * <p>
 * <p>
 * <p>
 * print in one line the maximum number of integers
 * that can be chosen to form a perfect subsequence.
 * <p>
 * v1, v2双指针指向第一个点, v2每次向后移动一位到最后一个满足条件的点
 * 然后v1向后移动一位, 直到两者长度超过n
 * <p>
 * <p>
 * 注意点: 10^9
 * 要用long, 范围:[-2^63, 2^63-1]。占用8个字节 (19位数字)
 *
 * 测试点4超时
 *
 * -> 二分的思路 binary\PerfectSequence_1085
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

        int v1 = 0;

        // 如果是v2指向最后一个元素, 那么在变化的时候无法弄清楚是应该放大v1, 还是应该缩小v2
        // int v2 = nums.length - 1;
        int v2 = 0;
        //
        int count = 1;

        while (v1 < N && v2 < N) {
            while (v2 < N && nums[v2] <= (long) nums[v1] * p) {
                count = Math.max(v2 - v1 + 1, count);
                v2++;
            }
            v1++;
        }


        System.out.print(count);

    }
}

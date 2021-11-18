package pat.advanced.twopoints;

import java.util.Scanner;

/**
 *
 * 4 11 12 13 14
 * 5 9 10 15 16 17
 *
 * -> : 13
 * 输入的两列数字都是严格递增的,
 * 偶数的中间数字是, length/2 - 1
 * 奇数的中间数字是, length/2
 */
public class Median_1029 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] nums1 = new int[N];
        for (int i = 0; i < N; i++) {
            nums1[i] = sc.nextInt();
        }
        int M = sc.nextInt();
        int[] nums2 = new int[M];
        for (int i = 0; i < M; i++) {
            nums2[i] = sc.nextInt();
        }


        solution1(nums1, nums2);

        // solution2(nums1, nums2);

    }

    /**
     * 创建第三个array来存储两个数组比较的结果
     */
    private static void solution1(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int mid = (totalLength + 1) / 2;

        int[] nums3 = new int[totalLength];
        int v1 = 0, v2 = 0, v3 = 0;
        while (v3 < totalLength) {
            if (nums1[v1] <= nums2[v2]) {
                nums3[v3] = nums1[v1];
                v1++;
                v3++;
            } else {
                nums3[v3] = nums2[v2];
                v3++;
                v2++;
            }
            // 每轮下来都是后面一个
            if (v3 == mid + 1) {
                System.out.println(nums3[mid]);
                return;
            }
        }


    }

    /**
     * 不用额外的空间
     * @param nums1
     * @param nums2
     */
    private static void solution2(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int mid = totalLength % 2 == 0 ? (totalLength / 2 - 1) : (totalLength / 2);

        int v1 = 0;
        int v2 = 0;

        while (v1 + v2 != mid) {
            // 用来标记是哪一个数组发生了变化
            boolean flag1 = false;
            boolean flag2 = false;

            if (nums1[v1] < nums2[v2]) {
                v1++;
            } else if (nums1[v1] > nums2[v2]) {
                v2++;
            } else {

            }

            if (v1 + v2 == mid) {
                System.out.println();
            }

        }



    }
}

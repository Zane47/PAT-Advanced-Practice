package pat.advanced.twopoints;

import java.util.Arrays;
import java.util.Scanner;

/**todo: 测试点024错误.
 * 1. 先进行插入排序, 如果执行过程中发现与给定序列吻合, 就是插入排序,
 * 计算出下一步的序列后结束算法；
 * 2. 如果不是插入排序, 那么一定是归并排序, 模拟归并排序的过程,
 * 如果执行过程中发现与给定序列吻合, 那么计算出下一步的序列后结束算法。
 * <p>
 * 注意点:
 * 1. 初始序列不参与对比
 */
public class InsertOrMerge_1089 {

    private static int N;

    // 原始数列
    private static int[] origin;

    // 原始数据备份
    private static int[] tempOrigin;

    // 目标数组
    private static int[] target;

    public static void main(String[] args) {
        // ------------------------ input, initial ------------------------
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        // 原始数列
        origin = new int[N];
        // 原始数据备份
        tempOrigin = new int[N];
        // 目标数组
        target = new int[N];

        for (int i = 0; i < N; i++) {
            int v1 = sc.nextInt();
            origin[i] = v1;
            tempOrigin[i] = v1;
        }

        for (int i = 0; i < N; i++) {
            target[i] = sc.nextInt();
        }

        // ------------------------ do ------------------------
        // 插入序列中找到乐目标数组
        if (insertSort()) {
            System.out.println("Insertion Sort");
            printArray(tempOrigin);
        } else {
            System.out.println("Merge Sort");
            // 还原tempOrigin数组
            for (int i = 0; i < N; i++) {
                tempOrigin[i] = origin[i];
            }
            // 归并排序
            mergeSort();
        }

    }

    /**
     * 归并排序
     */
    private static void mergeSort() {
        // 是否存在数组中间步骤与target数组相同
        boolean flag = false;

        // 归并排序
        for (int step = 2; step / 2 <= N; step = step * 2) {
            if (step != 2 && isSame(tempOrigin, target)) {
                flag = true;
            }

            for (int i = 0; i < N; i = i + step) {
                // -1?, sort前闭后开
                Arrays.sort(tempOrigin, i, Math.min(i + step, N));
            }
            if (flag) {
                printArray(tempOrigin);
                return;
            }
        }

    }

    /**
     * 插入排序
     */
    private static boolean insertSort() {
        // 是否存在数组中间步骤与target数组相同
        boolean flag = false;
        // 进行n-1趟排序
        for (int i = 1; i < N - 1; i++) {
            // 不是初始序列(至少要走一次排序算法) && 中间步骤与目标相同
            if (i != 1 && isSame(tempOrigin, target)) {
                flag = true;
            }

            // ------------------------ 插入 ------------------------
            int temp = tempOrigin[i];
            int j = i;
            while (j > 0 && tempOrigin[j - 1] > temp) {
                tempOrigin[i] = tempOrigin[i - 1];
                j--;
            }
            tempOrigin[j] = temp;

            // 达到目标数组, 返回true, tempOrigin就是走了一次插入的结果
            if (flag) {
                return true;
            }
        }
        return false;
    }


    /**
     * 两个序列是否一致
     */
    private static boolean isSame(int[] v1, int[] v2) {
        for (int i = 0; i < N; i++) {
            if (v1[i] != v2[i]) {
                return false;
            }
        }
        return true;
    }


    private static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i != nums.length - 1) {
                System.out.print(" ");
            }
        }
    }


}

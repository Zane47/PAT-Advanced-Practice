package pat.advanced.tree.heap;

import java.util.Arrays;
import java.util.Scanner;

/**todo: error
 * -> InsertOrMerge_1089
 * <p>
 * 直接模拟插入排序和堆排序的每一步过程
 * 插入排序的左边是有序的
 * <p>
 * 步骤:
 * 1. 先直接插入排序, 然后查看给定序列和插入排序过程序列是否吻合
 * 如果吻合, 说明是插入排序, 计算出下一步运算的结果
 * <p>
 * 2. 如果不吻合, 那就只能是堆排序, 就按照堆排序的过程找到吻合的
 * 然后输出下一步运算的结果
 * <p>
 * 注意点:
 * 和1089一样, 初始序列不参与比较是否与目标序列相同,
 * 题目中说的中间序列是不包括初始序列的
 */
public class InsertionOrHeapSort_1098 {

    private static int N;

    // 原始数列
    private static int[] origin;

    // 原始数据备份, -> 记录排序过程
    private static int[] tempOrigin;

    // 目标数组
    private static int[] target;


    public static void main(String[] args) {
        // ------------------------ input ------------------------
        Scanner sc = new Scanner(System.in);

        // N <= 100
        N = sc.nextInt();

        // 原始数列
        origin = new int[111];
        // 原始数据备份
        tempOrigin = new int[111];
        // 目标数组
        target = new int[111];

        for (int i = 0; i < N; i++) {
            int v1 = sc.nextInt();
            origin[i] = v1;
            tempOrigin[i] = v1;
        }

        for (int i = 0; i < N; i++) {
            target[i] = sc.nextInt();
        }


        // ------------------------ do ------------------------

        if (insertSort()) {
            System.out.println("Insertion Sort");
            printArray(tempOrigin);
        } else {
            System.out.println("Heap Sort");

            // 还原tempOrigin数组
            for (int i = 0; i < N; i++) {
                tempOrigin[i] = origin[i];
            }
            // 堆排序
            heapSort();
        }


    }

    /**
     * 堆排序
     */
    private static void heapSort() {
        boolean flag = false;

        // 建堆
        for (int i = N / 2; i >= 1; i--) {
            downAdjust(i, N);
        }

        for (int i = N; i > 1; i--) {
            // 不是初始序列 && temp与target相同
            if (i != N && isSame(tempOrigin, target)) {
                flag = true;
            }

            // 做一次堆排序

            // 交换heap[i]与堆顶
            swap(i, 1);

            // 调整堆顶
            downAdjust(1, i - 1);

            // 如果是, 输出下一次的序列
            if (flag) {
                printArray(tempOrigin);
                return;
            }
        }
    }

    /**
     * 对heap数组在范围[low, high]中向下调整
     *
     * @param low  要调整结点的数组下标
     * @param high 一般为堆的最后一个结点下标
     */
    private static void downAdjust(int low, int high) {
        // 要调整的坐标
        int cur = low;
        // 先赋值做孩子
        int child = cur * 2;

        while (child <= high) {
            // child为最大权值孩子结点
            if (child + 1 <= high && tempOrigin[child + 1] > tempOrigin[child]) {
                child += 1;
            }

            // 孩子结点最大权值大于父亲 -> 做调整
            if (tempOrigin[child] > tempOrigin[cur]) {
                // 交换最大权值孩子结点和父亲
                swap(child, cur);
                // 进入下一层
                cur = child;
                child = cur * 2;
            } else {
                // 孩子结点的权值均比父亲结点的小，调整结束
                break;
            }
        }
    }


    private static boolean insertSort() {
        boolean flag = false;
        // n-1趟排序
        for (int i = 2; i <= N; i++) {
            // 不是初始序列 && 中间过程与目标相同
            if (i != 2 && isSame(tempOrigin, target)) {
                flag = true;
            }
            // 插入过程, 直接sort
            Arrays.sort(tempOrigin, 0, i);

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


    private static void swap(int x, int y) {
        int temp = tempOrigin[x];
        tempOrigin[x] = tempOrigin[y];
        tempOrigin[y] = temp;
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

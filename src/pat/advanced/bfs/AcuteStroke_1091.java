package pat.advanced.bfs;

import java.util.Scanner;

/**
 * 3d的图, 来求区域, -> bfs求区域
 * 相邻: 上下, 左右, 前后, 6个方向的相邻
 * 这里的"相邻": 不需要两两相邻, 只需要与块中的某一个"1"相邻, 那么该"1"就算在块中
 * <p>
 * 三维bfs:
 * 枚举每一个位置, 是否被遍历过,
 * i)  如果是0, 跳过,
 * ii) 如果是1, 那么要遍历他的上下左右前后的位置(范围内的), 如果还是1, 那么就接着遍历他的上下左右前后位置
 * hasVisited数组来看是否已经到queue中了
 */
public class AcuteStroke_1091 {

    // 定义上下左右前后转换坐标数组, 用for来遍历他的前后左右相邻的情况
    private static int[] dx = {0, 0, 1, -1, 0, 0};
    private static int[] dy = {0, 0, 0, 0, 1, -1};
    private static int[] dz = {1, -1, 0, 0, 0, 0};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // M and N are the sizes of each slice
        // (i.e. pixels of a slice are in an M×N matrix,
        // and the maximum resolution is 1286 by 128
        int M = sc.nextInt();
        int N = sc.nextInt();

        // # of slices of a brain, <= 60
        int L = sc.nextInt();

        // T is the integer threshold
        // (i.e. if the volume of a connected core is less than T, then that core must not be counted).
        int T = sc.nextInt();

        //


    }


}

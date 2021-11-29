package pat.advanced.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * fixme: 测试点4 5 超时
 * 当三维矩阵的所有元素都是1的时候, dfs的深度过深, 系统栈到上限, 从而爆栈
 *
 * 3d的图, 来求区域, -> bfs求区域
 * 相邻: 上下, 左右, 前后, 6个方向的相邻
 * 这里的"相邻": 不需要两两相邻, 只需要与块中的某一个"1"相邻, 那么该"1"就算在块中
 * <p>
 * 三维bfs:
 * 枚举每一个位置, 是否被遍历过,
 * i)  如果是0, 跳过,
 * ii) 如果是1, 那么要遍历他的上下左右前后的位置(范围内的), 如果还是1, 那么就接着遍历他的上下左右前后位置
 * hasVisited数组来看是否已经到queue中了
 * <p>
 * input:
 * 3 4 5 2
 * 1 1 1 1
 * 1 1 1 1
 * 1 1 1 1
 * 0 0 1 1
 * 0 0 1 1
 * 0 0 1 1
 * 1 0 1 1
 * 0 1 0 0
 * 0 0 0 0
 * 1 0 1 1
 * 0 0 0 0
 * 0 0 0 0
 * 0 0 0 1
 * 0 0 0 1
 * 1 0 0 0
 * output:
 * 26
 */
public class AcuteStroke_1091 {
    private static int M;
    private static int N;
    private static int L;
    private static int T;

    // 定义上下左右前后转换坐标数组, 用for来遍历他的前后左右相邻的情况
    private static int[] dx = {0, 0, 1, -1, 0, 0};
    private static int[] dy = {0, 0, 0, 0, 1, -1};
    private static int[] dz = {1, -1, 0, 0, 0, 0};

    // 是否被遍历过
    private static boolean[][][] __hasVisited;

    // brain
    private static int[][][] __brain;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // M and N are the sizes of each slice
        // (i.e. pixels of a slice are in an M×N matrix,
        // and the maximum resolution is 1286 by 128
        M = sc.nextInt();
        N = sc.nextInt();

        // # of slices of a brain, <= 60
        L = sc.nextInt();

        // T is the integer threshold
        // (i.e. if the volume of a connected core is less than T, then that core must not be counted).
        T = sc.nextInt();

        // L层 , 每一层是M*N
        // 坐标是(l,m,n), 第l层的第m行第n个
        __brain = new int[L][M][N];
        // brain[x][y][z] 来表示这个点是0是1
        for (int l = 0; l < L; l++) {
            for (int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    __brain[l][m][n] = sc.nextInt();
                }
            }
        }


        // 该点是否被遍历过
        __hasVisited = new boolean[L][M][N];

        // >= T的个数
        int result = 0;

        // 遍历所有的点, 做bfs
        for (int l = 0; l < L; l++) {
            for (int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    // 没有被访问 && 值是1
                    if (!__hasVisited[l][m][n] && __brain[l][m][n] == 1) {
                        result += bfs(l, m, n);
                    }
                }
            }
        }
        System.out.print(result);
    }

    /**
     *
     */
    private static int bfs(int l, int m, int n) {
        // 这个区域的个数
        int tempCount = 0;
        Node inputNode = new Node();
        inputNode.x = m;
        inputNode.y = n;
        inputNode.z = l;
        Queue<Node> queue = new LinkedList<>();
        queue.add(inputNode);
        // __hasVisited[x][y][z] = true;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            __hasVisited[inputNode.z][inputNode.x][inputNode.y] = true;

            tempCount++;
            // 周边的点
            for (int i = 0; i < 6; i++) {
                int newX = node.x + dx[i];
                int newY = node.y + dy[i];
                int newZ = node.z + dz[i];

                if (check(newX, newY, newZ)) {
                    Node newNode = new Node();
                    newNode.x = newX;
                    newNode.y = newY;
                    newNode.z = newZ;
                    queue.add(newNode);
                    __hasVisited[newZ][newX][newY] = true;
                }

            }
        }
        if (tempCount >= T) {
            return tempCount;
        } else {
            return 0;
        }
    }

    /**
     * 校验是否越界
     */
    private static boolean check(int x, int y, int z) {
        // 被访问过
        return x >= 0 && x < M && y >= 0 && y < N && z >= 0 && z < L &&
                !__hasVisited[z][x][y] && __brain[z][x][y] == 1;
    }

    static class Node {
        int x;
        int y;
        int z;
    }

}

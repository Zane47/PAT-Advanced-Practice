package pat.advanced.graph;

import java.util.Arrays;
import java.util.Scanner;

/**全源最短路径, Folyd, 弗洛伊德算法
 * 算法笔记p398例子
 * <p>
 * <p>
 * 6 8 0 // 6个顶点, 8条边, 起点为0号. 以下8行为8条边
 * 0 1 1 // 边 0 -> 1的边权为1
 * 0 3 4
 * 0 4 4
 * 1 3 2
 * 2 5 1
 * 3 2 2
 * 3 4 3
 * 4 5 3
 * output:
 * 0 1 5 3 4 6
 */
public class AlexanderDemo_Floyd {
    // 一个很大的数, 或10^9, 表示顶点之间的不连通
    private static final int INF = 0x3fffffff;

    // 起点到各点的最短路径长度
    private static int[] distance;

    // 顶点是否被访问过
    private static boolean[] hasVisited;

    // 前驱结点
    private static int[] pre;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 顶点个数
        int n = sc.nextInt();
        // 边数
        int m = sc.nextInt();
        // 起点
        int s = sc.nextInt();

        // 初始化是否遍历过的数组
        hasVisited = new boolean[n];

        // 初始化前驱结点数组, 每一个前驱结点先都设置成本身
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }

        // 初始化起点到顶点的距离数据, 起点到起点本身是0, 其他都是INF
        distance = new int[n];
        Arrays.fill(distance, INF);
        distance[s] = 0;

        // 图, 邻接矩阵, 初始化
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 记录边权
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u][v] = sc.nextInt();
        }



        // 测试: 输出start到v5的最短路径
        int end = 5;


        System.out.println();

        // print distance
        for (int i = 0; i < n; i++) {
            System.out.print(distance[i]);
            System.out.print(" ");
        }
    }




}

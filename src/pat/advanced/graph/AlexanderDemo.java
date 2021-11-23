package pat.advanced.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 算法笔记p373例子
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
public class AlexanderDemo {
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

        dijkstraFunc(graph, n, s);

        // 测试: 输出start到v5的最短路径
        int end = 5;
        DFS(s, end);

        System.out.println();

        // print distance
        for (int i = 0; i < n; i++) {
            System.out.print(distance[i]);
            System.out.print(" ");
        }
    }

    /**
     * 最短路径
     * s到end
     */
    private static void DFS(int s, int end) {
        // 当前到达起点s, 输出并返回
        if (end == s) {
            System.out.print(end);
            System.out.print(" -> ");
            return;
        }

        // 递归访问v的前驱结点pre[v]
        DFS(s, pre[end]);

        // 最深层递归回来, 已经输出了起点, 接下来输出每一层的顶点v就可以了
        System.out.print(end);
        System.out.print(" -> ");
    }

    /**
     * 起点s到各个顶点的最短距离 -> distance[n]
     */
    private static void dijkstraFunc(int[][] graph, int n, int s) {
        // 循环n次
        for (int i = 0; i < n; i++) {
            // u可以让d[u]最小, MIN存放该最小的d[u]
            int u = -1;
            int min = INF;
            // 遍历找到未访问的顶点中distance[]最小的
            for (int j = 0; j < n; j++) {
                if (!hasVisited[j] && distance[j] < min) {
                    u = j;
                    min = distance[j];
                }
            }

            // 没有找到小于INF的distance[], 说明剩下的顶点和s不连通
            if (u == -1) {
                return;
            }
            // 标记已访问, 然后更新从u出发能到达的所有顶点v, 更新distance[v]
            hasVisited[u] = true;
            for (int v = 0; v < n; v++) {
                // 没有被访问过 && 从u能到达v && 以u为中介点可以让distance[v]更优
                if (!hasVisited[v] && graph[u][v] != INF && distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    // 记录v的前驱结点是v
                    pre[v] = u;
                }
            }
        }
    }


}

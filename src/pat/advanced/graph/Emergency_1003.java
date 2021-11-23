package pat.advanced.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * lead your men to the place as quickly as possible,
 * and at the mean time, call up as many hands on the way as possible.
 * <p>
 * 输出:
 * the number of different shortest paths between C1 and C2
 * , and the maximum amount of rescue teams you can possibly gather
 * <p>
 * <p>
 * 最短路径 + 点权 + 最短路径个数
 */
public class Emergency_1003 {

    private static final int INF = 0x3fffffff;
    // 输入的点权
    private static int[] weight;


    // 是否已经遍历过
    private static boolean[] hasVisited;
    // 从s到点v的最短路径长度, 即最短距离
    private static int[] d;
    // 从s到点v的最短路径个数
    private static int[] num;
    // 从s到点v的最大重量
    private static int[] w;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N (≤500) - # of cities (and the cities are numbered from 0 to N−1),
        int N = sc.nextInt();
        // M - the number of roads,
        int M = sc.nextInt();
        // C1 start
        int C1 = sc.nextInt();
        // C2 destination
        int C2 = sc.nextInt();

        // 输入点权
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
        }

        // 初始化图
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < M; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            graph[c1][c2] = sc.nextInt();
            graph[c2][c1] = graph[c1][c2];
        }

        // 初始化访问数组
        hasVisited = new boolean[N];
        hasVisited[C1] = true;

        // 初始化num[], num[C1] = 1, 其余为0
        num = new int[N];
        num[C1] = 1;

        // 初始化w[], w[C1] = weight[C1], 其余为0
        w = new int[N];
        w[C1] = weight[C1];

        // 初始化distance
        d = new int[N];
        Arrays.fill(d, INF);
        d[C1] = 0;

        dijkstraFunc(graph, C1, C2, N);

        System.out.print(num[C2]);
        System.out.print(" ");
        System.out.print(w[C2]);
    }

    private static void dijkstraFunc(int[][] graph, int c1, int c2, int n) {
        // 循环n次
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;

            for (int j = 0; j < n; j++) {
                if (!hasVisited[j] && d[j] < min) {
                    u = j;
                    min = d[j];
                }
            }

            // 如果没有找到, return
            if (u == -1) {
                return;
            }

            // 从u能到达的v, 做更新
            for (int v = 0; v < n; v++) {
                if (!hasVisited[v] && graph[u][v] != INF) {
                    if (d[u] + graph[u][v] < d[v]) {
                        d[v] = d[u] + graph[u][v];
                        w[v] = w[u] + weight[v];
                        num[v] = num[u];
                    } else if (d[v] == d[u] + graph[u][v] && w[v] < w[u] + weight[v]) {
                        w[v] = w[u] + weight[v];
                        num[v] += num[u];
                    }
                }
            }


        }

    }


}

package pat.advanced.graph;

import java.util.*;

/**
 * Solution1: 直接用基础的dijkstra
 * 单源最短路径 -> dijkstra + DFS
 * 边权有cost, 第二标尺
 * 第一标尺: 最短路径, 如果相同, 那么就按照第二标尺边权取最小, 保证第二标尺唯一
 * 这道题目简单并且只有两个标尺, 理论上可以直接用基础的dijkstra -> solution1
 * <p>
 * 输出的数据是start -> destination的路径 + total distance + total cost
 */
public class TravelPlan_1030_1 {
    private static final int INF = 0x3fffffff;

    // start点到各个点的最短距离
    private static int[] __d;

    // 是否遍历过该顶点
    private static boolean[] hasVisited;

    // 顶点的c
    private static int[] __c;

    // 最短路径+最小边权和的前驱结点
    private static int[] pre;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // # of cities
        int N = sc.nextInt();
        // # of highways
        int M = sc.nextInt();
        // start cities
        int S = sc.nextInt();
        // destination cities
        int D = sc.nextInt();

        // 图初始化
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 边权初始化
        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], INF);
        }
        // 输入graph和cost
        // 无向图
        for (int i = 0; i < M; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int d = sc.nextInt();
            int c = sc.nextInt();
            graph[city1][city2] = d;
            graph[city2][city1] = d;
            cost[city1][city2] = c;
            cost[city2][city1] = c;
        }

        // 初始化visited
        hasVisited = new boolean[N];

        // 初始化__distance
        __d = new int[N];
        Arrays.fill(__d, INF);
        __d[S] = 0;


        // 初始化__c
        __c = new int[N];
        Arrays.fill(__c, INF);
        __c[S] = 0;

        // 初始化pre, 每个点的前驱是他自己
        pre = new int[N];
        for (int i = 0; i < N; i++) {
            pre[i] = i;
        }

        // dijkstra算法更新__d和__c, 计算path
        dijkstraFunc(graph, cost, N);

        // 根据pre前驱数组dfs输出符合最短路径(第一标尺)+最小__c(第二标尺)的结果
        // 倒过来, 从Destination开始找到Start
        dfs(D, S);

        // total distance
        System.out.print(__d[D]);
        System.out.print(" ");

        // total cost
        System.out.print(__c[D]);
    }

    /**
     * 打印路径
     */
    private static void dfs(int v, int start) {
        if (v == start) {
            System.out.print(v);
            System.out.print(" ");
            return;
        }

        dfs(pre[v], start);
        System.out.print(v);
        System.out.print(" ");
    }

    /**
     * 使用dijkstra算法来更新__d和__c
     */
    private static void dijkstraFunc(int[][] graph, int[][] cost, int n) {
        // 循环n次
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            // 遍历所有的顶点, 找到: 未被访问 && 最小
            for (int j = 0; j < n; j++) {
                if (!hasVisited[j] && __d[j] < min) {
                    u = j;
                    min = __d[j];
                }
            }

            // 如果咩有找到, return
            if (u == -1) {
                return;
            }

            // 找到了, 访问他
            hasVisited[u] = true;
            // 从u出发的所有v, 进行更新, 看是否可以让最短路径__d变短, 并且更新__c花费
            for (int v = 0; v < n; v++) {
                // 没有被访问 && u可以到v
                if (!hasVisited[v] && graph[u][v] != INF) {
                    // 如果通过u到v的距离比__d[v]小, 那么就更新__d, __c, pre
                    if (graph[u][v] + __d[u] < __d[v]) {
                        __d[v] = graph[u][v] + __d[u];
                        __c[v] = __c[u] + cost[u][v];
                        pre[v] = u;
                    } else if (graph[u][v] + __d[u] == __d[v]) {
                        // 如果相等, 那么就要根据第二标尺来更新 __c, pre
                        if (__c[u] + cost[u][v] < __c[v]) {
                            __c[v] = __c[u] + cost[u][v];
                            pre[v] = u;
                        }
                    }
                }
            }
        }
    }
}

package pat.advanced.graph;

import java.util.*;

/**Solution1: 直接用基础的dijkstra
 * 单源最短路径 -> dijkstra + DFS
 * 边权有cost, 第二标尺
 * 第一标尺: 最短路径, 如果相同, 那么就按照第二标尺边权取最小, 保证第二标尺唯一
 * 这道题目简单并且只有两个标尺, 理论上可以直接用基础的dijkstra -> solution1
 *
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

        // 点权初始化
        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], INF);
        }

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


        dijkstraFunc(graph, cost, S, D);




    }

    /**
     * 使用dijkstra算法来更新__d和__c
     */
    private static void dijkstraFunc(int[][] graph, int[][] cost, int start, int destination) {






    }
}

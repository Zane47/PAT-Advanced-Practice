package pat.advanced.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 纯dijkstra, 三把标尺
 * 这种写起来太恶心了, 还是用dijkstra + dfs, 标尺太多了
 */
public class AllRoadsLead2Rome_1087_2 {

    private static final int INF = 0x3fffffff;

    // 名字对应编号
    private static Map<String, Integer> __name2Index;
    // 编号对应名字
    private static Map<Integer, String> __index2Name;

    // 是否被访问
    private static boolean[] __hasVisited;

    // 最短路径距离数组, 从起点到第i点的最短距离
    private static int[] __d;

    // 从起点到第i点的最大点权
    private static double[] __w;

    // 三把标尺下的前驱结点
    private static int[] __pre;

    // 从起点到点i的最短路径个数
    private static int[] __numOfPath;

    // 从start点到点i最短路径的顶点个数
    private static int[] __numOfPoint;

    public static void main(String[] args) {
        // 初始化map
        __name2Index = new HashMap<>();
        __index2Name = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        // # of cities, [2, 200]
        int N = sc.nextInt();
        // # of routes
        int K = sc.nextInt();
        // name of starting city
        String start = sc.next();
        __name2Index.put(start, 0);
        __index2Name.put(0, start);

        // N-1 lines, name of city and the happiness except the starting city
        int[] happiness = new int[N];
        for (int i = 1; i < N; i++) {
            String cityName = sc.next();
            int h = sc.nextInt();
            happiness[i] = h;
            __index2Name.put(i, cityName);
            __name2Index.put(cityName, i);
        }

        // 初始化图
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], INF);
        }

        // cost
        for (int i = 0; i < K; i++) {
            String city1 = sc.next();
            String city2 = sc.next();
            int city1Index = __name2Index.get(city1);
            int city2Index = __name2Index.get(city2);
            int cost = sc.nextInt();
            graph[city1Index][city2Index] = cost;
            graph[city2Index][city1Index] = graph[city1Index][city2Index];
        }

        // 初始化访问结点
        __hasVisited = new boolean[N];

        // 初始化最短路径距离__distance
        __d = new int[N];
        Arrays.fill(__d, INF);
        // start点是0
        __d[0] = 0;

        // 初始化__pre
        __pre = new int[N];
        // 每一个点的前驱是他自身
        for (int i = 0; i < N; i++) {
            __pre[i] = i;
        }

        // 初始化__w, __w的初始值是起点的weight
        __w = new double[N];
        for (int i = 0; i < N; i++) {
            __w[i] = happiness[0];
        }

        // 初始化__numOfPath, 从起点到点i的最短路径个数
        // 起点的是1, 其他的是0
        __numOfPath = new int[N];
        __numOfPath[0] = 1;

        // 初始化__numOfPoint, 从start点到点i的最短路径个数
        __numOfPoint = new int[N];

        dijkstraFunc(graph, N, happiness);

        int destination = __name2Index.get("ROM");

        // # of different routes with least cost
        System.out.print(__numOfPath[destination]);
        System.out.print(" ");

        // the cost
        System.out.print(__d[destination]);
        System.out.print(" ");

        //
        // the happiness
        System.out.print((int) __w[destination]);
        System.out.print(" ");

        // the avg happiness
        System.out.println((int) __w[destination] / __numOfPoint[destination]);

        // path, 输出路径
        dfs(destination, 0);
    }

    /**
     * dfs 打印路径
     *
     * @param destination 目的地ROM
     * @param start       出发地
     */
    private static void dfs(int destination, int start) {
        if (destination == start) {
            System.out.print(__index2Name.get(start));
            return;
        }

        dfs(__pre[destination], start);
        System.out.print("->");
        System.out.print(__index2Name.get(destination));
    }

    /**
     * dijkstra + 三把标尺
     *
     * @param graph  graph
     * @param n      顶点个数
     * @param weight 点权
     */
    private static void dijkstraFunc(int[][] graph, int n, int[] weight) {
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            // 所有的点
            for (int j = 0; j < n; j++) {
                // 没有被访问过 && 起点到他的最小值小于min
                if (!__hasVisited[j] && __d[j] < min) {
                    u = j;
                    min = __d[j];
                }
            }
            if (u == -1) {
                return;
            }
            // 访问他
            __hasVisited[u] = true;
            for (int v = 0; v < n; v++) {
                // v咩有被访问过 && u可以到v
                if (!__hasVisited[v] && graph[u][v] != INF) {
                    // 第一标尺
                    if (__d[u] + graph[u][v] < __d[v]) {
                        // 更新起点到v的最短距离
                        __d[v] = __d[u] + graph[u][v];
                        // 更新__w, 因为有更优解, 直接更新成更优解的
                        __w[v] = __w[u] + weight[v];
                        // 更新num, 到点v的最短路径条数, 直接覆盖
                        __numOfPath[v] = __numOfPath[u];
                        // 前驱结点更新
                        __pre[v] = u;
                        // 更新顶点个数, start -> v的顶点个数 = start -> u的顶点个数 + 1
                        __numOfPoint[v] = __numOfPoint[u] + 1;
                    } else if (__d[u] + graph[u][v] == __d[v]) {
                        // 更新num, 到点v的最短路径条数
                        // 长度相等, 叠加
                        __numOfPath[v] += __numOfPath[u];

                        // 第二标尺, max happiness, 最大点权和
                        if (__w[u] + weight[v] > __w[v]) {
                            // 更新__w, 因为有更优解, 直接更新成更优解的
                            __w[v] = __w[u] + weight[v];
                            // 前驱结点更新
                            __pre[v] = u;
                            // 更新顶点个数, start -> v的顶点个数 = start -> u的顶点个数 + 1
                            __numOfPoint[v] = __numOfPoint[u] + 1;
                        } else if (__w[u] + weight[v] == __w[v]) {
                            // 第三标尺, max average happiness
                            // 点权之和相同, 取平均点权更大的那个
                            // avg = all happiness / # of points - 1
                            double avgOfV = __w[v] / __numOfPoint[v];
                            double avgOfU = (__w[u] + weight[v]) / (__numOfPoint[u] + 1);
                            // 以u为中介点平均值更大
                            if (avgOfU > avgOfV) {
                                // 前驱结点更新
                                __pre[v] = u;
                                // 更新顶点个数, start -> v的顶点个数 = start -> u的顶点个数 + 1
                                __numOfPoint[v] = __numOfPoint[u] + 1;
                            }
                        }
                    }
                }
            }
        }
    }
}

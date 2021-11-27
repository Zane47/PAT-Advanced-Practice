package pat.advanced.graph;

import java.util.*;

/**
 * dijkstra + DFS
 * 单源最短路径
 * 边权有cost, 第二标尺 -> dijkstra + DFS
 * 第一标尺: 最短路径, 如果相同, 那么就按照第二标尺边权取最小, 保证第二标尺唯一
 * 先用dijkstra算法求出所有的pre数组, 然后遍历该数组, 根据第二标尺来输出最小__c
 * 输出的数据是start -> destination的路径 + total distance + total cost
 */
public class TravelPlan_1030_2 {
    private static final int INF = 0x3fffffff;

    // start点到各个点的最短距离
    private static int[] __d;

    // 是否遍历过该顶点
    private static boolean[] hasVisited;

    // 顶点的c
    private static int[] __c;

    // 最短路径的前一个顶点(前驱顶点), 可能有多个
    private static List<List<Integer>> __pre;

    // 在dfs遍历时候, 用来表示这一次遍历的路径, 临时路径
    private static List<Integer> __tempPath;

    // 最优路径
    private static List<Integer> __resultPath;

    // dfs时, 记录的最小cost
    private static int minCost = INF;


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

        // 初始化__distance, start到自己的最短距离是0
        __d = new int[N];
        Arrays.fill(__d, INF);
        __d[S] = 0;


        // 初始化__c, start到自己的__c是0
        __c = new int[N];
        Arrays.fill(__c, INF);
        __c[S] = 0;

        // 初始化__pre, 给每一个顶点都初始化一个preList
        __pre = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> tempList = new LinkedList<>();
            __pre.add(tempList);
        }

        // dijkstra模板更新__d, __pre
        dijkstraFunc(graph, N);

        // 初始化dfs的时候用来表示这次遍历路径的list, 临时路径
        __tempPath = new LinkedList<>();

        // 初始化最优路径
        __resultPath = new LinkedList<>();

        // 根据__pre和__c来计算第二标尺
        dfs(D, S, cost);

        // 倒着输出resultPath
        for (int i = __resultPath.size() - 1; i >= 0; i--) {
            System.out.print(__resultPath.get(i));
            System.out.print(" ");
        }

        // 输出__d和__c
        System.out.print(__d[D]);
        System.out.print(" ");
        System.out.print(minCost);

    }

    /**
     * dfs, 对当前的路径tempPath, 边权相加, 然后与minCost作对比, 如果更优则更新
     * 从最后一个结点往前dfs找最短路径
     *
     * @param v    递归, 每层的顶点
     * @param S    start
     * @param cost
     */
    private static void dfs(int v, int S, int[][] cost) {
        // 递归边界, 到达起始顶点
        if (v == S) {
            // 加入最后的点
            __tempPath.add(v);
            // 记录遍历这条路径的cost
            int tempCost = 0;
            // 倒过来遍历这个临时路径, 计算tempCost
            for (int i = __tempPath.size() - 1; i > 0; i--) {
                int v1 = __tempPath.get(i);
                int v2 = __tempPath.get(i - 1);
                tempCost += cost[v1][v2];
            }
            // 如果更小, 更新路径和minCost
            if (tempCost < minCost) {
                minCost = tempCost;
                // __result清空, 然后添加, 不能直接__resultPath = __tempPath, 这样子引用的是同一个地址
                __resultPath.clear();
                __resultPath.addAll(__tempPath);
            }
            // 弹出最后一个, 也就是这上面添加的start结点
            __tempPath.remove(__tempPath.size() - 1);
            return;
        }

        // 如果不是start点, 计算路径
        __tempPath.add(v);
        // dfs遍历__pre[v]中的所有顶点
        for (Integer i : __pre.get(v)) {
            dfs(i, S, cost);
        }
        __tempPath.remove(__tempPath.size() - 1);

    }

    /**
     * 使用dijkstra算法来更新__d和__c
     */
    private static void dijkstraFunc(int[][] graph, int n) {
        // 循环n次
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            // 未被访问过 && __d最小的点
            for (int j = 0; j < n; j++) {
                if (!hasVisited[j] && __d[j] < min) {
                    u = j;
                    min = __d[j];
                }
            }

            // 没找到, return
            if (u == -1) {
                return;
            }

            // 找到了, 访问他
            hasVisited[u] = true;
            // 以u为起点的所有点, 做更新
            for (int v = 0; v < n; v++) {
                // v没有被访问过 && u能到v
                if (!hasVisited[v] && graph[u][v] != INF) {
                    // 以u为中介点可以让__d更小, 更新__d[v]和pre
                    if (__d[u] + graph[u][v] < __d[v]) {
                        // 更新__d
                        __d[v] = __d[u] + graph[u][v];

                        // v最短路径的前驱更新成了u, 之前的最短路径都删除, 更新成只有u的list
                        __pre.get(v).clear();
                        __pre.get(v).add(u);
                    } else if (__d[u] + graph[u][v] == __d[v]) {
                        // 以u为中介点, __d相等, 那么就说明又有一条最短路径, 添加__pre
                        // u作为v的前驱之一, 添加进入
                        __pre.get(v).add(u);
                    }
                }
            }
        }
    }
}

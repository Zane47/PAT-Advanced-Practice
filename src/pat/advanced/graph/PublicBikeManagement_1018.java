package pat.advanced.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 最短路径 + 点权
 * <p>
 * perfect = full / 2
 * <p>
 * PBMC will always choose the shortest path to reach that station.
 * If there are more than one shortest path,
 * the one that requires the least number of bikes sent from PBMC will be chosen.
 * 从PBMC发送最少的
 * <p>
 * <p>
 * 1. 在找到最短路径然后计算车子点权的过程中, 每个点都需要将自己的数量调整成为perfect,
 * 所以每个点的点权都减去perfect, 根据正负就可以知道是加车子还是减车子
 * <p>
 * 2. 第二标尺:
 * Note that if such a path is not unique,
 * output the one that requires minimum number of bikes that we must take back to PBMC.
 * <p>
 * 要求带回PBMC最少的车子
 *
 * 10 4 4 5
 * 4 8 9 0
 * 0 1 1
 * 1 2 1
 * 1 3 2
 * 2 3 1
 * 3 4 1
 * 1 0->1->2->3->4 2
 */
public class PublicBikeManagement_1018 {

    private static final int INF = 0x3fffffff;

    // 到某点的最短路径
    private static int[] __d;

    // visited数组
    private static boolean[] hasVisited;

    // 记录最短路径的前驱结点
    private static List<List<Integer>> __pre;

    // 记录临时路径, 用来和最终的路径做对比
    private static List<Integer> __tempPath;

    // 最终路径, 由临时路径对比得来
    private static List<Integer> __resultPath;

    // 从start要携带的最少的车辆数目, 第二标尺
    private static int minNeed = INF;

    // 带回start的车子数量
    private static int takeBack = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // <=100, 每个顶点的最大容量
        int cMax = sc.nextInt();
        int perfect = cMax / 2;
        // <=500顶点数量
        int N = sc.nextInt();

        // the stations are numbered from 1 to N,
        // and PBMC is represented by the vertex 0
        // 一共N+1个点

        // 出现问题的点
        int stationProblem = sc.nextInt();
        // road的数量
        int M = sc.nextInt();

        // 点权, 顶点的车子数量 , [1,N]
        int[] C = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            C[i] = sc.nextInt() - perfect;
        }

        // 初始化图 + 输入图
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            graph[v1][v2] = sc.nextInt();
            graph[v2][v1] = graph[v1][v2];
        }

        // 初始化访问数组
        hasVisited = new boolean[N + 1];

        // 初始化__d, 从start(这里是0)到每个顶点的最短巨鹿
        __d = new int[N + 1];
        Arrays.fill(__d, INF);
        __d[0] = 0;

        // 初始化前驱结点__pre
        __pre = new LinkedList<>();
        for (int i = 0; i < N + 1; i++) {
            List<Integer> temp = new LinkedList<>();
            __pre.add(temp);
        }

        // 先用dijkstra求最短路径, 可能有多条
        dijkstraFunc(graph, N + 1);

        // 更新了__preList, 那么接下来dfs来遍历, 同时看哪个是最优的
        // 初始化临时路径
        __tempPath = new LinkedList<>();

        // 初始化最终路径
        __resultPath = new LinkedList<>();

        // dfs来求所有最短路径中, 第二标尺最优的那个
        dfs(stationProblem, 0, C);

        // 输出结果
        // 从start带出的最少车辆数目
        System.out.print(minNeed);
        System.out.print(" ");
        // 最短路径
        for (int i = __resultPath.size() - 1; i >= 0; i--) {
            System.out.print(__resultPath.get(i));
            if (i != 0) {
                System.out.print("->");
            }
        }
        System.out.print(" ");
        // 带回车子的数量
        System.out.print(takeBack);
    }

    /**
     * @param v      递归每层的点
     * @param start  起点
     * @param weight 每个点的点权, 可能为负数
     */
    private static void dfs(int v, int start, int[] weight) {
        if (v == start) {
            // 加入这个点
            __tempPath.add(v);
            // 开始遍历__tempPath来计算, 需要倒着枚举, 这样子才是正过来的路径
            // 这条路径上, 需要从start带出的车子数量
            int need = 0;
            // 这条路径上, 需要带回到start的车子数量
            int remain = 0;
            for (int i = __tempPath.size() - 1; i >= 0; i--) {
                int node = __tempPath.get(i);
                // 该点点权 > 0, 那么就需要从该点带走车子
                if (weight[node] > 0) {
                    remain += weight[node];
                } else if (weight[node] < 0) {
                    // 该点点权 < 0, 给该点补充车子
                    if (remain + weight[node] < 0) {
                        // 现在有的车子数量还不够补充, 那么就要从start点带出车子来补充
                        need += Math.abs(remain + weight[node]);
                        remain = 0;
                    } else if (remain + weight[node] > 0) {
                        // 现有的车子数量足够了, 那么不需要从start带车子出来, 直接用remain的车子 补充

                        // remain -= weight[node];
                        // weight[node]本身就是负数
                        remain += weight[node];
                    }
                }
            }

            // 从start带出数目小
            if (need < minNeed) {
                minNeed = need;
                takeBack = remain;
                // 最短路径赋值
                __resultPath.clear();
                __resultPath.addAll(__tempPath);
            } else if (need == minNeed && remain < takeBack) {
                // 带出数目一样, 带回数目少, 优化带回数目, 更新最短路径
                takeBack = remain;
                __resultPath.clear();
                __resultPath.addAll(__tempPath);
            }

            __tempPath.remove(__tempPath.size() - 1);
            return;
        }

        __tempPath.add(v);
        for (Integer node : __pre.get(v)) {
            dfs(node, start, weight);
        }
        __tempPath.remove(__tempPath.size() - 1);
    }

    /**
     * 多条最短路径
     */
    private static void dijkstraFunc(int[][] graph, int n) {
        // n = N+1, 遍历所有顶点
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            // 遍历所有的
            for (int j = 0; j < n; j++) {
                if (!hasVisited[j] && __d[j] < min) {
                    u = j;
                    min = __d[j];
                }
            }
            // 没有找到
            if (u == -1) {
                return;
            }
            hasVisited[u] = true;
            // 找到了, 那么遍历所有的点v, 能通过u到达的v, 更新__d
            for (int v = 0; v < n; v++) {
                // 没有被访问过 && 能通过u到达
                if (!hasVisited[v] && graph[u][v] != INF) {
                    // 如果通过u到达v更加短, 那么就要更新__d[v]和更新最短路径前驱
                    if (__d[u] + graph[u][v] < __d[v]) {
                        __d[v] = __d[u] + graph[u][v];

                        // v最短路径的前驱更新成了u, 之前的最短路径都删除, 更新成只有u的list
                        __pre.get(v).clear();
                        __pre.get(v).add(u);
                    } else if (__d[u] + graph[u][v] == __d[v]) {
                        // 如果相等, 那么就要添加最短路径前驱
                        // 以u为中介点, __d相等, 那么就说明又有一条最短路径, 添加__pre
                        // u作为v的前驱之一, 添加进入
                        __pre.get(v).add(u);
                    }
                }
            }
        }
    }
}

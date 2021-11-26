package pat.advanced.graph;

import sun.awt.image.ImageWatched;

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
 */
public class PublicBikeManagement_1018 {

    private static final int INF = 0x3fffffff;

    // 到某点的最短路径
    private static int[] __d;

    // visited数组
    private static boolean[] hasVisited;

    // 记录最短路径的前驱结点
    private static List<List<Integer>> __pre;


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

        // dfs来求所有最短路径中, 第二标尺最优的那个

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
            if (u==-1) {
                return;
            }
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

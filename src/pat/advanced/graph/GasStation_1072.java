package pat.advanced.graph;

import java.util.*;

/**
 * 测试点4 超时
 * minimum distance between the station and any of the residential housing is as far away as possible.
 * However it must guarantee that all the houses are in its service range.
 * <p>
 * 加油站和房屋的最小距离尽可能远, 同时要保证所有的房子都要在加油站的服务范围之内
 * 输出满足条件的位置, 如果有多个位置, 第二标尺: 平均距离最小, 第三标尺: 最小的index number
 * <p>
 * 步骤:
 * 1. graph编号, 整个图顶点有N+M个, 居民房的编号直接[1,N], 加
 * 油站的编号, 去掉G, 然后加上居民编号, [N+1, N+M]
 * graph[n+m+1][n+m+1], 实例1: 房子[1, 5], 加油站[6, 8]
 * <p>
 * 2. 枚举每个加油站, 使用dijkstra算法计算到居民房的最短路径__distance是多少,
 * __distance: start点到所有点的最短距离, 也就是加油站到所有顶点的距离, 包括其他加油站, 但是只考虑居民房
 * 这里要注意:
 * 所有边都是真实存在的, 也就是说所有的顶点都要考虑,
 * 对应到示例中, G1距离最近的房屋是V2, 距离为2, G1 -> G2 -> 2
 * <p>
 * 3. 对于每一次枚举, 都要做比较
 * 获得到__distance之后, 遍历首先看是否合法,所有的距离要小于Ds,
 * 其次得到其中房屋距离加油站最短距离, 然后计算平均距离, 最后再看index
 * 如果都满足的话, 就更新结果
 */
public class GasStation_1072 {

    private static final int INF = 0x3fffffff;

    private static int N;
    private static int M;
    private static int Ds;

    private static int[][] __graph;

    // the index number of the best location
    private static int __resultIndex = -1;

    // minimum distances between the solution and all the houses, 取最大的最小距离
    private static double __minDistance = 0;

    // average distances between the solution and all the houses
    private static double __avgDistance = -1;


    // start点到所有顶点的最短距离
    private static int[] __distance;

    // 是否遍历过
    private static boolean[] __hasVisited;


    public static void main(String[] args) {
        // ------------------------ input, initial ------------------------
        Scanner sc = new Scanner(System.in);
        // total # of houses, <=10^3
        N = sc.nextInt();
        // total # of the candidate locations for the gas stations, <=10
        M = sc.nextInt();
        // # of roads connecting the houses and the gas stations, <=10^4
        int K = sc.nextInt();
        // the maximum service range of the gas station
        Ds = sc.nextInt();

        // init graph
        //[1, N + M + 1]
        __graph = new int[N + M + 1][N + M + 1];
        for (int i = 0; i < N + M + 1; i++) {
            Arrays.fill(__graph[i], INF);
        }

        // all the houses are numbered from 1 to N
        // all the candidate locations are numbered from G1 to GM.
        // [1, N]: 房屋;  [N+1, N+M]: 加油站
        for (int k = 0; k < K; k++) {
            String v1Str = sc.next();
            String v2Str = sc.next();
            int dist = sc.nextInt();
            int v1 = 0;
            int v2 = 0;
            if (v1Str.charAt(0) == 'G') {
                v1 = Integer.parseInt(v1Str.substring(1)) + N;
            } else {
                v1 = Integer.parseInt(v1Str);
            }
            if (v2Str.charAt(0) == 'G') {
                v2 = Integer.parseInt(v2Str.substring(1)) + N;
            } else {
                v2 = Integer.parseInt(v2Str);
            }
            __graph[v1][v2] = dist;
            __graph[v2][v1] = dist;
        }

        // ------------------------ do ------------------------
        // 枚举所有的加油站
        for (int i = N + 1; i < N + M + 1; i++) {
            // dijkstra求出__distance数组即可
            // __distance也是[1,N+M]
            dijkstraFunc(i);

            // ------------------------ 判断是否要更新 ------------------------
            // 遍历所有的居民房, 计算avg
            // 最大的最近距离, 距离满足<Ds的情况下, 尽可能大; 最远的最小距离
            double minDistance = INF;
            // 平均距离
            double avgDistance = 0;
            // 遍历房屋
            for (int j = 1; j < N + 1; j++) {
                // 有距离超过Ds, 那么就直接break掉, 这个加油站不行
                if (__distance[j] > Ds) {
                    // break前minDistance赋值, 因为有可能不行的数据在中间, minDistance已经被赋值过了
                    // 这里要还原
                    minDistance = -1;
                    break;
                }

                // 最近距离
                if (__distance[j] < minDistance) {
                    minDistance = __distance[j];
                }
                // 平均距离
                avgDistance += (double) __distance[j] / N;

            }
            // 大于Ds距离的加油站, 不符合要求, 直接找下一个加油站
            if (minDistance == -1) {
                continue;
            }

            // If there are more than one solution,
            // output the one with the smallest average distance to all the houses.
            // If such a solution is still not unique,
            // output the one with the smallest index number.
            // 最小平均距离
            // 要不要用BigInteger来比较大小?

            // 取最大的最近距离, 满足距离小于Ds的情况下, 距离尽可能小
            if (minDistance > __minDistance) {
                __minDistance = minDistance;
                __avgDistance = avgDistance;
                __resultIndex = i;
            } else if (minDistance == __minDistance) {
                if (__avgDistance > avgDistance) {
                    // output the one with the smallest index number.
                    __avgDistance = avgDistance;
                    __resultIndex = i;
                } else if (Math.abs(__avgDistance - avgDistance) < 1e-8) {
                    if (i < __resultIndex) {
                        __resultIndex = i;
                        __avgDistance = avgDistance;
                    }

                }


            }
        }
        // ------------------------ output ------------------------
        // the first line: the index number of the best location.
        // In the next line, print the minimum and the average distances
        // between the solution and all the houses.
        // be accurate up to 1 decimal place.
        // If the solution does not exist, simply output No Solution.

        // 未更新说明无解
        if (__resultIndex == -1) {
            System.out.println("No Solution");
        } else {
            // 还原成G的加油站编号
            __resultIndex -= N;
            System.out.println("G" + __resultIndex);
            System.out.printf("%.1f %.1f", __minDistance, __avgDistance);
        }
    }

    /**
     * dijkstra算法求出__distance
     *
     * @param start
     */
    private static void dijkstraFunc(int start) {
        // 每一次循环都要更新新的
        __distance = new int[N + M + 1];
        Arrays.fill(__distance, INF);
        __distance[start] = 0;
        __hasVisited = new boolean[N + M + 1];

        // 循环N+M次
        for (int i = 0; i < N + M; i++) {
            // u可以让d[u]最小, MIN存放该最小的d[u]
            int u = -1;
            int min = INF;

            // 遍历找到未访问的顶点中distance[]最小的
            for (int j = 1; j < N + M + 1; j++) {
                // 未被访问 && 距离小于min
                if (!__hasVisited[j] && __distance[j] < min) {
                    u = j;
                    min = __distance[j];
                }
            }

            if (u == -1) {
                return;
            }

            // 找到了, 访问他
            __hasVisited[u] = true;
            // 通过u能到达的点, 做更新
            for (int v = 1; v < N + M + 1; v++) {
                // 未被访问 && 可以通过u到达 && 以u为中介点, 可以使从start到v距离更小
                if (!__hasVisited[v] && __graph[u][v] != INF
                        && __distance[u] + __graph[u][v] < __distance[v]) {
                    __distance[v] = __distance[u] + __graph[u][v];
                }
            }
        }
    }
}

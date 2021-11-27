package pat.advanced.graph;

import java.util.*;

/**dijkstra + dfs
 * the least cost while gaining the most happiness.
 * the destination is always ROM
 * <p>
 * Map来对应名字和编号
 * <p>
 * <p>
 * input:
 * 6 7 HZH
 * ROM 100
 * PKN 40
 * GDN 55
 * PRS 95
 * BLN 80
 * ROM GDN 1
 * BLN ROM 1
 * HZH PKN 1
 * PRS ROM 2
 * BLN HZH 2
 * PKN GDN 1
 * HZH PRS 1
 * <p>
 * output:
 * 3 3 195 97
 * HZH->PRS->ROM
 * <p>
 * <p>
 * solution: dijkstra + dfs
 * 先用dijkstra求出所有的最短路径, 然后再用第二第三标尺来求最终结果
 */
public class AllRoadsLead2Rome_1087_1 {
    private static final int INF = 0x3fffffff;
    // 名字对应编号
    private static Map<String, Integer> __name2Index;
    // 编号对应名字
    private static Map<Integer, String> __index2Name;

    // 是否被访问
    private static boolean[] __hasVisited;

    // 最短路径距离数组
    private static int[] __d;

    // 记录前驱结点, 可能有多条最短路径, 有多个
    private static List<List<Integer>> __preList;

    // 记录临时的最短路径
    private static List<Integer> __tempPath;

    // 记录最短路径结果
    private static List<Integer> __resultPath;

    // 记录最大点权
    private static double __maxWeight = -1;

    // 记录平均点权
    private static double __avgWeight = -1;

    // 记录最短路径条数
    private static int __numOfShortestPath = 0;

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

        // 初始化最短路径__preList
        __preList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> temp = new LinkedList<>();
            __preList.add(temp);
        }
        // dijkstra
        dijkstraFunc(graph, N);

        // 初始化临时最短路径
        __tempPath = new LinkedList<>();

        // 初始化最终的最短路径
        __resultPath = new LinkedList<>();

        // dfs枚举遍历所有的最短路径, 根据第二第三标尺来进行判断
        dfs(__name2Index.get("ROM"), 0, happiness);

        // 输出
        // in the first line of output, you must print 4 numbers:
        // the number of different routes with the least cost, the cost, the happiness, and the average happiness (take the integer part only) of the recommanded route.
        // # of different routes with least cost
        System.out.print(__numOfShortestPath);
        System.out.print(" ");

        // the cost
        int cost = 0;
        for (int i = __resultPath.size() - 1; i > 0; i--) {
            cost += graph[__resultPath.get(i)][__resultPath.get(i-1)];
        }
        System.out.print(cost);
        System.out.print(" ");

        // the happiness
        System.out.print((int) __maxWeight);
        System.out.print(" ");

        // the avg happiness
        System.out.println((int) __avgWeight);

        // the route: city1->city2->...->ROM
        for (int i = __resultPath.size() - 1; i >= 0; i--) {
            System.out.print(__index2Name.get(__resultPath.get(i)));
            if (i != 0) {
                System.out.print("->");
            }
        }

    }

    /**
     * 遍历所有的前驱结点 -> 拼出完整路径 -> 根据第二, 第三标尺做比较
     *
     * @param v      当前结点
     * @param start  起始结点
     * @param weight happiness
     */
    private static void dfs(int v, int start, int[] weight) {
        if (v == start) {
            __numOfShortestPath++;

            __tempPath.add(v);
            // 该最短路径的点权和
            double tempWeight = 0;
            // 该最短路径的顶点个数, 用来计算平均weight, 要-1
            int numOfTempPath = __tempPath.size() - 1;
            // 计算这条__tempPath的第二第三标尺
            for (int i = __tempPath.size() - 1; i >= 0; i--) {
                Integer node = __tempPath.get(i);
                tempWeight += weight[node];
            }
            if (tempWeight > __maxWeight) {
                __maxWeight = tempWeight;
                __avgWeight = tempWeight / numOfTempPath;
                __resultPath.clear();
                __resultPath.addAll(__tempPath);
            } else if (tempWeight == __maxWeight) {
                double tempAvgWeight = tempWeight / numOfTempPath;
                if (tempAvgWeight > __avgWeight) {
                    __avgWeight = tempAvgWeight;
                    __resultPath.clear();
                    __resultPath.addAll(__tempPath);
                }
            }
            __tempPath.remove(__tempPath.size() - 1);
            return;
        }

        __tempPath.add(v);
        for (Integer node : __preList.get(v)) {
            dfs(node, start, weight);
        }
        __tempPath.remove(__tempPath.size() - 1);
    }


    private static void dijkstraFunc(int[][] graph, int n) {
        // 遍历n次
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            // 遍历__d, 查看最短距离的
            for (int j = 0; j < n; j++) {
                // 未被访问过 && 最短距离小于min
                if (!__hasVisited[j] && __d[j] < min) {
                    u = j;
                    min = __d[j];
                }
            }
            // 咩有找到
            if (u == -1) {
                return;
            }
            // 找到了
            __hasVisited[u] = true;
            // 遍历所有的, 看能不能通过从u到v, 让__d[v]更小
            for (int v = 0; v < n; v++) {
                // 没有访问过 && 能从u到v
                if (!__hasVisited[v] && graph[u][v] != INF) {
                    // 最短距离有更小的, 做更新
                    if (__d[u] + graph[u][v] < __d[v]) {
                        __d[v] = __d[u] + graph[u][v];
                        __preList.get(v).clear();
                        __preList.get(v).add(u);
                    } else if (__d[u] + graph[u][v] == __d[v]) {
                        // 最短距离相等, 不止一条, 增加前驱结点
                        __preList.get(v).add(u);
                    }
                }
            }
        }
    }


}

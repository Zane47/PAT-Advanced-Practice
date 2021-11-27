package pat.advanced.graph;

import java.util.*;

/**
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
 * 先用dijkstra求出所有的最短路径, 然后再用第二标尺来求最终结果
 */
public class AllRoadsLead2Rome_1087 {
    private static final int INF = 0x3fffffff;
    // 名字对应编号
    private static Map<String, Integer> name2Index;
    // 编号对应名字
    private static Map<Integer, String> index2Name;

    // 是否被访问
    private static boolean[] hasVisited;

    // 最短路径距离数组
    private static int[] __d;

    // 记录前驱结点, 可能有多条最短路径, 有多个
    private static List<List<Integer>> __preList;

    // 记录临时的最短路径
    private static List<Integer> __tempPath;

    // 记录最短路径结果
    private static List<Integer> __resultPath;

    public static void main(String[] args) {
        // 初始化map
        name2Index = new HashMap<>();
        index2Name = new HashMap<>();

        Scanner sc = new Scanner(System.in);
        // # of cities, [2, 200]
        int N = sc.nextInt();
        // # of routes
        int K = sc.nextInt();
        // name of starting city
        String start = sc.next();
        name2Index.put(start, 0);
        index2Name.put(0, start);

        // N-1 lines, name of city and the happiness except the starting city
        int[] happiness = new int[N];
        for (int i = 1; i < N; i++) {
            String cityName = sc.next();
            int h = sc.nextInt();
            happiness[i] = h;
            index2Name.put(i, cityName);
            name2Index.put(cityName, i);
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
            int city1Index = name2Index.get(city1);
            int city2Index = name2Index.get(city2);
            int cost = sc.nextInt();
            graph[city1Index][city2Index] = cost;
            graph[city2Index][city1Index] = graph[city1Index][city2Index];
        }

        // 初始化访问结点
        hasVisited = new boolean[N];

        // 初始化最短路径距离__distance
        __d = new int[N];
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
        dfs(name2Index.get("ROM"), 0, happiness);

        // 输出


    }

    /**
     *
     * @param v
     * @param start
     * @param weight
     */
    private static void dfs(int v, int start, int[] weight) {


    }

    private static void dijkstraFunc(int[][] graph, int n) {
        // 遍历n次
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            // 遍历__d, 查看最短距离的
            for (int j = 0; j < n; j++) {
                // 未被访问过 && 最短距离小于min
                if (!hasVisited[j] && __d[j] < min) {
                    u = j;
                    min = __d[j];
                }
            }
            // 咩有找到
            if (u == -1) {
                return;
            }
            // 找到了
            hasVisited[u] = true;
            // 遍历所有的, 看能不能通过从u到v, 让__d[v]更小
            for (int v = 0; v < n; v++) {
                // 没有访问过 && 能从u到v
                if (!hasVisited[v] && graph[u][v] != INF) {
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

package pat.advanced.graph.redo;

import java.util.*;

/**
 * 用dijkstra求出所有的最短路径, 然后dfs根据其他标尺输出符合要求的唯一路径
 */
public class AllRoadsLead2Rome_1087 {

    private static final int INF = 0x3fffffff;

    private static int[][] __graph;

    private static Map<Integer, String> __index2City;
    private static Map<String, Integer> __city2Index;

    private static int[] __happiness;

    // 起点到编号为i的点的字段路径距离
    private static int[] __distance;

    // 是否被访问过
    private static boolean[] __hasVisited;


    // preList
    private static List<List<Integer>> __preList;

    // 最终的最短路径
    private static List<Integer> __resultList;

    // 临时路径, 用来计算最终的最短路径
    private static List<Integer> __tempList;

    // 最短路径个数
    private static int __numOfShortestPath;

    // 最多的快乐
    private static double __maxHappiness;

    // 最多的平均快乐
    private static double __maxAvgHappiness;


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // [2,200]
        int N = sc.nextInt();
        int K = sc.nextInt();

        __graph = new int[N][N];

        String startCity = sc.next();

        __index2City = new HashMap<>();
        __city2Index = new HashMap<>();

        __index2City.put(0, startCity);
        __city2Index.put(startCity, 0);


        // The next N−1 lines : the name of a city and an integer that represents the happiness one can gain from that city, except the starting city
        __happiness = new int[N];
        __happiness[0] = 0;
        for (int i = 1; i < N; i++) {
            String name = sc.next();
            int happiness = sc.nextInt();
            __city2Index.put(name, i);
            __index2City.put(i, name);
            __happiness[i] = happiness;
        }

        __distance = new int[N];
        Arrays.fill(__distance, INF);
        __distance[0] = 0;

        __graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(__graph[i], INF);
        }

        for (int k = 0; k < K; k++) {
            String city1 = sc.next();
            String city2 = sc.next();
            int cost = sc.nextInt();
            int index1 = __city2Index.get(city1);
            int index2 = __city2Index.get(city2);
            __graph[index1][index2] = cost;
            __graph[index2][index1] = cost;
        }

        __hasVisited = new boolean[N];

        int destination = __city2Index.get("ROM");

        // 初始化preList
        __preList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> list = new ArrayList<>();
            __preList.add(list);
        }

        dijkstraFunc(N);

        // 找到了所有的最短路径, 接下来dfs遍历所有的最短路径, 然后根据其他标尺来做比较

        __resultList = new ArrayList<>();
        __tempList = new ArrayList<>();

        __numOfShortestPath = 0;
        __maxHappiness = -1;
        __maxAvgHappiness = -1;
        dfs(destination, 0);

        // output
        // # of different routes with the least cost, the cost, the happiness, and the average happiness (take the integer part only) of the recommanded route. Then in the next line, you are supposed to print the route in the format City1->City2->...->ROM.
        System.out.print(__numOfShortestPath + " ");

        int cost = 0;
        for (int i = 0; i < __resultList.size() - 1; i++) {
            int city1 = __resultList.get(i);
            int city2 = __resultList.get(i + 1);
            cost += __graph[city1][city2];
        }
        System.out.print(cost + " ");

        System.out.print((int) __maxHappiness + " ");
        System.out.println((int) __maxAvgHappiness);

        // __resultList中是倒序的

        for (int i = __resultList.size() - 1; i >= 0; i--) {
            String city = __index2City.get(__resultList.get(i));
            System.out.print(city);
            if (i != 0) {
                System.out.print("->");
            }
        }


    }

    private static void dfs(int v, int start) {
        if (v == start) {
            __numOfShortestPath++;

            __tempList.add(v);


            // 标尺
            // the one with the maximum happiness will be recommanded.

            // If such a route is still not unique, then we output the one with the maximum average happiness
            double tempHappiness = 0;
            for (Integer city : __tempList) {
                tempHappiness += __happiness[city];
            }

            // 195/2 = 97, size-1
            int count = __tempList.size() - 1;

            // 第一把标尺
            if (tempHappiness > __maxHappiness) {
                __maxHappiness = tempHappiness;
                __maxAvgHappiness = tempHappiness / count;
                __resultList.clear();
                __resultList.addAll(__tempList);
            } else if (tempHappiness == __maxHappiness) {
                // 相等, 用第二把标尺, maximum average happiness
                double tempAvgHappiness = tempHappiness / count;
                if (tempAvgHappiness > __maxAvgHappiness) {
                    __resultList.clear();
                    __resultList.addAll(__tempList);
                }
            }
            __tempList.remove(__tempList.size() - 1);
        }

        __tempList.add(v);
        // v的前驱结点全遍历
        for (Integer next : __preList.get(v)) {
            dfs(next, start);
        }
        // 回溯
        __tempList.remove(__tempList.size() - 1);
    }

    /**
     * dijkstra求出所有的最短路径
     *
     * @param n
     */
    private static void dijkstraFunc(int n) {
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;
            for (int j = 0; j < n; j++) {
                // 最短距离小于min && 未被访问过
                if (__distance[j] < min && !__hasVisited[j]) {
                    u = j;
                    min = __distance[j];
                }
            }

            // 没找到
            if (u == -1) {
                return;
            }

            // 访问他
            __hasVisited[u] = true;
            // 从u出发能到达的所有点
            for (int v = 0; v < n; v++) {
                // 没有被访问过 && 从u出发可以到达
                if (!__hasVisited[v] && __graph[u][v] != INF) {
                    // 有更小的, 更新最短路径
                    if (__distance[u] + __graph[u][v] < __distance[v]) {
                        __distance[v] = __distance[u] + __graph[u][v];
                        __preList.get(v).clear();
                        __preList.get(v).add(u);
                    } else if (__distance[u] + __graph[u][v] == __distance[v]) {
                        __preList.get(v).add(u);
                    }
                }
            }
        }
    }


}

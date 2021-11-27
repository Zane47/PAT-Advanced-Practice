package pat.advanced.graph;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 纯dijkstra, 三把标尺
 */
public class AllRoadsLead2Rome_1087_2 {
    private static final int INF = 0x3fffffff;

    // 名字对应编号
    private static Map<String, Integer> __name2Index;
    // 编号对应名字
    private static Map<Integer, String> __index2Name;

    // 是否被访问
    private static boolean[] __hasVisited;

    // 最短路径距离数组
    private static int[] __d;

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


    }





}

package pat.advanced.graph;

import java.util.*;

/**
 * 超时, 但是学习这个方法的环处理
 * 相对比1, 修改dfs环的情况
 *
 * 8 59
 * AAA BBB 10
 * BBB AAA 20
 * AAA CCC 40
 * DDD EEE 5
 * EEE DDD 70
 * FFF GGG 30
 * GGG HHH 20
 * HHH FFF 10
 *
 * 2
 * AAA 3
 * GGG 3
 *
 */
public class HeadOfAGang_1034_2 {
    // 阈值K
    private static int K;

    private static int[][] __graph;

    // 顶点是否遍历
    private static boolean[] __hasVertexVisited;

    // 路径是否遍历
    private static boolean[][] __hasPathVisited;

    // 姓名到编号
    private static Map<String, Integer> __nameToIndex;

    // 编号到姓名
    private static Map<Integer, String> __indexToName;

    // 总人数, 用来设置编号
    private static int __number = 0;

    // 点权
    private static int[] __weight;

    // 最后结果, {[head, memberNum], [head, memberNum], [head, memberNum]}
    private static List<int[]> __result;


    // ------------------------ 当dfs的时候, 每个for要使用的变量 ------------------------

    // 头目
    private static int __head = 0;
    // gang成员数量
    private static int __memberNum = 0;
    // gang连通块边权之和
    private static int __totalRelationWeight = 0;


    public static void main(String[] args) {
        // ------------------------ input + initial ------------------------
        Scanner sc = new Scanner(System.in);
        // <=1k, the number of phone calls
        int N = sc.nextInt();
        // <=1k, the weight threthold
        K = sc.nextInt();


        // input的是通话记录个数, 不是人的个数
        // 先初始化最大的, 然后按照人的数量(map)再初始化
        int[][] tempGraph = new int[2010][2010];
        for (int i = 0; i < 2010; i++) {
            Arrays.fill(tempGraph[i], 0);
        }

        int[] tempWeight = new int[2010];
        Arrays.fill(tempWeight, 0);

        __nameToIndex = new HashMap<>();
        __indexToName = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String name1 = sc.next();
            String name2 = sc.next();
            int time = sc.nextInt();

            if (!__nameToIndex.containsKey(name1)) {
                int id = __number;
                __nameToIndex.put(name1, id);
                __indexToName.put(id, name1);
                __number++;
                // 点权加上去
                tempWeight[id] += time;
            } else {
                tempWeight[__nameToIndex.get(name1)] += time;
            }

            if (!__nameToIndex.containsKey(name2)) {
                int id = __number;
                __nameToIndex.put(name2, id);
                __indexToName.put(id, name2);
                __number++;
                // 点权加上去
                tempWeight[id] += time;
            } else {
                tempWeight[__nameToIndex.get(name2)] += time;
            }

            int v1 = __nameToIndex.get(name1);
            int v2 = __nameToIndex.get(name2);
            tempGraph[v1][v2] += time;
            tempGraph[v2][v1] += time;
        }


        // 其实不需要下面的赋值, 因为在dfs for循环中, i的大小设置就是__number

        __graph = new int[__number][__number];
        for (int i = 0; i < __number; i++) {
            Arrays.fill(__graph[i], 0);
        }
        // 点权初始化
        __weight = new int[__number];
        __weight = Arrays.copyOfRange(tempWeight, 0, __number);


        // 顶点是否被访问过
        __hasVertexVisited = new boolean[__number];
        Arrays.fill(__hasVertexVisited, false);

        // 路径是否被访问过. 默认初始化false
        __hasPathVisited = new boolean[__number][__number];


        // 根据map的size来初始化graph
        for (int i = 0; i < __number; i++) {
            __graph[i] = Arrays.copyOfRange(tempGraph[i], 0, __number);
        }

        __result = new ArrayList<>();

        // ------------------------遍历+处理------------------------

        // dfs遍历图, 获取每个连通块信息
        dfsTravel();


        // ------------------------output------------------------
        // first print in a line the total number of gangs.
        // Then for each gang, print in a line the name of the head and
        // the total number of the members.

        // It is guaranteed that the head is unique for each gang.
        // output must be sorted according to the alphabetical order of
        // the names of the heads.

        // 根据名字字母排序
        Collections.sort(__result, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                String s1 = __indexToName.get(o1[0]);
                String s2 = __indexToName.get(o2[0]);
                return s1.compareTo(s2);
            }
        });

        // print
        System.out.println(__result.size());

        for (int i = 0; i < __result.size(); i++) {
            int[] v1 = __result.get(i);
            System.out.print(__indexToName.get(v1[0]));
            System.out.print(" ");
            System.out.print(v1[1]);
            if (i != __result.size() - 1) {
                System.out.println();
            }
        }

    }

    /**
     * dfs遍历图, 获取每个连通块信息
     */
    private static void dfsTravel() {
        // 遍历总人数
        for (int i = 0; i < __number; i++) {
            // 没有被访问过
            if (!__hasVertexVisited[i]) {
                // 没到一个新的连通块都要重新赋值和还原
                __head = i;
                __memberNum = 0;
                __totalRelationWeight = 0;

                dfs(i);

                if (__memberNum > 2 && __totalRelationWeight > K) {
                    int[] v1 = new int[]{__head, __memberNum};
                    __result.add(v1);
                }
            }
        }

    }

    /**
     * 遍历该连通块, 更新参数
     *
     * @param nowNode 现在访问的node
     */
    private static void dfs(int nowNode) {
        __memberNum++;
        __hasVertexVisited[nowNode] = true;

        // 如果点权大的话, 那么更新head
        if (__weight[nowNode] > __weight[__head]) {
            __head = nowNode;
        }

        // 通过这个点再接着遍历下去
        // 注意可能有环的情况
        for (int i = 0; i < __number; i++) {
            // 能通过nowNode到达
            if (__graph[i][nowNode] != 0) {
                // 增加连通块边权和
                __totalRelationWeight += __graph[i][nowNode];

                // 删除这条边, 防止回头
                __graph[i][nowNode] = __graph[nowNode][i] = 0;

                if (!__hasVertexVisited[i]) {
                    dfs(i);
                }


            }
        }
    }
}

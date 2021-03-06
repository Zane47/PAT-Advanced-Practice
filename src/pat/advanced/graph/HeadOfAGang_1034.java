package pat.advanced.graph;

import java.util.*;

/**测试点3, 超时-2
 * The weight of a relation:
 * the total time length of all the phone calls made between the two persons.
 * <p>
 * "Gang"
 * a cluster of more than 2 persons who are related to each other
 * with total relation weight being greater than a given threshold K.
 * <p>
 * In each gang, the one with maximum total weight is the head.
 * Now given a list of phone calls, you are supposed to find the gangs and the heads.
 * <p>
 * <p>
 * 连通分量, 顶点数量>2, 每一个边权之和大于K, 就是gang
 * <p>
 * 步骤:
 * 1. 姓名和编号要做映射, 用MAP, 或者直接建立一个Gang Class
 * 2. 每个人的点权是边权之和, 那么输入的时候每一行数据都给两个顶点加上权值
 * 3. 遍历图, DFS, 要获取: (1)点权最大的结点, (2)每个连通分量的顶点个数 (3)total weight
 * 4. dfs获得每个连通分量total, 如果符合条件, 则记录该连通分块
 * <p>
 * 重要注意点:
 * 1. 可能有环, 在dfs的时候需要做处理
 * 在第一个测试用例的时候, FGH(567)形成了环, 如果正常的遍历, 就只会遍历5->6, 6->7, 而5->7会因为之前两个遍历的时候三个顶点都遍历过了而导致不再遍历
 * -> 解决思路:
 * (1) 增加边是否遍历过
 * (2) 需要主要的是, 因为dfs中会增加gangnumberNum,
 * 但是如果是因为边权没有被访问过, 但是顶点被访问过了再dfs的话, 会重复计算gangmember, 需要手动-1
 *
 * 2. graph的size, 一开始初始化的时候直接初始化最大的可能, > 2000, 这里取2010
 * 然后再根据人数(顶点数)来初始化真正的graph大小
 */
public class HeadOfAGang_1034 {
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
                // 没有被访问过
                if (!__hasVertexVisited[i]) {
                    // 边权增加
                    __totalRelationWeight += __graph[i][nowNode];

                    __hasPathVisited[i][nowNode] = true;
                    __hasPathVisited[nowNode][i] = true;

                    dfs(i);
                } else if (__hasVertexVisited[i] && !__hasPathVisited[i][nowNode]) {
                    // 被访问过, 但是有路径没有被访问过
                    __hasPathVisited[i][nowNode] = true;
                    __hasPathVisited[nowNode][i] = true;

                    __totalRelationWeight += __graph[i][nowNode];

                    // 从这里进入dfs的话, 就说明已经遍历过这个顶点,
                    // dfs中会__memberNum++, 不需要这个++
                    // 手动-1
                    __memberNum--;
                    dfs(i);
                }
            }
        }
    }
}

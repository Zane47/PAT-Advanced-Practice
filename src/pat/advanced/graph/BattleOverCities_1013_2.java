package pat.advanced.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 邻接表表示graph
 * 修改判断逻辑
 * <p>
 * <p>
 * tell the number of highways need to be repaired, quickly.
 * <p>
 * <p>
 * 给定一个无向图并规定，当删除图中的某个顶点时，将会同时把与之连接的边一起删除。
 * 接下来给出k个查询，每个查询给出一个欲删除的顶点编号，求删除该顶点（和与其连接的边）后
 * 需要增加多少条边，才能使图变为连通（注：k次查询均在原图上进行）。
 * <p>
 * 3 2 3
 * 1 2
 * 1 3
 * 1 2 3
 * output:
 * 1
 * 0
 * 0
 * <p>
 * 1. 给一个无向图, 如何计算要添加的边, 让整个图连通
 * 添加边的个数 = 连通块个数-1 -> 证明见< 算法笔记上机训练 > P339, 自己画图粗略的出来关系
 * 2. 求连通块个数, dfs求双层for的时候, 外层记录
 */
public class BattleOverCities_1013_2 {

    private static int N;

    public static void main(String[] args) {
        // ------------------------ input + init ------------------------
        Scanner sc = new Scanner(System.in);

        // the total number of cities, <1000
        N = sc.nextInt();
        // the number of remaining highways,
        int M = sc.nextInt();
        // and the number of cities to be checked,
        int K = sc.nextInt();

        // [1, N]city编号
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            List<Integer> list = new ArrayList<>();
            graph.add(list);
        }

        for (int i = 0; i < M; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            graph.get(city1).add(city2);
            graph.get(city2).add(city1);

        }

        for (int k = 0; k < K; k++) {
            int lostCity = sc.nextInt();
            // 连通块个数
            int blockNum = 0;

            // 顶点是否被访问过
            boolean[] hasVisited = new boolean[N + 1];

            // 遍历所有顶点
            for (int i = 1; i <= N; i++) {
                // 没有被访问过 && 没有被攻占
                if (!hasVisited[i] && i != lostCity) {
                    blockNum++;
                    // 遍历顶点i所在的连通块
                    dfs(graph, i, hasVisited, lostCity);
                }
            }
            System.out.println(blockNum - 1);
        }

    }


    private static void dfs(List<List<Integer>> graph,
                            int nowCity, boolean[] hasVisited, int lostCity) {
        // 此处判断是否是lostCity, 如果是的话不要遍历, 否则他会错误地认为他们是连通的
        if (nowCity == lostCity) {
            return;
        }

        hasVisited[nowCity] = true;

        for (int i = 1; i <= N; i++) {
            // 没有访问过 && 可以通过该点访问到
            if (!hasVisited[i] && graph.get(i).contains(nowCity)) {
                dfs(graph, i, hasVisited, lostCity);
            }
        }
    }
}

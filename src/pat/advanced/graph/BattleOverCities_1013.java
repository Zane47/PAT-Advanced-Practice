package pat.advanced.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * tell the number of highways need to be repaired, quickly.
 *
 *
 * 给定一个无向图并规定，当删除图中的某个顶点时，将会同时把与之连接的边一起删除。
 * 接下来给出k个查询，每个查询给出一个欲删除的顶点编号，求删除该顶点（和与其连接的边）后
 * 需要增加多少条边，才能使图变为连通（注：k次查询均在原图上进行）。
 *
 *
 *
 */
public class BattleOverCities_1013 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // the total number of cities, <1000
        int N = sc.nextInt();
        // the number of remaining highways,
        int M = sc.nextInt();
        // and the number of cities to be checked,
        int K = sc.nextInt();

        // [1, N]city编号
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(graph[i], -1);
        }

        for (int i = 0; i < M; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            graph[city1][city2] = 1;
            graph[city2][city1] = 1;
        }

        for (int k = 0; k < K; k++) {
            int deadCity = sc.nextInt();




        }

    }
}

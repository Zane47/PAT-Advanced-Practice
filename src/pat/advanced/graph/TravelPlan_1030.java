package pat.advanced.graph;

import java.util.*;

/**
 * 单源最短路径 -> dijkstra
 */
public class TravelPlan_1030 {
    private static final int MAX = 0x3fffffff;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // # of cities
        int N = sc.nextInt();
        // # of highways
        int M = sc.nextInt();
        // start cities
        int S = sc.nextInt();
        // destination cities
        int D = sc.nextInt();

        int[][] distance = new int[N][N];
        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], MAX);
        }
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[i], MAX);
        }

        for (int i = 0; i < M; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int d = sc.nextInt();
            int c = sc.nextInt();
            distance[city1][city2] = d;
            distance[city2][city1] = d;
            cost[city1][city2] = c;
            cost[city2][city1] = c;
        }





    }
}

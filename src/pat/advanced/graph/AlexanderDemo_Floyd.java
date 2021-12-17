package pat.advanced.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 全源最短路径, Floyd, 弗洛伊德算法
 * <p>
 * 算法笔记p398例子
 * <p>
 * <p>
 * // 6个顶点, 8条边, 以下8行为8条边
 * // 边 0 -> 1的边权为1, 有向图
 * 6 8
 * 0 1 1
 * 0 3 4
 * 0 4 4
 * 1 3 2
 * 2 5 1
 * 3 2 2
 * 3 4 3
 * 4 5 3
 * <p>
 * 对Floyd算法来说，需要注意的是：
 * 不能将最外层的k循环放到内层（即产生i->j->k的三重循环），这会导致最后结果出错。
 * 理由是：如果当较后访问的dis[u][v]有了优化之后，
 * 前面访问的dis[i][j]会因为已经被访问而无法获得进一步优化(这里i、j先于u、v进行访问)
 */
public class AlexanderDemo_Floyd {
    // 一个很大的数, 或10^9, 表示顶点之间的不连通
    private static final int INF = 0x3fffffff;

    // __distance[i][j]: 顶点i和顶点j的最短距离
    private static int[][] __dist;

    private static int n;

    public static void main(String[] args) {
        // ------------------------ input, initial ------------------------
        Scanner sc = new Scanner(System.in);
        // 顶点个数
        n = sc.nextInt();
        // 边数
        int m = sc.nextInt();


        // 初始化__distance, 自己到自己都是0
        __dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(__dist[i], INF);
        }

        for (int i = 0; i < n; i++) {
            __dist[i][i] = 0;

        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            // 有向图
            __dist[u][v] = w;
        }

        // ------------------------ do ------------------------

        floydFunc();

        // ------------------------ output ------------------------
        // 输出__distance数组
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(__dist[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * 必须是: k -> i -> j
     */
    private static void floydFunc() {
        // 对Floyd算法来说，需要注意的是：
        // 不能将最外层的k循环放到内层（即产生i->j->k的三重循环），这会导致最后结果出错。
        // 理由是：如果当较后访问的dis[u][v]有了优化之后，
        // 前面访问的dis[i][j]会因为已经被访问而无法获得进一步优化(这里i、j先于u、v进行访问)

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (__dist[i][k] != INF
                            && __dist[i][k] + __dist[k][j] < __dist[i][j]) {
                        // 更短的路径
                        __dist[i][j] = __dist[i][k] + __dist[k][j];
                    }
                }
            }
        }
    }
}

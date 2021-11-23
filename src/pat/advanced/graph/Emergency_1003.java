package pat.advanced.graph;

import java.util.Arrays;
import java.util.Scanner;

/**
 * lead your men to the place as quickly as possible,
 * and at the mean time, call up as many hands on the way as possible.
 * <p>
 * 输出:
 * the number of different shortest paths between C1 and C2
 * , and the maximum amount of rescue teams you can possibly gather
 * <p>
 * <p>
 * 最短路径 + 点权 + 最短路径个数
 */
public class Emergency_1003 {

    private static final int INF = 0x3fffffff;
    // 输入的点权
    private static int[] weight;


    // 是否已经遍历过
    private static boolean[] hasVisited;
    // 从s到点v的最短路径长度, 即最短距离
    private static int[] d;
    // 从s到点v的最短路径个数
    private static int[] num;
    // 从s到点v的最大重量
    private static int[] w;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N (≤500) - # of cities (and the cities are numbered from 0 to N−1),
        int N = sc.nextInt();
        // M - the number of roads,
        int M = sc.nextInt();
        // C1 start
        int C1 = sc.nextInt();
        // C2 destination
        int C2 = sc.nextInt();

        // 输入点权
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            weight[i] = sc.nextInt();
        }

        // 初始化图
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], INF);
        }
        for (int i = 0; i < M; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            graph[c1][c2] = sc.nextInt();
            graph[c2][c1] = graph[c1][c2];
        }

        // 初始化访问数组
        hasVisited = new boolean[N];

        // 初始化num[], num[C1] = 1, 其余为0
        num = new int[N];
        num[C1] = 1;

        // 初始化w[], w[C1] = weight[C1], 其余为0
        w = new int[N];
        w[C1] = weight[C1];

        // 初始化distance
        d = new int[N];
        Arrays.fill(d, INF);
        d[C1] = 0;

        dijkstraFunc(graph, C1, C2, N);

        System.out.print(num[C2]);
        System.out.print(" ");
        System.out.print(w[C2]);
    }

    private static void dijkstraFunc(int[][] graph, int c1, int c2, int n) {
        // 循环n次
        for (int i = 0; i < n; i++) {
            int u = -1;
            int min = INF;

            for (int j = 0; j < n; j++) {
                if (!hasVisited[j] && d[j] < min) {
                    u = j;
                    min = d[j];
                }
            }

            // 如果没有找到, return
            if (u == -1) {
                return;
            }

            hasVisited[u] = true;

            // 从u能到达的v, 做更新
            for (int v = 0; v < n; v++) {
                if (!hasVisited[v] && graph[u][v] != INF) {
                    if (d[u] + graph[u][v] < d[v]) {
                        d[v] = d[u] + graph[u][v];
                        w[v] = w[u] + weight[v];
                        num[v] = num[u];
                    } else if (d[v] == d[u] + graph[u][v]) {
                        // 只要有相等的情况就要叠加num[]
                        // 注意!!!, 最短路径条数和点权没有关系, 必须卸载外面
                        num[v] += num[u];

                        // 如果这个时候点权更大， 那么就可以更新w[v]
                        if (w[u] + weight[v] > w[v]) {
                            w[v] = w[u] + weight[v];
                        }
                    }
                }
            }
        }

    }


}


/*
// 1003Emergency最短路径.cpp : 定义控制台应用程序的入口点。
//


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
#include <algorithm>

using namespace std;
// 无限大
const int INF = 0x3FFFFFFF;
// 最大顶点数
const int MAX_NUM_OF_VERTEX = 510;

int n = 0;
int m = 0;
int startVertex = 0;
int endVertex = 0;

int weight[MAX_NUM_OF_VERTEX] = { 0 };
int G[MAX_NUM_OF_VERTEX][MAX_NUM_OF_VERTEX];

// 记录最短距离
int __d[MAX_NUM_OF_VERTEX];
// 记录最大点权之和
int __w[MAX_NUM_OF_VERTEX];
int numberOfPath[MAX_NUM_OF_VERTEX];
bool isVisited[MAX_NUM_OF_VERTEX] = { false };

void Dijkstra(int s);

int main()
{
	scanf("%d%d%d%d", &n, &m, &startVertex, &endVertex);
	for (int i = 0; i < n; i++) {
		scanf("%d", &weight[i]);
	}


	fill(G[0], G[0] + MAX_NUM_OF_VERTEX * MAX_NUM_OF_VERTEX, INF);

	int u, v = 0;
	for (int i = 0; i < m; i++) {
		scanf("%d%d", &u, &v);
		scanf("%d", &G[u][v]);
		G[v][u] = G[u][v];
	}

	Dijkstra(startVertex);


	printf("%d %d\n", numberOfPath[endVertex], __w[endVertex]);

    return 0;
}



void Dijkstra(int s) {
	// s为起点，其他点到s点的距离初始化都是INF
	fill(__d, __d + MAX_NUM_OF_VERTEX, INF);
	memset(numberOfPath, 0, sizeof(numberOfPath));
	memset(__w, 0, sizeof(__w));
	__d[s] = 0;
	__w[s] = weight[s];
	numberOfPath[s] = 1;
	for (int i = 0; i < n; i++) {
		int u = -1;
		int MIN = INF;

		for (int j = 0; j < n; j++) {
			if (isVisited[j] == false && __d[j] < MIN) {
				u = j;
				MIN = __d[j];
			}
		}

		if (u == -1) {
			return;
		}
		isVisited[u] = true;
		// 更新
		for (int v = 0; v < n; v++) {
			if (isVisited[v] == false && G[u][v] != INF) {
				if (__d[u] + G[u][v] < __d[v]) {
					__d[v] = __d[u] + G[u][v];
					__w[v] = __w[u] + weight[v];
					numberOfPath[v] = numberOfPath[u];
				}
				else if (__d[u] + G[u][v] == __d[v]) {
					if (__w[u] + weight[v] > __w[v]) {
						__w[v] = __w[u] + weight[v];
					}
					numberOfPath[v] += numberOfPath[u];
				}

			}

		}

	}


}














 */

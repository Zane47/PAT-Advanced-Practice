package pat.advanced.graph;

import java.util.*;

/**测试点4, 内存超限
 * calculate the maximum potential amount of forwards for any specific user,
 * assuming that only L levels of indirect followers are counted.
 * <p>
 * 7 3
 * 3 2 3 4
 * 0
 * 2 5 6
 * 2 3 1
 * 2 3 4
 * 1 4
 * 1 5
 * 2 2 6
 * output:
 * 4 (2 -> 1 -> 4 -> 6,5)
 * 5 (6 -> 3 -> 5,4,1 -> 7)
 * <p>
 * 这道题目,
 * dfs: 控制遍历深度不超过题目给定的层数L, 遍历过程中计数访问到的结点个数(细节处理会比较麻烦)
 * 使用DFS遍历很容易出错，因为需要注意一种情况，即可能有一个用户X在第i次被访问，
 * 但是此时已经达到转发层数上限，故无法继续遍历。
 * 但若该用户可以通过另一条路径更快地被访问到，那么是可以继续深入遍历的。
 * 除此之外，DFS还可能导致同一个结点的转发次数被重复计算
 * 需要额外设置一个数组来记录结点是否已经转发过信息，才能最终解决此问题。
 * 本题强烈不推荐使用DFS来写。
 * 具体的例子见< 算法笔记 > P365
 * <p>
 * <p>
 * bfs: 需要把结点编号和层号建立成结构体, 控制遍历层数不超过L
 * <p>
 * 注意点:
 * 1. 编号从1到N
 * 2. M[i] user_list[i], user_list[i]is a list of the M[i] users that followed by user[i]
 * 这里输入的是, 第i个用户的关注人, 也就是说是user_list中人发消息的话, 消息会流入user[i]
 * 所以是有向图
 * 第一行数据: 3 2 3 4, user2,3,4发消息会流入user1, 倒过来的
 * 不用邻接矩阵建图了, 用邻接表
 * 应该倒过来输入, 如果用户X关注了用户Y，则需要建立由Y指向X的有向边，
 * 来表示Y发布的消息可以传递到X并被X转发。
 */
public class ForwardsOnWeibo_1076 {

    // 邻接表 图
    private static List<List<Node>> __graph;

    // N
    private static int N;


    public static void main(String[] args) {
        // ------------------------ input+initial ------------------------
        Scanner sc = new Scanner(System.in);

        // N (<=1000), the number of users, all the users are numbered from 1 to N
        N = sc.nextInt();
        // and L (<=6), the number of levels of indirect followers that are counted
        int L = sc.nextInt();

        // init __graph, 注意[1,N],直接N+1
        __graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            List<Node> node = new ArrayList<>();
            __graph.add(node);
        }

        for (int n = 1; n <= N; n++) {
            int num = sc.nextInt();
            for (int i = 0; i < num; i++) {
                int next = sc.nextInt();
                // 如果用户X关注了用户Y，则需要建立由Y指向X的有向边，
                Node node = new Node();
                node.layer = 0;
                node.id = n;
                __graph.get(next).add(node);
            }
        }


        int K = sc.nextInt();
        for (int k = 0; k < K; k++) {
            // ------------------------ bfs ------------------------
            // 起点
            int start = sc.nextInt();
            //  the maximum potential amount of forwards this user can trigger,
            int forwardNum = bfs(start, L);
            System.out.println(forwardNum);
        }
    }

    /**
     *
     */
    private static int bfs(int start, int L) {
        int forwardNum = 0;

        // 顶点是否加入到队列中
        boolean[] hasInQueue = new boolean[N + 1];

        Queue<Node> queue = new LinkedList<>();
        Node startNode = new Node();
        startNode.layer = 0;
        startNode.id = start;
        queue.add(startNode);
        hasInQueue[start] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            List<Node> nextNodes = __graph.get(node.id);
            for (Node nextNode : nextNodes) {
                nextNode.layer = node.layer + 1;
                // 没有入队过 && layer不大于L
                if (!hasInQueue[nextNode.id] && nextNode.layer <= L) {
                    queue.add(nextNode);
                    hasInQueue[nextNode.id] = true;
                    forwardNum++;
                }

            }

        }


        return forwardNum;
    }

    static class Node {
        // 编号
        int id;
        // 层数
        int layer;
    }

}

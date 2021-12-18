package pat.advanced.tree.union;


import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 和找朋友类似的问题
 * 每个人有多个爱好, 求爱好的集合
 * <p>
 * hobbyFav[h]: 喜欢爱好h的任意一个人的编号
 * findFather(hobbyFav[h])找到这个人的社交网络的根节点
 * <p>
 * 对于每次读入的数据, hobbyFav[h]如果是被人第一次喜欢, 那么就赋值,
 * 如果不是第一次被人喜欢, 做合并
 */
public class SocialClusters_1107 {

    private static int N;

    // 并查集
    private static int[] __father;

    // 以某爱好为根节点的人数, 如果不是根节点, 设置为0
    private static Integer[] __root;

    // 喜欢爱好i的任意一个人的编号
    private static int[] __hobbyFav;


    public static void main(String[] args) {
        // ------------------------ input, initial, do ------------------------
        Scanner sc = new Scanner(System.in);
        // total # of people in a social network, <=1k
        // [1, N]
        N = sc.nextInt();

        __father = new int[N + 1];

        init();

        // [1, 1000]的hobby编号, __hobbyFav[h]: 喜欢第h号hobby的任意一个人的编号
        __hobbyFav = new int[1001];

        // gives the hobby list of a person
        for (int i = 0; i < N; i++) {
            int numOfHobbies = sc.next().charAt(0) - '0';
            for (int j = 0; j < numOfHobbies; j++) {
                // 如果这个爱好是第一次出现, 那么就把
                int hobby = sc.nextInt();
                // 第一次被人喜欢
                if (__hobbyFav[hobby] == 0) {
                    __hobbyFav[hobby] = i;
                }
                // 合并
                union(i, findRoot(__hobbyFav[hobby]));
            }
        }

        // 是否是根节点
        __root = new Integer[N + 1];
        Arrays.fill(__root, 0);
        for (int i = 1; i <= N; i++) {
            __root[findRoot(i)]++;
        }

        // ------------------------ output ------------------------

        // print in one line the total # of clusters in the network.
        //
        // Then in the second line,
        // print the # of people in the clusters in non-increasing order.
        int result = 0;
        for (int i = 1; i <= N; i++) {
            if (__root[i] > 0) {
                result++;
            }
        }
        System.out.println(result);

        Arrays.sort(__root, Collections.reverseOrder());

        for (int i = 1; i <= result; i++) {
            System.out.print(__root[i]);
            if (i != result - 1) {
                System.out.print(" ");
            }
        }

    }

    /**
     * 初始化并查集, 每个人的father都是他自己
     */
    private static void init() {
        for (int i = 1; i <= N; i++) {
            __father[i] = i;
        }
    }

    /**
     * 合并
     */
    private static void union(int v1, int v2) {
        int rootV1 = findRoot(v1);
        int rootV2 = findRoot(v2);
        if (rootV1 != rootV2) {
            __father[rootV1] = rootV2;
        }
    }

    /**
     * 找元素的根节点
     */
    private static int findRoot(int x) {
        int v1 = x;

        while (x != __father[x]) {
            x = __father[x];
        }

        // 路径压缩
        while (v1 != __father[v1]) {
            int temp = v1;
            v1 = __father[v1];
            __father[temp] = x;

        }

        return x;
    }
}

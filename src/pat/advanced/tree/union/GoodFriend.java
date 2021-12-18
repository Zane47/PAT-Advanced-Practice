package pat.advanced.tree.union;

import java.util.Scanner;

/**
 * 有一个叫作“数码世界”的奇异空间，在数码世界里生活着许许多多的数码宝贝，其中有些数码宝贝之间可能是好朋友。
 * 并且数码世界有两条不成文的规定：
 * 第一，数码宝贝A和数码宝贝B是好朋友等价于数码宝贝B和数码宝贝A是好朋友。
 * 第二，如果数码宝贝A和数码宝贝C是好朋友，而数码宝贝B和数码宝贝C也是好朋友，那么A和B也是好朋友。
 * <p>
 * 现在给出这些数码宝贝中所有好朋友的信息，问：可以把这些数码宝贝分成多少组，
 * 满足每组中的任意两只数码宝贝都是好朋友，且任意两组之间的数码宝贝都不是好朋友。
 * <p>
 * <p>
 * 输入格式:
 * 输入的第一行有两个正整数n (n<=100)和m (m<=100), 分别表示数码宝贝的个数和好朋友的组数，
 * 其中数码宝贝编号为1~n。
 * 接下来有m行，每行两个正整数a和b，表示数码宝贝a和数码宝贝b是好朋友。
 * <p>
 * 输出格式:
 * 输出一个整数，表示这些数码宝贝可以分成的组数。
 * <p>
 * input:
 * 4 2
 * 1 4
 * 2 3
 * output2
 * 2
 * <p>
 * input:
 * 7 5
 * 1 2
 * 2 3
 * 3 1
 * 1 4
 * 5 6
 * output:
 * 3
 * <p>
 * 画图后可知, 就是求并查集集合个数
 * <p>
 * 用数组flag来记录结点是否是根节点, 根节点设置为true, 最后遍历查看true的个数
 */
public class GoodFriend {

    private static int[] __father;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 数码宝贝的个数, 编号为1~n
        int n = sc.nextInt();
        // 好朋友的组数
        int m = sc.nextInt();

        __father = new int[n + 1];
        initUnion(n);

        for (int i = 0; i < m; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            union(v1, v2);
        }

        // 默认都是false
        boolean[] rootFlag = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            rootFlag[findRootFather(i)] = true;
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (rootFlag[i]) {
                result++;
            }
        }
        System.out.println(result);
    }

    /**
     * 初始化并查集
     * 每一个都初始化成自身
     *
     * @param n
     */
    private static void initUnion(int n) {

        for (int i = 1; i <= n; i++) {
            __father[i] = i;
        }
    }


    /**
     * 返回元素x所在集合的根节点
     *
     * @param x
     * @return
     */
    private static int findRootFather(int x) {
        // 保留初始x的值
        int v1 = x;
        while (__father[x] != x) {
            x = __father[x];
        }
        // 此时x是根节点

        // 路径压缩
        while (__father[v1] != v1) {
            int z = v1;
            v1 = __father[v1];
            __father[z] = x;
        }

        return x;
    }

    /**
     * 合并两个元素所在的集合
     */
    private static void union(int v1, int v2) {
        // 分别找到两个元素所在的根节点
        int fatherV1 = findRootFather(v1);
        int fatherV2 = findRootFather(v2);
        // 不在同一个集合中
        if (fatherV2 != fatherV1) {
            __father[fatherV1] = v2;
        }
    }

}

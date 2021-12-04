package pat.advanced.simulation;

import java.util.Arrays;
import java.util.Scanner;

/**
 * N >=5的字母
 * 保持原序
 * <p>
 * n1: 左上到左下, 左边的一竖
 * n2: 左下到右下, 下边的一横
 * n3: 左下到右上, 右边的一竖
 * <p>
 * 要这个U越squared越好, 即
 * n1 = n3 = max{k|k<=n2 for all 3 <= n2 <= N}, 同时n1 + n2 + n3 - 2 = N
 * -> n1 = n3 = k(k<=n2), k取max理解成对n1和n3取max
 * -> U形图片的两侧长度n1=n3不超过底部的n2, 在这个条件下, 尽可能让n1和n3最大
 * <p>
 * 自己画一下N从6到10
 * (1)这里N最大是80, 数据量不大, 可以用递推, 每一次都先加到底部, 然后看能否转移到两侧
 * (2)可以看的出来, 其实n1 = n3 = (N+2)/3, 向下取整的, 然后根据三者和求n2即可
 */
public class HelloWorld4U_1031 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // str contains no white space
        String[] strs = sc.next().split("");
        // [5, 80]
        int N = strs.length;
        // N = 5的初始化数值
        int n1 = 2, n2 = 3, n3 = 2;
        // 从6开始递推, 先加到底部去, 然后看能不能加到两侧去
        for (int i = 6; i <= N; i++) {
            // 先加到底部
            n2 = n2 + 1;
            // 看如果从底部转移到两侧还满足条件的话, 则转移
            if (check(n1, n2, n3, i)) {
                n1 = n1 + 1;
                n3 = n3 + 1;
                n2 = n2 - 2;
            }
        }

        // 做输出
        // 二维数组输出
        String[][] strings = new String[n1][n2];

        for (int i = 0; i < n1; i++) {
            Arrays.fill(strings[i], " ");
        }


        int v1 = 0;
        // 第一列
        for (int i = 0; i < n1; i++) {
            strings[i][0] = strs[v1++];
        }
        // 最后一行
        for (int i = 1; i < n2; i++) {
            strings[n1 - 1][i] = strs[v1++];
        }
        // 最后一列, 从倒数第二行开始
        for (int i = n3 - 2; i >= 0; i--) {
            strings[i][n2 - 1] = strs[v1++];
        }

        // print
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                System.out.print(strings[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * 从底部转移到上面
     */
    private static boolean check(int n1, int n2, int n3, int i) {
        n2 = n2 - 2;
        n1 = n1 + 1;
        n3 = n3 + 1;
        return n3 == n1 && n2 >= n1 && n1 + n2 + n3 - 2 == i;
    }
}

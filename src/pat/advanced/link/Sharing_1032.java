package pat.advanced.link;

import java.util.Scanner;

/**测试点5运行超时
 * find the starting position of the common suffix
 * 相同后缀的开始位置
 * <p>
 * 1. 用静态链表表示方法
 * 2. 新增flag表示是否是第一个链表, 遍历第一个链表的时候, 赋值
 * 然后遍历第二个链表, 找到第一个flag为1的点即可
 */
public class Sharing_1032 {

    public static void main(String[] args) {
        // ------------------------ input+initial ------------------------
        Scanner sc = new Scanner(System.in);
        // the address of the first nodes of the 1st words
        int start1 = sc.nextInt();
        // the address of the first nodes of the 2nd words
        int start2 = sc.nextInt();

        // the total # of nodes <= 10^5
        int N = sc.nextInt();

        Node[] nodes = new Node[100010];

        for (int i = 0; i < 100010; i++) {
            Node node = new Node();
            nodes[i] = node;
            nodes[i].flag = 0;
        }

        for (int i = 0; i < N; i++) {
            int address = sc.nextInt();
            nodes[address].data = sc.next();
            nodes[address].next = sc.nextInt();
        }

        // ------------------------ flag表示是否第一个链表, 然后第二个link中遍历第一个flag为1的点 ------------------------
        // 1. 遍历第一条链表, 赋值flag为1
        int cur = start1;
        while (cur != -1) {
            nodes[cur].flag = 1;
            cur = nodes[cur].next;
        }

        // 2. 遍历第二条链表, 找第一个flag为1的
        int result = -1;
        cur = start2;
        while (cur != -1) {
            if (nodes[cur].flag == 1) {
                result = cur;
                break;
            } else {
                cur = nodes[cur].next;
            }
        }

        if (result != -1) {
            System.out.printf("%05d\n", result);
        } else {
            System.out.println(-1);
        }

    }

    static class Node {
        String data;
        int next;
        // 是否是第一条链表(链表编号)
        int flag;
    }
}

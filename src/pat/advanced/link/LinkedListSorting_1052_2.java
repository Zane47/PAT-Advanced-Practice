package pat.advanced.link;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**测试点3超时
 * 使用< 算法笔记 > 中的模板做法
 * sort the structures according to their key values in increasing order.
 * <p>
 * <p>
 * 注意点:
 * 1. head的作用: 可能有无效结点, 也就是不在link上的点, output的时候不能直接输出N, 要计算count
 * 2. 均为无效结点 -> 0 -1
 * < 算法上机实训 >P272
 * 步骤:
 * 直接套用配套用书讲解的一般解题步骤。
 * 步骤1：定义静态链表。其中结点性质由bool型变量flag定义，用以表示为结点在链表中是否出现。
 * 若当flag为false，则表示无效结点（不在链表上的结点）。
 * 步骤2：进行初始化。令flag均为false（即0），表示初始状态下所有结点都是无效结点。
 * 步骤3：由题目给出的链表首地址begin遍历整条链表，并标记有效结点的flag为true，同时计数有效结点的个数count。
 * <p>
 * 步骤4：对结点进行排序。排序函数cmp的排序原则是：如果cmp的两个参数结点中有无效结点，
 * 则按flag从大到小排序，以把有效结点排到数组左端
 * （因为有效结点的flag为1，大于无效结点的flag）；
 * 否则，按数据域从小到大排序。
 * <p>
 * 步骤5：由于有效结点已经按照数据域从小到大排序，因此按要求输出有效结点即可。
 */
public class LinkedListSorting_1052_2 {
    public static void main(String[] args) {
        // ------------------------ input, initial ------------------------
        Scanner sc = new Scanner(System.in);
        // <= 10^5, the total # of nodes in memory
        int N = sc.nextInt();
        // an address of the head node
        int head = sc.nextInt();

        // 初始化nodes
        Node[] nodes = new Node[100010];
        for (int i = 0; i < 100010; i++) {
            Node node = new Node();
            nodes[i] = node;
            // 全都初始化成无效结点
            nodes[i].flag = 0;
            nodes[i].key = Integer.MIN_VALUE;
        }


        for (int i = 0; i < N; i++) {
            int address = sc.nextInt();
            nodes[address].address = address;
            nodes[address].key = sc.nextInt();
            nodes[address].next = sc.nextInt();
        }

        // ------------------------ do ------------------------
        // 有效结点个数
        int count = 0;
        int cur = head;
        while (cur != -1) {
            // link中的成员
            nodes[cur].flag = 1;
            count++;
            cur = nodes[cur].next;
        }

        // 特例
        if (count == 0) {
            System.out.println("0 -1");
            return;
        }

        // 筛选+排序
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                // 有一个是无效结点就放到后面去
                if (o1.flag == 0 || o2.flag == 0) {
                    return o2.flag - o1.flag;
                } else {
                    // 都是有效结点, 按照key排序
                    return o1.key - o2.key;
                }
            }
        });


        // ------------------------ output ------------------------
        System.out.printf("%d %05d\n", count, nodes[0].address);

        for (int i = 0; i < count; i++) {
            if (i != count - 1) {
                System.out.printf("%05d %d %05d\n", nodes[i].address, nodes[i].key, nodes[i+1].address);
            } else {
                System.out.printf("%05d %d -1\n", nodes[i].address, nodes[i].key);
            }
        }

    }

    static class Node {
        int address;
        int key;
        int next;
        // 是否在link中的标志
        int flag;
    }
}

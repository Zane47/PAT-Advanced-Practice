package pat.advanced.link;

import java.util.*;

/**测试点34 超时
 * remove the nodes with duplicated absolute values of the keys
 * 绝对值一致
 * 00100 5
 * 99999 -7 87654
 * 23854 -15 00000
 * 87654 15 -1
 * 00000 -15 99999
 * 00100 21 23854
 * ->
 * 00100 21 23854
 * 23854 -15 99999
 * 99999 -7 -1
 * 00000 -15 87654
 * 87654 15 -1
 * <p>
 * < 算法笔记实训 >步骤: P275
 * 步骤1：定义静态链表。其中结点性质由int型变量order定义，用以表示结点在链表上的序号。
 * 由于最后需要先输出所有未删除的结点，然后输出所有被删除的结点，因此不妨在
 * 后面的步骤中令未删除的结点的order从0开始编号，被删除的结点的order从maxn开始编号。
 * <p>
 * 步骤2：初始化。令order的初值均为2maxn，这样无效结点就会被区分开来。
 * <p>
 * 步骤3：设置变量countValid（初始化为0），用来记录未删除的有效结点的个数；
 * 设置countRemoved（初始化为0），用来记录被删除的有效结点的个数。
 * 由题目给出的链表首地址begin遍历整条链表，如果当前访问结点的权值的绝对值还未出现过
 * （可以开一个全局的bool数组isExist来记录），那么就把该结点的order设为countValid,然
 * 后令countValid加1；如果当前访问结点的权值的绝对值已经出现过，
 * 那么就把结点的order设为maxn+countRemoved,然后令countRemoved加1。
 * 这样未删除的结点的order就从0开始编号，而被删除的结点就从maxn开始编号。
 * <p>
 * 步骤4：对结点进行排序，排序函数cmp的排序原则是：直接按照结点的order从小到大排序。
 * 由于未删除的结点的order从0开始编号，被删除的结点从maxr开始编号，
 * 而无效结点的order为初始的2maxn，
 * 因此结点的顺序就是按未删除的结点、已删除的结点、无效结点进行排列。
 * <p>
 * 步骤5：输出链表。记count为count Valid与countRemoved之和，之后将node[0]~
 * node[count-1]输出。注意：最后一个未删除结点和最后一个被删除结点单独处理。
 */
public class DeduplicationOnALinkedList_1097_2 {

    private static final int MAXN = 100010;

    public static void main(String[] args) {
        // ------------------------ input, initial ------------------------
        Scanner sc = new Scanner(System.in);
        // address5位非负数
        //  the address of the first node
        int first = sc.nextInt();
        // the total # of nodes, <= 10^5
        int N = sc.nextInt();

        Node[] nodes = new Node[MAXN];
        for (int i = 0; i < MAXN; i++) {
            nodes[i] = new Node();
            nodes[i].order = 2 * MAXN;
        }

        for (int i = 0; i < N; i++) {
            int address = sc.nextInt();
            nodes[address].address = address;
            nodes[address].key = sc.nextInt();
            nodes[address].next = sc.nextInt();
        }


        // ------------------------ do ------------------------
        // 绝对值是否已经出现
        boolean[] hasExisted = new boolean[MAXN];

        // 有效的结点个数
        int resultCount = 0;
        // removed的结点个数
        int removedCount = 0;

        int cur = first;
        while (cur != -1) {
            // 还没有出现过
            if (!hasExisted[Math.abs(nodes[cur].key)]) {
                // 标记已存在
                hasExisted[Math.abs(nodes[cur].key)] = true;

                // 从0开始标号
                nodes[cur].order = resultCount++;
            } else {
                // 已存在了, 从maxn开始标号
                nodes[cur].order = MAXN + removedCount++;
            }
            cur = nodes[cur].next;
        }


        // 按照order从小到大排序,
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.order - o2.order;
            }
        });

        // 排序后nodes中的结点为
        // [0, resultCount) 有效的
        // [resultCount, removedCount) 无效的
        int count = resultCount + removedCount;


        // ------------------------ ouput ------------------------
        // output the resulting linked list first, then the removed list.
        // Each node occupies a line, and is printed in the same format as in the input.
        for (int i = 0; i < count; i++) {
            // 最后一个结点, -1
            if (i == resultCount - 1 || i == count - 1) {
                System.out.printf("%05d %d -1\n", nodes[i].address, nodes[i].key);
            } else {
                System.out.printf("%05d %d %05d\n", nodes[i].address, nodes[i].key,
                        nodes[i + 1].address);
            }
        }


    }


    static class Node {
        int address;
        //  an integer of which absolute value is no more than 10^4
        int key;
        int next;
        // 新增一个属性, 结点在link上的序号
        // result中的结点从0开始编号, removed中的结点从maxn开始编号
        int order;
    }

}

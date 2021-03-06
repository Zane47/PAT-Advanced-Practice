package pat.advanced.link;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 测试点3超时
 * https://www.nowcoder.com/pat/5/problem/4091
 * <p>
 * sort the structures according to their key values in increasing order.
 * <p>
 * <p>
 * 注意点:
 * 1. head的作用: 可能有无效结点, 也就是不在link上的点, output的时候不能直接输出N, 要计算count
 * 2. 特殊处理: 均为无效结点 -> 0 -1
 */
public class LinkedListSorting_1052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // <=10^5, the total # of nodes in memory
        int N = sc.nextInt();
        // an address of the head node
        int head = sc.nextInt();

        Node[] nodes = new Node[100010];
        for (int i = 0; i < 100010; i++) {
            Node node = new Node();
            nodes[i] = node;
            nodes[i].key = Integer.MIN_VALUE;
        }

        for (int i = 0; i < N; i++) {
            int address = sc.nextInt();
            nodes[address].key = sc.nextInt();
            nodes[address].next = sc.nextInt();
        }

        // ------------------------ do ------------------------
        // head的作用: 用来排除不在link的结点, 正常的点放到list中
        List<Node> validList = new ArrayList<>();
        int cur = head;
        while (cur != -1 && nodes[cur].key != Integer.MIN_VALUE) {
            Node node = new Node();
            node.address = cur;
            node.key = nodes[cur].key;
            validList.add(node);
            cur = nodes[cur].next;
        }

        if (validList.size() == 0) {
            System.out.println("0 -1");
            return;
        }

        // 按照key的大小排序
        validList.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.key - o2.key;
            }
        });


        // ------------------------ output ------------------------
        System.out.printf("%d %05d\n", validList.size(), validList.get(0).address);

        // 赋值next
        for (int i = 0; i < validList.size() - 1; i++) {
            System.out.printf("%05d ", validList.get(i).address);
            System.out.printf("%d ", validList.get(i).key);
            System.out.printf("%05d\n", validList.get(i + 1).address);
        }

        // 打印最后一条数据
        System.out.printf("%05d %d %d",
                validList.get(validList.size() - 1).address,
                validList.get(validList.size() - 1).key,
                -1);

    }

    static class Node {
        int address;
        int key;
        int next;
    }
}

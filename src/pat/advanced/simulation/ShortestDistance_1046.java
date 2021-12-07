package pat.advanced.simulation;


import java.util.Arrays;
import java.util.Scanner;

/**
 * 简单的环形, pair的距离, 两种可能, 顺时针和逆时针, 计算即可
 * 数组, 双向链表
 * <p>
 * It is guaranteed that the total round trip distance is no more than 10 ^ 7
 *
 * 所有结点连起来会形成一个环形，
 * dis[i]存储第1个结点到第i个结点的下一个结点的距离，
 * sum保存整个路径一圈的总和值。
 * 求得结果就是dis[right – 1] – dis[left – 1]
 * 和 sum – dis[right – 1] – dis[left – 1]中较小的那一个
 * 注意：可能left和right的顺序颠倒了，这时候要把left和right的值交换
 */
public class ShortestDistance_1046 {
    public static void main(String[] args) {
        // 双向链表, 测试点2超时 -> 贪心: https://www.jianshu.com/p/cb54521fda65
        // solution1();

        // solution2 liu chuo
        // 还是超时, 测试点0错误
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        // dis[i]存储第1个结点到第i个结点的下一个结点的距离
        int[] dist = new int[N];
        // 整个路径一圈路径和
        int sum = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            sum += nums[i];
            dist[i] = sum;
        }

        int M = sc.nextInt();
        for (int m = 0; m < M; m++) {
            int left = sc.nextInt();
            int right = sc.nextInt();

            if (left > right) {
                int temp = right;
                right = left;
                left = temp;
            }

            int temp = dist[right - 1] - dist[left - 1];
            System.out.println(Math.min(temp, sum - temp));
        }

    }

    /**
     * 双向链表
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            sum += nums[i];
        }


        // head, null, 这里设置成0的值
        Node head = new Node(0);
        Node pre = head;
        // 初始化双向链表
        for (int i = 0; i < nums.length; i++) {
            Node node = new Node(nums[i]);
            node.next = null;
            node.prev = pre;
            pre.next = node;
            pre = node;
            if (i == nums.length - 1) {
                node.next = head;
                head.prev = node;
            }
        }

        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int node1 = sc.nextInt();
            int node2 = sc.nextInt();
            Node cur1 = head;
            int tempResult1 = 0;
            while (cur1.value != nums[node1-1]) {
                cur1 = cur1.next;
            }
            do {
                tempResult1 += cur1.value;
                cur1 = cur1.next;
            } while (cur1.value != nums[node2-1]);

            /*
            Node cur2 = head;
            int tempResult2 = 0;
            while (cur2.value != nums[node2-1]) {
                cur2 = cur2.prev;
            }
            do {
                tempResult2 += cur2.value;
                cur2 = cur2.next;
            } while (cur2.value != nums[node1-1]);
            */
            System.out.println(Math.min(tempResult1, sum - tempResult1));

        }
    }


    static class Node {
        int value;
        Node prev;
        Node next;

        Node(int val) {
            this.value = val;
        }

        Node(int val, Node prev, Node next) {
            this.value = val;
            this.prev = prev;
            this.next = next;
        }

    }
}

package pat.advanced.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
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
 */
public class DeduplicationOnALinkedList_1097 {

    public static void main(String[] args) {
        // ------------------------ input, initial ------------------------
        Scanner sc = new Scanner(System.in);
        // address5位非负数
        //  the address of the first node
        int first = sc.nextInt();
        // the total # of nodes, <= 10^5
        int N = sc.nextInt();

        Node[] nodes = new Node[100010];
        for (int i = 0; i < 100010; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < N; i++) {
            int address = sc.nextInt();
            nodes[address].address = address;
            nodes[address].key = sc.nextInt();
            nodes[address].next = sc.nextInt();
        }

        // key is an integer of which absolute value is no more than 10
        // hashTable的方式记录, 如果abs>1的话就说明重复了
        int[] hashTable = new int[10010];

        // ------------------------ do ------------------------
        // 首先遍历一遍, 然后记录每一个key的出现次数

        // todo: 有没有可能全都是不连续的数据

        // 个数
        int count = 0;
        int cur = first;
        while (cur != -1) {
            hashTable[Math.abs(nodes[cur].key)]++;
            count++;
            cur = nodes[cur].next;
        }

        if (count == 0) {
            return;
        }

        // 再次遍历
        List<Node> resultList = new ArrayList<>();
        List<Node> removedList = new ArrayList<>();

        // 记录是否是第一次遍历到该数字的绝对值
        boolean[] nums = new boolean[10010];

        // 记录removedList中的key abs, 是否存在removedList中
        boolean[] removedNum = new boolean[10010];

        cur = first;
        while (cur != -1) {
            Node node = nodes[cur];

            // 序列中key仅出现了一次
            if (hashTable[Math.abs(node.key)] == 1) {
                resultList.add(node);
            } else {
                // 整个序列中不止一个key
                // 如果是第一次出现, 就加入resultList
                if (!nums[Math.abs(node.key)]) {
                    resultList.add(node);
                    nums[Math.abs(node.key)] = true;
                } else {
                    // 如果不是第一次出现, 判断是否要加入removedList
                    if (!removedNum[Math.abs(node.key)]) {
                        removedList.add(node);
                        removedNum[Math.abs(node.key)] = true;
                    }
                }
            }
            // 下一个
            cur = nodes[cur].next;
        }

        int a = 0;


        // ------------------------ ouput ------------------------
        // output the resulting linked list first, then the removed list.
        // Each node occupies a line, and is printed in the same format as in the input.


    }


    static class Node {
        int address;
        //  an integer of which absolute value is no more than 10^4
        int key;
        int next;
    }

}

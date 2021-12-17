package pat.advanced.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 测试点3, 4超时
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

        // 个数
        int count = 0;
        int cur = first;
        while (cur != -1) {
            hashTable[Math.abs(nodes[cur].key)]++;
            count++;
            cur = nodes[cur].next;
        }

        // 无效结点处理, head就不在link上
        if (count == 0) {
            return;
        }

        // 再次遍历
        List<Node> resultList = new ArrayList<>();
        List<Node> removedList = new ArrayList<>();

        // 记录是否是第一次遍历到该数字的绝对值
        boolean[] nums = new boolean[10010];

        // 记录removedList中的key, 是否存在removedList中
        List<Integer> removedNumList = new ArrayList<>();

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
                    // 如果不是第一次出现, 直接加入removedList
                    removedList.add(node);
                }
            }
            // 下一个
            cur = nodes[cur].next;
        }



        // ------------------------ ouput ------------------------
        // output the resulting linked list first, then the removed list.
        // Each node occupies a line, and is printed in the same format as in the input.

        for (int i = 0; i < resultList.size(); i++) {
            if (i != resultList.size() - 1) {
                System.out.printf("%05d %d %05d\n",
                        resultList.get(i).address, resultList.get(i).key,
                        resultList.get(i + 1).address);
            } else {
                System.out.printf("%05d %d -1\n", resultList.get(i).address, resultList.get(i).key);
            }
        }

        for (int i = 0; i < removedList.size(); i++) {
            if (i != removedList.size() - 1) {
                System.out.printf("%05d %d %05d\n",
                        removedList.get(i).address, removedList.get(i).key,
                        removedList.get(i + 1).address);
            } else {
                System.out.printf("%05d %d -1\n", removedList.get(i).address, removedList.get(i).key);
            }
        }
    }


    static class Node {
        int address;
        //  an integer of which absolute value is no more than 10^4
        int key;
        int next;
    }

}

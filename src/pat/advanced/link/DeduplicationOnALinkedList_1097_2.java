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
public class DeduplicationOnALinkedList_1097_2 {

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


        // ------------------------ do ------------------------
        // 绝对值是否已经出现
        boolean[] hasShow = new boolean[100010];




        List<Node> resultList = new ArrayList<>();
        List<Node> removedList = new ArrayList<>();

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

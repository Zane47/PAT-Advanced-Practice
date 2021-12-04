package pat.advanced.stl;


import java.util.Scanner;
import java.util.Stack;

/**
 * a stack which can keep M numbers at most.
 * Push N numbers in the order of 1, 2, 3, ..., N and pop randomly.
 * <p>
 * pop的序列是否可能存在
 * <p>
 * 一个一个读取,
 * <p>
 * 5 7 5
 * 1 2 3 4 5 6 7
 * 3 2 1 7 5 6 4
 * 7 6 5 4 3 2 1
 * 5 6 4 3 7 2 1
 * 1 7 6 5 4 3 2
 * ac
 * todo: 但是有更好的写法 -> 算法笔记上机实战中
 */
public class PopSequence_1051 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 三个输入数字都<=1000
        // the maximum capacity of the stack
        int M = sc.nextInt();
        // the length of push sequence
        int N = sc.nextInt();
        // the number of pop sequences to be checked
        int K = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        // K个序列
        for (int k = 0; k < K; k++) {
            // 清空栈
            stack.clear();
            // yesorno
            boolean suc = true;
            // 元素是否被访问
            boolean[] hasVisited = new boolean[N + 1];
            int[] input = new int[N];
            // 读取输入的数字
            // 先把所有的都读了, 不然下面的break会导致有些数字没读到
            for (int i = 0; i < N; i++) {
                input[i] = sc.nextInt();
            }
            for (int i = 0; i < N; i++) {
                int num = input[i];
                // 从1到num的数字入栈,
                for (int j = 1; j <= num; j++) {
                    // 如果这个数字没有如果栈
                    if (stack.size() > M) {
                        suc = false;
                        break;
                    } else if (!hasVisited[j]) {
                        stack.push(j);
                        if (stack.size() > M) {
                            suc = false;
                            break;
                        }
                        hasVisited[j] = true;

                    }
                }
                if (!suc) {
                    System.out.println("NO");
                    break;
                }
                if (!stack.empty() && num != stack.pop()) {
                    suc = false;
                    break;
                }
            }

            if (!suc) {
                if (stack.size() < M) {
                    System.out.println("NO");
                }
            } else {
                System.out.println("YES");
            }
        }
    }


}

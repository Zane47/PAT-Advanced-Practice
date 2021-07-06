package pat.advanced;

import java.util.Scanner;

/**
 *
 *
 * The elevator is on the 0th floor at the beginning and
 * does not have to return to the ground floor when the requests are fulfilled.
 *
 *
 * 3 2 3 1
 * 41
 */

public class Elevator_1008 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int result = 0;
        int N = sc.nextInt();
        // 初始在第0层
        int[] array = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            array[i] = sc.nextInt();
        }


        for (int i = 0; i < N; i++) {
            int offset = array[i + 1] - array[i];
            if (offset > 0) {
                result += 6 * (offset);
            }
            else if (offset < 0) {
                result += 4 * (-offset);
            }
        }


        // 每一层的停止时间
        result += 5 * N;




        System.out.print(result);

    }


}

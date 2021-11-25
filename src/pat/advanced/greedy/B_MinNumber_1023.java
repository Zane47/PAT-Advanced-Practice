package pat.advanced.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1023 组个最小数
 *
 *
 *
 *
 */
public class B_MinNumber_1023 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        // 首位不能是0
        for (int i = 1; i < 10; i++) {
            if (nums[i] != 0) {
                System.out.print(i);
                nums[i]--;
                break;
            }
        }

        // 第二位开始
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < nums[i]; j++) {
                System.out.print(i);
            }
        }

    }
}

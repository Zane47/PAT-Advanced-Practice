package pat.advanced.simulation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * S3, H5, C1, D13 J2
 * -> 4, 2, 5, 3, 1
 * J2, H5, D13, S3, C1
 * -> 4, 2, 5, 3, 1
 * C1, H5, S3, J2, D13
 *
 * input:
 * 2
 * 36 52 37 38 3 39 40 53 54 41 11 12 13 42 43 44 2 4 23 24 25 26 27 6 7 8 48 49 50 51 9 10 14 15 16 5 17 18 19 1 20 21 22 28 29 30 31 32 33 34 35 45 46 47
 * output
 * S7 C11 C10 C12 S1 H7 H8 H9 D8 D9 S11 S12 S13 D10 D11 D12 S3 S4 S6 S10 H1 H2 C13 D2 D3 D4 H6 H3 D13 J1 J2 C1 C2 C3 C4 D1 S5 H5 H11 H12 C6 C7 C8 C9 S2 S8 S9 H10 D5 D6 D7 H4 H13 C5
 *
 */
public class ShufflingMachine_1042 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[] color = new char[] {'S', 'H', 'C', 'D'};
        // 初始化arraylist, 扑克牌的顺序
        List<String> list = new ArrayList<>();
        // 1-54第一位为空
        list.add("");
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(color[i]);
                sb.append(j);
                list.add(sb.toString());
            }
        }
        list.add("J1");
        list.add("J2");


        int K = sc.nextInt();
        int[] nums = new int[55];
        for (int i = 1; i <= 54; i++) {
            nums[i] = sc.nextInt();
        }

        // 使用start和end数组保存每一次变换的开始顺序和结束顺序（以1~54的编号存储）
        // 最后根据编号与扑克牌字母数字的对应关系输出end数组
        int[] start = new int[55];
        int[] end = new int[55];

        // 初始化, 1-54
        for (int i = 1; i <= 54; i++) {
            end[i] = i;
        }

        // 洗牌次数
        for (int k = 0; k < K; k++) {
            for (int i = 1; i <= 54; i++) {
                start[i] = end[i];
            }
            // 开始洗牌
            for (int i = 1; i <= 54; i++) {
                end[nums[i]] = start[i];
            }
        }

        for (int i = 1; i <= 54 ;i++) {
            if (i == 54) {
                System.out.print(list.get(end[i]));
            } else {
                System.out.print(list.get(end[i]) + " ");
            }
        }
    }

}

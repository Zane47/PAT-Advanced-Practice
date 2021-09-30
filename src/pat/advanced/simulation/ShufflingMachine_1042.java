package pat.advanced.simulation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShufflingMachine_1042 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[] color = new char[] {'S', 'H', 'C', 'D'};
        // 初始化arraylist
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
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
        for (int i = 1; i < 55; i++) {
            nums[i] = sc.nextInt();
        }

        // 使用start和end数组保存每一次变换的开始顺序和结束顺序（以1~54的编号存储）=
        int[] start = Arrays.copyOf(nums, 55);
        int[] end = Arrays.copyOf(nums, 55);

        // 洗牌次数
        for (int k = 0; k < K; k++) {
            // 开始洗牌
            for (int i = 1; i < 55; i++) {
                end[] = nums[];
            }



        }

        for (int i = 0; i < 54 ;i++) {
            if (i == 53) {
                System.out.print(list.get(i));
            } else {
                System.out.print(list.get(i) + " ");
            }
        }








    }





}

package pat.advanced;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 固定是3*2 6 位
 *
 * 所以这边直接一个/ 一个%即可
 * 14 168 144
 *
 */

public class ColorsInMars_1027 {
    public static void main(String[] args) throws IOException {
        String[] marRGB = new String[] {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C"
        };
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] strArray = input.split(" ");
        System.out.print("#");
        for (int i = 0; i < 3; i++) {
            // 但是这个方法有问题
            // 遇到0会忽略掉
            // 131 7 76 -> #A1075B
            // #A175B
            // System.out.print(Integer.toString(Integer.parseInt(strArray[i]), 13).toUpperCase());

            System.out.print(marRGB[Integer.parseInt(strArray[i]) / 13]);
            System.out.print(marRGB[Integer.parseInt(strArray[i]) % 13]);
        }

    }





}

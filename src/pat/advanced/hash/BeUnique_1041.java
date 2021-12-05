package pat.advanced.hash;

import sun.awt.image.BufferedImageDevice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

/**
 * one bets on a number chosen from [1,10^4]
 * The first one who bets on a unique number wins.
 * For example, if there are 7 people betting on { 5 31 5 88 67 88 17 },
 * then the second one who bets on 31 wins.
 * <p>
 * 第一个bet出单独数字的人
 * <p>
 * 输入N <= 10^5
 */
public class BeUnique_1041 {
    public static void main(String[] args) throws IOException {

        // todo: 4, 5 测试点超时
        // Solution1();


        // 依旧超时
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[100001];

        int[] hashTable = new int[100001];


        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
            hashTable[nums[i]]++;
        }
        int result = 0;

        for (int i = 0; i < N; i++) {
            if (hashTable[nums[i]] == 1) {
                result = nums[i];
                break;
            }
        }

        if (result == 0) {
            System.out.println("None");
        } else {
            System.out.println(result);
        }

    }

    /**
     * 方法1
     * 测试点4, 5超时
     *
     * @throws IOException
     */
    private static void Solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int N = Integer.parseInt(s.split(" ")[0]);

        int[] nums = new int[N];

        Map<Integer, Integer> map = new HashMap<>(N);


        // 读入
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(s.split(" ")[i + 1]);

            if (map.containsKey(nums[i])) {
                int value = map.get(nums[i]);
                value = value + 1;
                map.put(nums[i], value);
            } else {
                map.put(nums[i], 1);
            }
        }

        // 记录第一个独特的数字
        int result = 0;

        for (int i = N - 1; i >= 0; i--) {
            if (map.get(nums[i]) == 1) {
                result = nums[i];
            }
        }


        if (result == 0) {
            System.out.println("None");
        } else {
            System.out.println(result);
        }
    }
}

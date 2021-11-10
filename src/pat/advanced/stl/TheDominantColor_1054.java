package pat.advanced.stl;

import java.util.*;

/**
 * A strictly dominant color takes more than half of the total area
 * 求超过半数的颜色
 */
public class TheDominantColor_1054 {
    public static void main(String[] args) {
        // pat测试点超时, nowcode ac
        // solution1();

        //
        solution2();

    }

    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        // 直接用map来存储， 然后看超过一般的大小
        int threshold = (M * N) / 2;
        Map<Integer, Integer> map = new HashMap<>();

        int[][] arrays = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arrays[i][j] = sc.nextInt();
                if (null != map.get(arrays[i][j])) {
                    int old = map.get(arrays[i][j]);
                    old++;
                    map.put(arrays[i][j], old);
                    if (old > threshold) {
                        System.out.print(arrays[i][j]);
                        return;
                    }
                } else {
                    map.put(arrays[i][j], 1);
                }
            }
        }


    }


    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        int[][] arrays = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arrays[i][j] = sc.nextInt();
            }
        }

        // 直接用map来存储， 然后看超过一般的大小
        int threshold = (M * N) / 2;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (null != map.get(arrays[i][j])) {
                    Integer oldValue = map.get(arrays[i][j]);
                    map.put(arrays[i][j], ++oldValue);
                } else {
                    map.put(arrays[i][j], 1);
                }
            }
        }

        int maxKey = 0;
        int max = -1;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > threshold && entry.getValue() > max) {
                max = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        System.out.print(maxKey);


    }
}

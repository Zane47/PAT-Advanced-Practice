package pat.advanced;

import java.util.*;

/**注意点
 *
 *
 * 第二个测试点过不了
 *
 * 1.输出格式，保留一位小数;还有0的情况，后面不需要输出空格
 *
 * 2.不要输出相加系数为零的项，完全抵消的话，输出0；
 *
 * 3.注意数组越界问题（我因为数组范围太小， 第二个测试点一直无法通过，但是报的是答案错误而不是段错误，导致我看了好久的程序，
 *
 * 后来输入允许的最多项后， vs2008 报出stack around the variable “” was corrupted的错误。具体的问题详解请百度，扩大数组范围后，问题解决）
 *
 *
 */

/**
 * 2 1 2.4 0 3.2
 * 2 2 1.5 1 0.5
 *
 * 3 2 1.5 1 2.9 0 3.2
 *
 *
 * 3 999 5 100 -5 0 -0.5
 * 4 100 5 10 -1 3 5.7 0 10
 *
 * 4 999 5.0 10 -1.0 3 5.7 0 9.5
 * wrong ans: 5 999 5.0 100 0.0 3 5.7 10 -1.0 0 9.5
 *
 * 1 0 -3.2
 * 1 0 3.2
 * 0
 *
 *
 *
 */

public class APlusB4Polynomials_1002 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();

        String[] v1 = line1.split(" ");
        String[] v2 = line2.split(" ");
        int K1 = Integer.parseInt(v1[0]);
        int K2 = Integer.parseInt(v2[0]);

        HashMap<Integer, Double> map1 = new HashMap<>();
        HashMap<Integer, Double> map2 = new HashMap<>();

        for (int i = 1; i < 2 * K1; i++) {
            if (i % 2 == 1) {
                map1.put(Integer.parseInt(v1[i]), Double.parseDouble(v1[i + 1]));
            }
        }

        for (int i = 1; i < 2 * K2; i++) {
            if (i % 2 == 1) {
                map2.put(Integer.parseInt(v2[i]), Double.parseDouble(v2[i + 1]));
            }
        }

        HashMap<Integer, Double> resultMap = new HashMap<>(map1);

        for (Integer key : map2.keySet()) {
            if (resultMap.containsKey(key)) {
                if (resultMap.get(key) + map2.get(key) != 0) {
                    resultMap.put(key, resultMap.get(key) + map2.get(key));
                }
                else {
                    // 相加为0了那么就要删除这个记录
                    resultMap.remove(key);
                }
            }
            else {
                resultMap.put(key, map2.get(key));
            }
        }
        System.out.print(resultMap.size());
        // 如果为0，不需要输出空格
        if (resultMap.size() != 0) {
            System.out.print(" ");
        }


        // 倒序输出 ListIterator
        ListIterator<Map.Entry<Integer, Double>> iterator =
                new ArrayList<Map.Entry<Integer, Double>>(resultMap.entrySet()).listIterator(resultMap.size());


        while (iterator.hasPrevious()) {

            Map.Entry<Integer, Double> entry = iterator.previous();
            System.out.print(entry.getKey() + " ");
            System.out.printf("%.1f", entry.getValue());
            if (iterator.hasPrevious()) {
                System.out.print(" ");
            }
        }



    }



}

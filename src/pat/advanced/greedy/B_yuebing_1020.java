package pat.advanced.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**1020	月饼
 *
 * 有一个测试点"非零返回"??
 *
 * ref: https://github.com/liuchuo/PAT/edit/master/BasicLevel_Java/1020.%20%E6%9C%88%E9%A5%BC%20(25).java
 *
 * 注意点:
 *
 * 1. implements Comparable<>, 否则超时
 * 2. BufferedReader, 否则超时
 *
 *
 * 1. 库存量和总售价是正数, 不是正整数, 用double
 * 2. 最大收益，以亿元为单位并精确到小数点后 2 位。
 *
 * 最大收益
 * 假如我们有 3 种月饼，其库存量分别为 18、15、10 万吨，总售价分别为 75、72、45 亿元。
 * 如果市场的最大需求量只有 20 万吨，
 * 最大收益策略应该是卖出全部 15 万吨第 2 种月饼、以及 5 万吨第 3 种月饼，
 * 获得 72 + 45/2 = 94.5（亿元）。
 *
 */
public class B_yuebing_1020 {
    public static void main(String[] args) throws IOException {
        // Solution1 超时
        // solution1();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");


        int n = Integer.parseInt(in[0]);
        int d = Integer.parseInt(in[1]);

        ArrayList<MoonCake> moonCakes = new ArrayList<MoonCake>();
        String[] w = br.readLine().split(" ");
        String[] pr = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            double m = Double.parseDouble(w[i]);
            double p = Double.parseDouble(pr[i]);
            double value = p / m;
            moonCakes.add(new MoonCake(m, p, value));
        }

        Collections.sort(moonCakes);

        // 现在是第几种月饼, 按照单价从高到低
        int v1 = 0;
        // 剩余的重量, 初始就是市场可以接受的最大重量
        int remainingWeight = d;
        // 最大收益
        double maxProfile = 0;
        while (remainingWeight >= 0) {
            if (remainingWeight >= moonCakes.get(v1).store) {
                remainingWeight -= moonCakes.get(v1).store;
                maxProfile += moonCakes.get(v1).totalSellPrice;
            } else {
                // 市场的剩余重量小于某月饼的库存
                maxProfile += moonCakes.get(v1).singleSellPrice * remainingWeight;
                break;
            }
            v1++;
        }
        System.out.printf("%.2f", maxProfile);
    }

    private static void solution1() {

        Scanner sc = new Scanner(System.in);
        // 不超过 1000 的正整数 N 表示月饼的种类数
        int N = sc.nextInt();
        // 不超过 500（以万吨为单位）的正整数 D 表示市场最大需求
        int D = sc.nextInt();
        // // 接回车
        sc.nextLine();
        List<MoonCake> moonCakes = new ArrayList<>();
        // 随后一行给出 N 个正数表示每种月饼的库存量（以万吨为单位)
        String[] strings1 = sc.nextLine().split(" ");
        for (String s : strings1) {
            MoonCake moonCake = new MoonCake();
            moonCake.store = Double.parseDouble(s);
            moonCakes.add(moonCake);
        }
        // 接回车
        //sc.nextLine();
        // 最后一行给出 N 个正数表示每种月饼的总售价（以亿元为单位）
        String[] strings2 = sc.nextLine().split(" ");
        for (int i = 0; i < strings2.length; i++) {
            moonCakes.get(i).totalSellPrice = Double.parseDouble(strings2[i]);
            moonCakes.get(i).singleSellPrice = moonCakes.get(i).totalSellPrice / moonCakes.get(i).store;
        }

        // 按照单价从高到低排列
        moonCakes.sort((o1, o2) -> Double.compare(o2.singleSellPrice, o1.singleSellPrice));
        // 现在是第几种月饼, 按照单价从高到低
        int v1 = 0;
        // 剩余的重量, 初始就是市场可以接受的最大重量
        int remainingWeight = D;
        // 最大收益
        double maxProfile = 0;
        while (remainingWeight >= 0) {
            if (remainingWeight >= moonCakes.get(v1).store) {
                remainingWeight -= moonCakes.get(v1).store;
                maxProfile += moonCakes.get(v1).totalSellPrice;
            } else {
                // 市场的剩余重量小于某月饼的库存
                maxProfile += moonCakes.get(v1).singleSellPrice * remainingWeight;
                break;
            }
            v1++;
        }
        System.out.printf("%.2f", maxProfile);
    }

    static class MoonCake implements Comparable<MoonCake> {
        // 库存量
        double store;
        // 总售价
        double totalSellPrice;
        // 单价
        double singleSellPrice;

        public MoonCake() {

        }

        public MoonCake(double amount, double price, double value) {
            this.store = amount;
            this.totalSellPrice = price;
            this.singleSellPrice = value;
        }

        @Override
        public int compareTo(MoonCake o) {
            return singleSellPrice > o.singleSellPrice ? -1 : 1;
        }
    }

}

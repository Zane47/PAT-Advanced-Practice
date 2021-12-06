package pat.advanced.greedy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 起始状态的邮箱是空的,
 * 如果不可能到达终点, 那么就打印可以走的最远距离
 * <p>
 * 思路:
 * step1
 * 按照distance从小到达进行排列, 如果第一个gas station的distance不是0,直接fail
 * 终点的价格设置为0
 * <p>
 * step2
 * 优先找油价低的, 范围是: 从现在的点now, 如果加满油可以到达的点
 * 1. 到下一个价格更低的点
 * 2. 没有价格更低, 那么就到加满油能走到的范围内价格最低的点
 * 3. 如果加满了油, 仍无可以到达的点, 那么就fail
 * <p>
 * !!!!这里有一个问题:
 * 在加满油可以达到的所有点中, 存在价格更低的点
 * 按照e.g.1来计算
 * 1. 优先到价格最低的点? 0 -> 3 -> 5 -> 6 -> 8 = 764.25 wrong
 * 2. 优先到第一个价格更低的点? 0 -> 1 -> 3 -> 5 -> 6 -> 7 -> 8 =  right
 * <p>
 * 所以, 优先到达第一个价格更低的点!!!!
 */
public class ToFillOrNotToFill_1033 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // the maximum capacity of the tank, <=100
        int cMax = sc.nextInt();
        // D (<=30000), the distance between Hangzhou and the destination city
        int D = sc.nextInt();
        // the average distance per unit gas that the car can run;<=20
        int dAvg = sc.nextInt();
        // and N (≤ 500), the total number of gas stations
        int N = sc.nextInt();

        GasStation[] stations = new GasStation[N + 1];
        for (int i = 0; i < N; i++) {
            stations[i] = new GasStation();
            stations[i].price = sc.nextDouble();
            stations[i].distance = sc.nextInt();
        }

        // 终点也是一个station
        stations[N] = new GasStation();
        stations[N].price = 0;
        stations[N].distance = D;
        // step1: 按照距离递增, 如果第一个不是0, 直接fail
        Arrays.sort(stations, (o1, o2) -> o1.distance - o2.distance);

        if (stations[0].distance > 0) {
            System.out.println("The maximum travel distance = " + 0.00);
            return;
        }

        // 做贪心
        // step2: 计算如果加满油可以到达的station中, 选择价格更优的一个
        // 第一个价格更低, 或者范围内价格最低的

        double result = 0;

        // 现在车子在的点
        int nowPoint = 0;

        // 满油情况下可以走的最远距离
        int maxDistance = cMax * dAvg;

        while (nowPoint != N) {
            // 满油情况下可以走到的最远的station
            int longestAvailableStation = calcNextLongestStation(stations, nowPoint, maxDistance);

            // 加满了油仍然没有可以到达的点
            if (longestAvailableStation == 0) {
                System.out.printf("The maximum travel distance = %d.00", stations[nowPoint].distance + maxDistance);
                return;
            }
            // 从nowPoint到这个点, 找第一个更低的油价, 如果没有的话, 找范围内最低的油价
            int nextPoint = findNextPoint(stations, nowPoint, longestAvailableStation);
            System.out.print(nextPoint);
            // result 加上从起点到终点, 刚好加的油的价格
            double nextDistance = (double) stations[nextPoint].distance - stations[nowPoint].distance;
            result += (nextDistance / dAvg) * stations[nowPoint].price;

            nowPoint = nextPoint;
        }
        System.out.println();

        System.out.printf("%.2f", result);

    }

    /**
     * 找到下一个要去的点
     * 1.找第一个比nowPoint油价更低的点,
     * 2.如果没有的话, 找从nowPoint+1到longest范围内最低的油价的点
     *
     * @param stations                stations
     * @param nowPoint                现在在的点
     * @param longestAvailableStation 最远可以达到的点
     * @return nextPoint
     */
    private static int findNextPoint(GasStation[] stations, int nowPoint, int longestAvailableStation) {
        double nowPrice = stations[nowPoint].price;
        // 找第一个比nowPoint油价更低的点,
        for (int i = nowPoint + 1; i <= longestAvailableStation; i++) {
            if (stations[i].price <= nowPrice) {
                return i;
            }
        }

        // 如果没有的话, 找从nowPoint+1到longest范围内最低的油价的点
        int minPoint = nowPoint + 1;
        for (int i = nowPoint + 1; i <= longestAvailableStation; i++) {
            if (stations[i].price < stations[minPoint].price) {
                minPoint = i;
            }
        }

        return minPoint;
    }

    /**
     * 计算车子加满油时, 从nowPoint可以达到的最远的点
     *
     * @param stations
     * @param nowPoint    现在车子在的点
     * @param maxDistance 车子可以行驶的最远距离, 常数
     */
    private static int calcNextLongestStation(GasStation[] stations, int nowPoint, int maxDistance) {
        // 从nowpoint出发, 加满油可以达到的最远的距离, 距离: 是相对于起点而言的
        int maxAvailDistance = stations[nowPoint].distance + maxDistance;

        // 从后往前, 找第一个能到达的点
        for (int i = stations.length - 1; i > nowPoint; i--) {
            if (maxAvailDistance >= stations[i].distance) {
                return i;
            }
        }
        return 0;
    }

    static class GasStation {
        double price;
        int distance;
    }

}

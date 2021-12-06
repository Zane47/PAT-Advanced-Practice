package pat.advanced.greedy;

import java.util.Scanner;

/**
 * 起始状态的邮箱是空的,
 * 如果不可能到达终点, 那么就打印可以走的最远距离
 *
 * 思路:
 * step1
 * 按照distance从小到达进行排列, 如果第一个gas station的distance不是0,直接fail
 * 终点的价格设置为0
 *
 * step2
 * 优先找油价低的, 范围是: 从现在的点now, 如果加满油可以到达的点
 * 1. 到下一个价格更低的点
 * 2. 没有价格更低, 那么就到加满油能走到的范围内价格最低的点
 * 3. 如果加满了油, 仍无可以到达的点, 那么就fail
 *
 * !!!!这里有一个问题:
 * 在加满油可以达到的所有点中, 存在价格更低的点
 * 按照e.g.1来计算
 * 1. 优先到价格最低的点? 0 -> 3 -> 5 -> 6 -> 8 = 764.25 wrong
 * 2. 优先到第一个价格更低的点? 0 -> 1 -> 3 -> 5 -> 6 -> 7 -> 8 = 674.59 right
 *
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

        





    }
}

package pat.advanced.tree;

import java.util.List;
import java.util.Scanner;

/**
 * -> 增加点权, TotalSalesOfSupplyChain_1079
 * It is assumed that each member in the supply chain has exactly one supplier except the root supplier, and there is no supply cycle
 * 说明是树
 * <p>
 * you are supposed to tell the highest price we can expect from some retailers.
 *
 * print:
 * total sales we can expect from all the retailers, accurate up to 1 decimal place.
 */
public class HighestPriceInSupplyChain_1090 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //  the total number of the members in the supply chain
        //  they are numbered from 0 to N−1;
        int N = sc.nextInt();

        // the unit price given by the root supplier
        double P = sc.nextDouble();

        // the percentage rate of price increment for each distributor or retailer.
        double r = sc.nextDouble();

        // Si is the index of the supplier for the i-th member.
        // Sroot for the root supplier is defined to be −1.
        // All the numbers in a line are separated by a space.
        for (int i = 0; i < N; i++) {
            
        }






    }

    static class TreeNode {
        double value;
        List<Integer> children;
    }

}

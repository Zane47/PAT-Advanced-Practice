package pat.advanced.stl;

import java.util.Scanner;

/**
 *
 * todo: 读懂题意
 *
 *
 * For each test case, print the final ranks in a line.
 * The i-th number is the rank of the i-th programmer,
 * and all the numbers must be separated by a space,
 * with no extra space at the end of the line.
 */
public class MiceAndRice_1056 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // # of programmers
        int Np = sc.nextInt();
        // the max number of mice in a group, <=1000
        int Ng = sc.nextInt();

        // If there are less than Ng mice at the end of the player's list,
        // then all the mice left will be put into the last group

        // each Wi is the weight of the i-th mouse respectively
        int[] W = new int[Np];
        for (int i = 0; i < Np; i++) {
            W[i] = sc.nextInt();
        }

        // 3th line gives the initial playing order which is a permutation of 0 ~ Np-1
        int[] permutation = new int[Np];
        for (int i = 0; i < Np;i++) {
            permutation[i] = sc.nextInt();
        }





    }
}

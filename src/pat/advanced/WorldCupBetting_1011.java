package pat.advanced;


import java.util.Scanner;

/**
 *
 *  W    T    L
 * 1.1  2.5  1.7
 * 1.2  3.1  1.6
 * 4.1  1.2  1.1
 *
 *
 * T T W 39.31
 *
 *  (4.1×3.1×2.5×65%−1)×2=39.31
 *
 */
public class WorldCupBetting_1011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] array = new double[3][3];
        for (int i = 0; i < 3; i++) {
            array[i][0] = sc.nextDouble();
            array[i][1] = sc.nextDouble();
            array[i][2] = sc.nextDouble();
        }

        int[] betResult = new int[3];
        for (int i = 0; i < 3; i++) {
            double max = array[i][0];
            betResult[i] = 0;
            if (array[i][1] > max) {
                max = array[i][1];
                betResult[i] = 1;
            }
            if (array[i][2] > max) {
                max = array[i][2];
                betResult[i] = 2;
            }
        }

        for (int i = 0; i < 3; i++) {
            switch (betResult[i]) {
                case 0:
                    System.out.print("W ");
                    break;
                case 1:
                    System.out.print("T ");
                    break;
                case 2:
                    System.out.print("L ");
                    break;
            }
        }

        double result = (array[0][betResult[0]] * array[1][betResult[1]] * array[2][betResult[2]] * 0.65 - 1) * 2;

        System.out.printf("%.2f", result);

    }


}

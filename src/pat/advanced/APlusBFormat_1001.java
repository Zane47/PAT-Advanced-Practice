package pat.advanced;

/**
 * -697465 -873425
 * 146710 187686
 */


import java.util.Scanner;

public class APlusBFormat_1001 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int v1 = a + b;
        if (v1 < 0) {
            System.out.print("-");
            v1 = -v1;
        }

        String strResult = String.valueOf(v1);
        for (int i = 0 ; i < strResult.length(); i++) {
            if ((strResult.length() - i) % 3 == 0 && i != 0) {
                System.out.print(",");
            }
            System.out.print(strResult.charAt(i));
        }



    }

}

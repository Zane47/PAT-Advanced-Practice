package pat.advanced;


import java.util.Scanner;

/**注意点
 * 1.注意数据输入N < 10^ 100，所以一开始要用String来接受
 *
 *
 */

public class SpellItRight_1005 {

    public static void main(String[] args) {
        String[] numEnglish = new String[] {
                "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        };

        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        int result = 0;
        for (int i = 0; i < inputStr.length(); i++) {
            result += Integer.parseInt(String.valueOf(inputStr.charAt(i)));
        }
        String strResult = String.valueOf(result);

        for (int i = 0; i < strResult.length(); i++) {
            int index = Integer.parseInt(String.valueOf(strResult.charAt(i)));
            System.out.print(numEnglish[index]);
            if (i != strResult.length() - 1) {
                System.out.print(" ");
            }
        }




    }

}

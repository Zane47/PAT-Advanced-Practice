package pat.advanced.patstring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * replace 1 (one) by @, 0 (zero) by %, l by L, and O by o
 */
public class Password_1035 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String name = sc.next();
            String pwd = sc.next();
            if (pwd.contains("1") ||
                    pwd.contains("0") ||
                    pwd.contains("l") ||
                    pwd.contains("O")) {
                pwd = pwd.replace("1", "@");
                pwd = pwd.replace("0", "%");
                pwd = pwd.replace("l", "L");
                pwd = pwd.replace("O", "o");
                String[] str = new String[2];
                str[0]  =name;
                str[1] = pwd;
                list.add(str);
            }
        }
        if (list.size() == 0) {
            if (N == 1) {
                System.out.print("There is 1 account and no account is modified");
            } else {
                System.out.printf("There are %d accounts and no account is modified", N);
            }
        } else {
            System.out.println(list.size());
            for (String[] l : list) {
                System.out.print(l[0]);
                System.out.print(" ");
                System.out.println(l[1]);
            }
        }




    }
}

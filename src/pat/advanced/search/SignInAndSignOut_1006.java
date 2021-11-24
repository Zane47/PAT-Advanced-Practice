package pat.advanced.search;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 对于时间的处理 ->
 *     1. 换成总秒数, 求最大和最小
 *     2.
 */
public class SignInAndSignOut_1006 {

    public static void main(String[] args) throws ParseException {
        // solution1();
        DateFormat dateFormat = new SimpleDateFormat("HH:MM:SS");

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Date minSignIn = dateFormat.parse("2015-10-01 23:59:59");
        Date maxSignOut = dateFormat.parse("2015-10-01 00:00:00");

        String unlockId = "";
        String lockId = "";
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            String signInTime = sc.next();
            String signOutTime = sc.next();
            Date signIn = dateFormat.parse(signInTime);
            Date signOut = dateFormat.parse(signOutTime);

            if (signIn.before(minSignIn)) {
                minSignIn = signIn;
                unlockId = id;
            }
            if (signOut.after(maxSignOut)) {
                maxSignOut = signOut;
                lockId = id;
            }
        }

        System.out.print(unlockId);
        System.out.print(" ");
        System.out.print(lockId);




    }

    /**
     * 1. 时间换成总秒数, 求最大和最小
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int minTime = Integer.MAX_VALUE;
        int maxTime = -1;
        String unlockId = "";
        String lockId = "";
        for (int i = 0; i < n; i++) {
            String id = sc.next();
            String signInTime = sc.next();
            String signOutTime = sc.next();
            String[] str1 = signInTime.split(":");
            int tempSignIn = 3600 * Integer.parseInt(str1[0]) + 60 * Integer.parseInt(str1[1]) + Integer.parseInt(str1[2]);
            String[] str2 = signOutTime.split(":");
            int tempSignOut = 3600 * Integer.parseInt(str2[0]) + 60 * Integer.parseInt(str2[1]) + Integer.parseInt(str2[2]);

            // signin 最小
            if (tempSignIn < minTime) {
                minTime = tempSignIn;
                unlockId = id;
            }
            // signout 最大
            if (tempSignOut > maxTime) {
                maxTime = tempSignOut;
                lockId = id;
            }
        }

        System.out.print(unlockId);
        System.out.print(" ");
        System.out.print(lockId);
    }


}

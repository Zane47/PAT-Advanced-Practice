package pat.advanced.sort;

import java.util.*;

/**
 * name, ID and grade.
 *
 *
 *
 *
 */
public class ListGrades_1083 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[][] array = new String[N][3];
        for (int i = 0; i < N; i++) {
            array[i][0] = sc.next();
            array[i][1] = sc.next();
            array[i][2] = sc.next();
        }
        int low = sc.nextInt();
        int high = sc.nextInt();
        List<String[]> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int grade = Integer.parseInt(array[i][2]);
            if (grade >= low && grade <= high) {
                String[] temp = new String[3];
                temp[0] = array[i][0];
                temp[1] = array[i][1];
                temp[2] = array[i][2];
                result.add(temp);
            }
        }
        if (result.size() == 0) {
            System.out.println("NONE");
            return;
        }

        result.sort(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o2[2]) - Integer.parseInt(o1[2]);
            }
        });

        for (String[] str : result) {
            System.out.print(str[0]);
            System.out.print(" ");
            System.out.println(str[1]);
        }

    }
}

package pat.advanced.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * !!!注意点:
 * 最后一个测试点超时
 * 用int存储id, 输出的时候要06d
 *
 *
 *
 * sort records according to any column
 *
 * output:
 * if C = 1 then the records must be sorted in increasing order according to ID's;
 * if C = 2 then the records must be sorted in non-decreasing order according to names;
 * if C = 3 then the records must be sorted in non-decreasing order according to grades.
 * If there are several students who have the same name or grade,
 * must be sorted according to their ID's in increasing order.
 */
public class ListSorting_1028 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // # of records
        int N = sc.nextInt();
        // C is the column that you are supposed to sort the records with
        int C = sc.nextInt();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Student stu = new Student();
            stu.setId(sc.nextInt());
            stu.setName(sc.next());
            stu.setGrade(sc.nextInt());
            students.add(stu);
        }
        students.sort((o1, o2) -> {
            if (C == 1) {
                return o1.getId() - o2.getId();
            } else if (C == 2) {
                if (o1.getName().equals(o2.getName())) {
                    return o1.getId() - o2.getId();
                }
                return o1.getName().compareTo(o2.getName());

            } else if (C == 3) {
                if (o1.getGrade() == o2.getGrade()) {
                    return o1.getId() - o2.getId();
                }
                return o1.getGrade() - o2.getGrade();
            }
            return 0;
        });

        for (Student stu : students) {
            System.out.printf("%06d %s %d\n", stu.getId(), stu.getName(), stu.getGrade());
        }

    }

    private static int compare(char[] v1, char[] v2) {
        int lim = Math.min(v1.length, v2.length);
        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return v1.length - v2.length;
    }

    static class Student {
        // 6-digit, 但是000001, 输出的时候要用06d来输出
        int id;
        String name;
        // [0, 100]
        int grade;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }
    }


}

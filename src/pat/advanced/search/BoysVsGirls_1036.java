package pat.advanced.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 查找
 * tell the difference between the lowest grade of all the male students
 * and the highest grade of all the female students.
 *
 * For each test case, output in 3 lines.
 * 1st line gives the name and ID of the female student with the highest grade,
 * 2nd line gives that of the male student with the lowest grade.
 * 3rd line gives the difference grade
 * If one such kind of student is missing, output Absent in the corresponding line, and output NA in the third line instead.
 *
 */
public class BoysVsGirls_1036 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Student stu = new Student();
            stu.setName(sc.next());
            stu.setGender(sc.next());
            stu.setId(sc.next());
            stu.setGrade(sc.nextInt());
            studentList.add(stu);
        }

        // 女生最高分
        String femaleName = "";
        String femaleId = "";
        int maxFemaleGrade = -1;

        // 男生最低分
        String maleName = "";
        String maleId = "";
        int minMaleGrade = Integer.MAX_VALUE;

        for (Student student : studentList) {
            if ("M".equals(student.getGender())) {
                if (student.getGrade() < minMaleGrade) {
                    maleName = student.getName();
                    maleId = student.getId();
                    minMaleGrade = student.getGrade();
                }
            } else if ("F".equals(student.getGender())) {
                if (student.getGrade() > maxFemaleGrade) {
                    femaleName = student.getName();
                    femaleId = student.getId();
                    maxFemaleGrade = student.getGrade();
                }
            }
        }

        if ("".equals(femaleName)) {
            System.out.println("Absent");
        } else {
            System.out.print(femaleName);
            System.out.print(" ");
            System.out.println(femaleId);
        }

        if ("".equals(maleName)) {
            System.out.println("Absent");
        } else {
            System.out.print(maleName);
            System.out.print(" ");
            System.out.println(maleId);
        }

        if ("".equals(maleName) || "".equals(femaleName)) {
            System.out.println("NA");
        } else {
            System.out.print(maxFemaleGrade - minMaleGrade);
        }

    }

    static class Student {
        String name;
        String gender;
        String id;
        int grade;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }
    }

}

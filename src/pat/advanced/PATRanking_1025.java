package pat.advanced;

import java.math.BigDecimal;
import java.util.*;

/**
 * N <= 100, K <= 300
 * merge all the ranklists and generate the final rank.
 * The output must be sorted in non-decreasing order of the final ranks.
 * <p>
 * The testees with the same score must have the same rank,
 * and the output must be sorted in non-decreasing order of their registration numbers.
 * <p>
 * 输出 registration_number final_rank location_number local_rank
 * <p>
 * <p>
 * input:
 * 2
 * 5
 * 1234567890001 95
 * 1234567890005 100
 * 1234567890003 95
 * 1234567890002 77
 * 1234567890004 85
 * 4
 * 1234567890013 65
 * 1234567890011 25
 * 1234567890014 100
 * 1234567890012 85
 * <p>
 * output:
 * 9
 * 1234567890005 1 1 1
 * 1234567890014 1 2 1
 * 1234567890001 3 1 2
 * 1234567890003 3 1 2
 * 1234567890004 5 1 4
 * 1234567890012 5 2 2
 * 1234567890002 7 1 5
 * 1234567890013 8 2 3
 * 1234567890011 9 2 4
 */
public class PATRanking_1025 {
    public static void main(String[] args) {
        // 全局排列
        // solution1();

        // 2、归并
        // 所有的分组已经是排序好的了，那么每个组相当于排成了一个有序的队列，
        // 让每个队列的第一个相互比较，找出成绩最高，学号最小的，出队列，输出，
        // 此时这个队列的第二位移到到队列头，重复比较。直到所有的学生输出完毕。
        solution2();
    }

    /**
     * 2、归并
     * 所有的分组已经是排序好的了，那么每个组相当于排成了一个有序的队列，
     * 让每个队列的第一个相互比较，找出成绩最高，学号最小的，出队列，输出，
     * 此时这个队列的第二位移到到队列头，重复比较。直到所有的学生输出完毕。
     */
    private static void solution2() {
        Scanner sc = new Scanner(System.in);
        List<Student> allStudents = new ArrayList<>();
        int N = sc.nextInt();
        for (int n = 0; n < N; n++) {
            int K = sc.nextInt();
            // 每一轮的studentList, 用来计算localRank的, 每轮都塞到最后的List中
            List<Student> tempStudents = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                Student stu = new Student();
                stu.setRegistrationNumber(sc.next());
                stu.setLocationNumber(n + 1);
                stu.setScore(sc.nextInt());
                tempStudents.add(stu);
            }
            // 一组数据记录完成, 做局部排序 ->localRank
            // 按照score做倒序排列, 如果score一样, 按照registrationNumber做正序排列
            tempStudents.sort((o1, o2) -> {
                return rank(o1, o2);
            });
            // 设置localRank, 重复的成绩排名一致
            tempStudents.get(0).setLocalRank(1);
            for (int i = 1; i < tempStudents.size(); i++) {
                if (tempStudents.get(i).getScore() == tempStudents.get(i - 1).getScore()) {
                    tempStudents.get(i).setLocalRank(tempStudents.get(i - 1).getLocalRank());
                } else {
                    tempStudents.get(i).setLocalRank(i + 1);
                }
            }
            allStudents.addAll(tempStudents);
        }
        // 做全体排序 -> final_rank
        // 归并排序



        printAllStudents(allStudents);
    }


    /**
     * 全局排列的解法
     */
    private static void solution1() {
        Scanner sc = new Scanner(System.in);
        List<Student> allStudents = new ArrayList<>();
        int N = sc.nextInt();
        for (int n = 0; n < N; n++) {
            int K = sc.nextInt();
            // 每一轮的studentList, 用来计算localRank的, 每轮都塞到最后的List中
            List<Student> tempStudents = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                Student stu = new Student();
                stu.setRegistrationNumber(sc.next());
                stu.setLocationNumber(n + 1);
                stu.setScore(sc.nextInt());
                tempStudents.add(stu);
            }
            // 一组数据记录完成, 做局部排序 ->localRank
            // 按照score做倒序排列, 如果score一样, 按照registrationNumber做正序排列
            tempStudents.sort((o1, o2) -> {
                return rank(o1, o2);
            });
            // 设置localRank, 重复的成绩排名一致
            tempStudents.get(0).setLocalRank(1);
            for (int i = 1; i < tempStudents.size(); i++) {
                if (tempStudents.get(i).getScore() == tempStudents.get(i - 1).getScore()) {
                    tempStudents.get(i).setLocalRank(tempStudents.get(i - 1).getLocalRank());
                } else {
                    tempStudents.get(i).setLocalRank(i + 1);
                }
            }
            allStudents.addAll(tempStudents);
        }
        // 做全体排序 -> final_rank
        allStudents.sort((o1, o2) -> {
            return rank(o1, o2);
        });
        allStudents.get(0).setFinalRank(1);
        for (int i = 1; i < allStudents.size(); i++) {
            if (allStudents.get(i).getScore() == allStudents.get(i - 1).getScore()) {
                allStudents.get(i).setFinalRank(allStudents.get(i - 1).getFinalRank());
            } else {
                allStudents.get(i).setFinalRank(i + 1);
            }
        }

        printAllStudents(allStudents);
    }

    /**
     * 按照score做倒序排列, 如果score一样, 按照registrationNumber做正序排列
     */
    private static int rank(Student o1, Student o2) {
        if (o1.getScore() != o2.getScore()) {
            return o2.getScore() - o1.getScore();
        } else {
            BigDecimal bigDecimal1 = new BigDecimal(o1.getRegistrationNumber());
            BigDecimal bigDecimal2 = new BigDecimal(o2.getRegistrationNumber());
            return bigDecimal1.compareTo(bigDecimal2);
        }
    }

    private static void printAllStudents(List<Student> allStudents) {
        System.out.println(allStudents.size());
        for (Student student : allStudents) {
            // registration_number final_rank location_number local_rank
            System.out.print(student.getRegistrationNumber());
            System.out.print(" ");
            System.out.print(student.getFinalRank());
            System.out.print(" ");
            System.out.print(student.getLocationNumber());
            System.out.print(" ");
            System.out.println(student.getLocalRank());
        }
    }

    static class Student {
        // registration_number final_rank location_number local_rank
        String registrationNumber;
        int score;
        int finalRank;
        int locationNumber;
        int localRank;

        public String getRegistrationNumber() {
            return registrationNumber;
        }

        public void setRegistrationNumber(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getFinalRank() {
            return finalRank;
        }

        public void setFinalRank(int finalRank) {
            this.finalRank = finalRank;
        }

        public int getLocationNumber() {
            return locationNumber;
        }

        public void setLocationNumber(int locationNumber) {
            this.locationNumber = locationNumber;
        }

        public int getLocalRank() {
            return localRank;
        }

        public void setLocalRank(int localRank) {
            this.localRank = localRank;
        }
    }
}

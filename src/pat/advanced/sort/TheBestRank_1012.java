package pat.advanced.sort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**todo
 * C - C Programming Language, M - Mathematics (Calculus or Linear Algrbra), and E - English.
 * <p>
 * At the mean time, we encourage students by emphasizing on their best ranks --
 * that is, among the four ranks with respect to the three courses and the average grade,
 * we print the best rank for each student.
 * <p>
 * Then the best ranks for all the students are No.1
 * since the 1st one has done the best in C Programming Language,
 * while the 2nd one in Mathematics,
 * the 3rd one in English, and the last one in average.
 * <p>
 * 要输出对每一个学生来说, 自己排名最高和最高的标准, 也就是按照什么科目/平均分排列下, 该学生最高
 * 注意:
 * 1. 平均分是四舍五入的
 * 2. The priorities of the ranking methods are ordered as A > C > M > E, 所以如果有排名一样的情况, 输出最高优先级的那个
 */
public class TheBestRank_1012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        List<Student> students = new ArrayList<>();
        Map<Integer, Boolean> existed = new HashMap<>();
        for (int i = 0; i < N; i++) {
            Student stu = new Student();
            stu.setId(sc.nextInt());
            stu.setScoreC(sc.nextInt());
            stu.setScoreM(sc.nextInt());
            stu.setScoreE(sc.nextInt());
            int sum = stu.getScoreC() + stu.getScoreM() + stu.getScoreE();
            BigDecimal bigDecimal = new BigDecimal(sum);
            stu.setAverage(bigDecimal.divide(BigDecimal.valueOf(3), RoundingMode.HALF_UP).intValue());
            students.add(stu);
            existed.put(stu.getId(), true);
        }
        // 排序
        sort(students, "A");
        sort(students, "C");
        sort(students, "M");
        sort(students, "E");

        for (int i = 0; i < M; i++) {
            int id = sc.nextInt();
            if (!existed.containsKey(id)) {
                System.out.print("N/A");
            } else {
                for (Student student : students) {
                    if (student.getId() == id) {
                        // 如果有一样的情况, priorities of the ranking methods are ordered as A > C > M > E
                        student.getRankBasedA();
                        student.getRankBasedC();
                        student.getRankBasedM();
                        student.getRankBasedE();

                    }
                }
            }

            if (i != M - 1) {
                System.out.println();
            }
        }


    }


    private static void sort(List<Student> students, String base) {
        students.sort((o1, o2) -> {
            int v1 = 0, v2 = 0;
            switch (base) {
                case "A":
                    v1 = o1.getAverage();
                    v2 = o2.getAverage();
                    break;
                case "C":
                    v1 = o1.getScoreC();
                    v2 = o2.getScoreC();
                    break;
                case "M":
                    v1 = o1.getScoreM();
                    v2 = o2.getScoreM();
                    break;
                case "E":
                    v1 = o1.getScoreE();
                    v2 = o2.getScoreE();
                    break;
                default:
                    break;
            }
            return v2 - v1;
        });
        for (int i = 0; i < students.size(); i++) {
            if ("A".equalsIgnoreCase(base)) {
                if (students.get(i).getAverage() == students.get(i - 1).getAverage()) {
                    students.get(i).setRankBasedA(students.get(i - 1).getRankBasedA());
                } else {
                    students.get(i).setRankBasedA(i + 1);
                }
            } else if ("C".equalsIgnoreCase(base)) {
                if (students.get(i).getScoreC() == students.get(i - 1).getScoreC()) {
                    students.get(i).setRankBasedC(students.get(i - 1).getRankBasedC());
                } else {
                    students.get(i).setRankBasedC(i + 1);
                }
            } else if ("M".equalsIgnoreCase(base)) {
                if (students.get(i).getScoreM() == students.get(i - 1).getScoreM()) {
                    students.get(i).setRankBasedM(students.get(i - 1).getRankBasedM());
                } else {
                    students.get(i).setRankBasedM(i + 1);
                }
            } else if ("E".equalsIgnoreCase(base)) {
                if (students.get(i).getScoreE() == students.get(i - 1).getScoreE()) {
                    students.get(i).setRankBasedE(students.get(i - 1).getRankBasedE());
                } else {
                    students.get(i).setRankBasedE(i + 1);
                }
            }
        }
    }

    static class Student {
        // a string of 6 digits
        int id;
        int scoreC;
        int scoreM;
        int scoreE;
        int average;

        int rankBasedC;
        int rankBasedM;
        int rankBasedE;
        int rankBasedA;

        public int getRankBasedC() {
            return rankBasedC;
        }

        public void setRankBasedC(int rankBasedC) {
            this.rankBasedC = rankBasedC;
        }

        public int getRankBasedM() {
            return rankBasedM;
        }

        public void setRankBasedM(int rankBasedM) {
            this.rankBasedM = rankBasedM;
        }

        public int getRankBasedE() {
            return rankBasedE;
        }

        public void setRankBasedE(int rankBasedE) {
            this.rankBasedE = rankBasedE;
        }

        public int getRankBasedA() {
            return rankBasedA;
        }

        public void setRankBasedA(int rankBasedA) {
            this.rankBasedA = rankBasedA;
        }

        public int getAverage() {
            return average;
        }

        public void setAverage(int average) {
            this.average = average;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScoreC() {
            return scoreC;
        }

        public void setScoreC(int scoreC) {
            this.scoreC = scoreC;
        }

        public int getScoreM() {
            return scoreM;
        }

        public void setScoreM(int scoreM) {
            this.scoreM = scoreM;
        }

        public int getScoreE() {
            return scoreE;
        }

        public void setScoreE(int scoreE) {
            this.scoreE = scoreE;
        }
    }

}

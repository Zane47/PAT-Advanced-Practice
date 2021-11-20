package pat.advanced;

import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        Student[] students = new Student[30010];
        int N = sc.nextInt();
        // 因为有多组, 所以记录index
        int index = 0;
        for (int n = 0; n < N; n++) {
            int K = sc.nextInt();
            for (int i = 0; i < K; i++) {
                students[index].setRegistrationNumber(sc.next());
                students[index].setLocationNumber(i + 1);
                students[index].setScore(sc.nextInt());
                index++;
            }
            // 一组数据记录完成, 做局部排序

        }
        // 所有数据排序完成, 做全体排序


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

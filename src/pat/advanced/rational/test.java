package pat.advanced.rational;

public class test {
    public static void main(String[] args) {
        long v1 = 10L;
        long v2 = 15L;
        if (v1 < v2) {
            long temp = v2;
            v2 = v1;
            v1 = temp;
        }
        int result = (int)v2;
        while (v1 % v2 != 0) {
            int temp = (int) (v1 % v2);
            if (temp == 1) {
                break;
            }
            v1 = v1 / temp;
            v2 = v2 / temp;
            result = temp;
        }

        int a = 0;

    }
}

package pat.advanced.rational;

/**
 * https://www.cnblogs.com/liuzhen1995/p/6423600.html
 * lcm(m,n) = (m*n) / gcd(m,n)
 */
public class LeastCommonMultiple_LCM {
    //使用欧几里得算法求解数m和数n最大公约数
    public int getGcd(int m,int n){
        while(n > 0){
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    //求解数m和n和最小公倍数
    public int getLcm(int m,int n){
        int gcd = getGcd(m,n);
        int result = m * n / gcd;
        return result;
    }

    public static void main(String[] args){
        LeastCommonMultiple_LCM test = new LeastCommonMultiple_LCM();
        System.out.println("60和12的最大公约数："+test.getGcd(60, 12));
        System.out.println("60和12的最小公倍数："+test.getLcm(60, 12));
    }

}
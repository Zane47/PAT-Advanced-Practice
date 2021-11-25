# 输出printf
## 数字前面有0的情况
`000001`: 
```
System.out.printf("%06d\n", stu.getId());
```

## char[]的输出
```
char[] = {'a', 'b', 'c'}
new String(stu.getName()) -> abc 
Arrays.toString(stu.getName()) -> [a, b, c]
```

# 数学
## 最大公约数GCD，Greatest Common Divisor(GCD)
maxCommonDivisor
```
// 递归法求最大公约数
public static int maxCommonDivisor(int m, int n) {
    if (m < n) {// 保证m>n,若m<n,则进行数据交换
        int temp = m;
        m = n;
        n = temp;
    }
    if (m % n == 0) {// 若余数为0,返回最大公约数
        return n;
    } else { // 否则,进行递归,把n赋给m,把余数赋给n
        return maxCommonDivisor(n, m % n);
    }
}

// 循环法求最大公约数
public static int maxCommonDivisor2(int m, int n) {

    if (m < n) {// 保证m>n,若m<n,则进行数据交换
        int temp = m;
        m = n;
        n = temp;
    }
    while (m % n != 0) {// 在余数不能为0时,进行循环
        int temp = m % n;
        m = n;
        n = temp;
    }
    return n;// 返回最大公约数
}
```


## 最小公倍数
minCommonMultiple
```
// 求最小公倍数
public static int minCommonMultiple(int m, int n) {
    return m * n / maxCommonDivisor(m, n);
}

```
## 四舍五入
https://www.cnblogs.com/chenssy/p/3366632.html
_RoundingMode_.HALF_EVEN


# 时间
## java对时间的对比方法
https://blog.csdn.net/u013991521/article/details/60471545
https://blog.csdn.net/lom9357bye/article/details/50347689
1. 换算成总秒数进行对比 -> `SignInAndSignOut_1006`
不带日期的时间
2. 


# TIPS(做题技巧, 调试技巧)
## 线上调试法
e.g.: [1081](https://zhuanlan.zhihu.com/p/105108323)



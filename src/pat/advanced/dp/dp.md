











# 背包问题 -> leetcode下的背包问题
## 01背包问题
有n个物品, 重量分别为weight[i], 每个的价值是value[i]. 现有一个容量V的背包, 问如何选取物品放入背包, 可以让背包内物品的总价值最大
**其中每种物品都只有一件**

* 暴力解法: 每个物品都有两种选择: 放入或者不放入, 复杂度为O(2^n)

* dp: 复杂度O(nV)

### 二维

`dp[i][v]`: 前i件物品(1<=i<=n, 0<=v<=V)恰好装入容量为v的背包中所能获得的最大价值
考虑对第i件物品的选择策略, 有两种:

1. 不放入第i件物品, 那么问题转换为: 前i-1件物品恰好装入容量为v的背包中所能获得的最大价值, 也就是`dp[i][v] = dp[i-1][v]`

2. 放入第i件物品, 那么问题转换为: 前i-1件物品恰好装入容量为`v-weight[i]`的背包中所能获得的最大价值, `dp[i][v] = dp[i-1][v-weight[i]] + value[i]`


只有这两种策略, 取最大值即可, 状态转移方程如下

```
dp[i][v] = max(dp[i-1][v], dp[i-1][v-weight[i]] + value[i])
其中, 1<=i<=n, weight[i]<=v<=V
```


---

注意到`dp[i][v]`只与之前状态的`dp[i-1][]`有关, 所以可以枚举i从1至n, v从0到V, 通过边界`dp[0][v] = 0(0<=v<=V)`(即前0件物品放入任何容量v的背包中都只能获得价值0)就可以把整个dp数组递推出来. 

而由于`dp[i][v]`表示的是恰好为v的情况，所以需要枚举`dp[n][v](0<=v<=V)`,取其最大值才是最后的结果。

```
for (int i = 1; i <= n; i++) {
    for (int v = weight[i]; v <= V; v++) {
        dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i]] + value[i]);
    }
}
```

时间复杂度和空间复杂度都是O(nV), 其中空间复杂度可以再优化

***个人使用下面这种方法:***

上面虽然写的是`dp[i][v] = max(dp[i-1][v], dp[i-1][v-weight[i]] + value[i])`, 但是下面可以看到第i个数的下标是i-1, 

```java
// 初始化第0行第0列为0

// 遍历顺序：先遍历物品，再遍历背包容量
// 遍历物品, n为物品个数
for (int i = 1; i <= n; i++) {
    // 遍历背包容量
    for (int v = 1; v <= bagSize; v++) {
        // 其实就是当物品i的重量大于背包v的重量时,
        // 物品i无法放进背包中, 所以被背包内的价值依然和前面相同
        // 第i个数的下标是i-1
        if (weight[i - 1] > v) {
            dp[i][v] = dp[i - 1][v];
        } else {
            // 物品i可以放入背包,重量小于背包容量, 看要不要放入
            dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i - 1]] + value[i - 1]);
        }
    }
}
```

可以看到两者对背包容量的起始赋值不一致

二维的代码如下:

```java
import java.util.Arrays;

/**
 * 自己的写法
 * 时间和空间复杂度O(nV)
 */
public class Bag01Test_2 {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        testWeightBagProblem2(weight.length, weight, value, bagSize);
    }

    private static void testWeightBagProblem2(int n, int[] weight, int[] value, int bagSize) {

        // dp[i][v]: 前i个物品恰好组成容量为v的最大价值
        int dp[][] = new int[n + 1][bagSize + 1];

        // dp[0][]初始化, 选取0个物品的时候, 价值为0
        Arrays.fill(dp[0], 0);

        // dp[][0]初始化, 容量为0的时候, 价值为0
        for (int v = 0; v < bagSize; v++) {
            dp[v][0] = 0;
        }

        // 遍历
        // 前i个, [0, i-1], 第i-1个
        // 双for范围都是[1, n/V], 初始化过来, 第0行和第0列都初始化成0了
        // 从第1行和第1列开始遍历
        for (int i = 1; i <= n; i++) {
            for (int v = 1; v <= bagSize; v++) {
                // 重量大于v, 放不下
                if (weight[i - 1] > v) {
                    dp[i][v] = dp[i - 1][v];
                } else {
                    // 可以放下, 看要不要芳
                    dp[i][v] = Math.max(dp[i - 1][v], dp[i - 1][v - weight[i - 1]] + value[i - 1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= bagSize; v++) {
                System.out.print(dp[i][v] + " ");
            }
            System.out.println();
        }

        int maxValue = -1;
        for (int i = 0; i <= n; i++) {
            for (int v = 0; v <= bagSize; v++) {
                if (maxValue < dp[i][v]) {
                    maxValue = dp[i][v];
                }
            }
        }
        System.out.println(maxValue);
    }
}
```



### 一维 (滚动数组)

![img.png](img/img_12091501.png)

如图所示，注意到状态转移方程中计算`dp[i][v]`时总是只需要`dp[i-1][v]`左侧部分的数据(即只需要图中正上方与左上方的数据), 且当计算`dp[i+1][]`的部分时，`dp[i-1]`的数据又完全用不到了(只需要用到`dp[i][]`), 因此不妨可以直接开一个一维数组`dp[v]`(即把第一维省去), 

**枚举方向改变为i从1到n, v从V到0 ** ***(逆序!)***, 这样状态转移方程改变为:

```java
dp[v] = max(dp[v], dp[v-weight[i]] + value[i])
// 其中, 1<=i<=n,weight[i]<=v<=V
```

这样修改对应到图中可以这样理解：v的枚举顺序变为从**右往左**，`dp[i][v]`右边的部分为刚计算过的需要保存给下一行使用的数据，而`dp[i][v]`左上角的阴影部分为当前需要使用的部分。将这两者结合一下，即把`dp[i][v]`左上角和右边的部分放在一个数组里，每计算出一个`dp[i][v]`，就相当于把`dp[i-1][v]`抹消，因为在后面的运算中`dp[i-1][v]`再也用不到了。我们把这种技巧称为**滚动数组**

```C++
for (int i = 1; i <= n; i++) {
    // 倒序!!!
    for (int v = V; v >= weight[i]; v--) {
        dp[v] = Math.max(dp[v], dp[v - weight[i]] + value[i]);
    }
}
```
自己的理解:

#### 递推公式

在一维dp数组中，dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]。

dp[j]可以通过dp[j - weight[i]]推导出来，dp[j - weight[i]]表示容量为j - weight[i]的背包所背的最大价值。

dp[j - weight[i]] + value[i] 表示 容量为 j - 物品i重量 的背包 加上 物品i的价值。（也就是容量为j的背包，放入物品i了之后的价值即：dp[j]）

`dp[V]`有两个选择:

1. 取自己dp[j] 相当于 二维dp数组中的`dp[i-1][j]`，即不放物品i，
2. 取dp[j - weight[i]] + value[i]，即放物品i，

两者取max

#### 初始化

dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]，那么dp[0]就应该是0，因为背包容量为0所背的物品的最大价值就是0。

那么dp数组除了下标0的位置，初始为0，其他下标应该初始化多少呢？

看一下递归公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);

dp数组在推导的时候一定是取价值最大的数，如果题目给的价值都是正整数那么非0下标都初始化为0就可以了。

**这样才能让dp数组在递归公式的过程中取的最大的价值，而不是被初始值覆盖了**。

那么我假设物品价值都是大于0的，所以dp数组初始化的时候，都初始为0就可以了。

#### 遍历顺序

```C++
for(int i = 0; i < weight.size(); i++) { // 遍历物品
    for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
        dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
    }
}
```

务必注意!!!!: **遍历背包的顺序是不一样的**

二维dp遍历的时候，背包容量是从小到大，而一维dp遍历的时候，背包是从大到小。

**倒叙遍历是为了保证物品i只被放入一次！**。但如果一旦正序遍历了，那么物品0就会被重复加入多次！

举一个例子：物品0的重量weight[0] = 1，价值value[0] = 15

如果正序遍历

dp[1] = dp[1 - weight[0]] + value[0] = 15

dp[2] = dp[2 - weight[0]] + value[0] = 30

此时dp[2]就已经是30了，意味着物品0，被放入了两次，所以不能正序遍历。

为什么倒叙遍历，就可以保证物品只放入一次呢？

倒叙就是先算dp[2]

dp[2] = dp[2 - weight[0]] + value[0] = 15 （dp数组已经都初始化为0）

dp[1] = dp[1 - weight[0]] + value[0] = 15

所以从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。

---

**为什么二维dp数组历的时候不用倒叙呢？**

因为对于二维dp，`dp[i][j]`都是通过上一层即`dp[i - 1][j]`计算而来，本层的`dp[i][j]`并不会被覆盖！

（如何这里读不懂，大家就要动手试一试了，空想还是不靠谱的，实践出真知！）

**再来看看两个嵌套for循环的顺序，代码中是先遍历物品嵌套遍历背包容量，那可不可以先遍历背包容量嵌套遍历物品呢？**

不可以！

因为一维dp的写法，背包容量一定是要倒序遍历（原因上面已经讲了），如果遍历背包容量放在上一层，那么每个dp[j]就只会放入一个物品，即：背包里只放入了一个物品。

（这里如果读不懂，就在回想一下dp[j]的定义，或者就把两个for循环顺序颠倒一下试试！）

**所以一维dp数组的背包在遍历顺序上和二维其实是有很大差异的！**，这一点大家一定要注意。







#### 完整代码


```java
package leetcode.dp.bag;

/**
 * 一维dp数组
 * 空间复杂度 O(V)
 */
public class Bag01Test_3 {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int V = 4;
        testWeightBagProblem3(weight.length, weight, value, V);
    }
    private static void testWeightBagProblem3(int n, int[] weight, int[] value, int V) {
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[V + 1];

        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 0; i < n; i++) {
            for (int v = V; v >= weight[i]; v--) {
                // 此时dp[j]有两个选择，
                // 1.取自己dp[j] 相当于 二维dp数组中的dp[i-1][j]，即不放物品i，
                // 2.取dp[j - weight[i]] + value[i]，即放物品i，
                dp[v] = Math.max(dp[v], dp[v - weight[i]] + value[i]);
            }
        }
        //打印dp数组
        for (int v = 0; v <= V; v++) {
            System.out.print(dp[v] + " ");
        }
    }
}
```



### dp如何避免重复计算
在一开始暴力枚举每件物品放或者不放入背包时，其实忽略了一个特性：第i件物品放或者不放而产生的最大值是完全可以由前面i-1件物品的最大值来决定的，而暴力做法无视了这一点。
另外，01背包中的每个物品都可以看作一个阶段，这个阶段中的状态有`dp[i][0]`~
`dp[i][V]`，它们均由上一个阶段的状态得到。事实上，对能够划分阶段的问题来说，都可以尝试把阶段作为状态的一维，这可以使我们更方便地得到满足无后效性的状态。从中也可以得到这么一个技巧，如果当前设计的状态不满足无后效性，那么不妨把状态进行升维，即增加一维或若干维来表示相应的信息，这样可能就能满足无后效性了。










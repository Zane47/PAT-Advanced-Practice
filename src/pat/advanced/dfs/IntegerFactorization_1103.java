package pat.advanced.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * step1: 输入了N和P之后, 预先开一个list保存所有不超过N的n^P, 为了下标的对应, 把0也存进去
 * 例如: N=10, P=2, fac[0] = 0, fac[1] = 1, fac[2] = 4, fac[3] = 9
 *
 * step2: dfs
 * dfs从fac中选K个数字来组合成N, 这就是一个递归遍历的过程了
 * 每一个元素都可以选或者不选, 这就是两个分支了
 *
 * dfs的参数:
 * 1. 当前处理到几号位置, index.
 * 2. 当前选择了几个数字, nowK.
 * 3. 当前选择的数字之和,底数的P次方, nowSum
 * 4. 当前选择了数字的底数之和(第二标尺), nowFacSum
 * 第三标尺: 为了保证底数字典序最大, 我们对fac从后往前遍历
 *
 * __tempPath存储临时的底数序列
 * __resultPath存储最终的底数序列
 *
 * step3:
 * 递归边界和递归逻辑
 * step3.1: 递归边界:
 * nowK == K && nowSum == N的时候就说明找到了
 * 这个时候就要做对比了, 将现在序列的底数和和temp的底数和做对比更新(第二标尺)
 * 第三标尺我们在遍历的时候从后往前遍历就已经保证了, 首先被记录的肯定就是底数字典序最大的
 *
 *
 * step3.2: 递归逻辑: 为了让底数字典序最大, 对fac倒序遍历, 对每一个fac[i]都可以选择选或者不选,
 * 如果选择了, 那么nowSum+fac[index], nowK+1, 底数加入__temp, nowFacSum+index
 * 因为可以重复选择, 下一步还是对fac[i]进行选择, 但是其他参数要如上变化
 *
 * 如果咩有选择, 那么进入下一层递归: __fac[i-1], 其他的nowSum, nowK, facSum都不变
 *
 * 注意点:
 * 1.多方案判断是否更优的时候做法的时间复杂度最好是O(1), 否则容易超时.
 * 所以这里在dfs的参数中记录当前底数之和facSum, 避免找到一组解的时候计算一组
 * 2.同1, 不要找到一组解的时候才判断temp序列和ans序列的字典序关系(第三标尺)
 * 让index从大到小遍历, 这样子就可以直接满足
 *
 */
public class IntegerFactorization_1103 {

    private static List<Integer> __fac;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N <= 400
        int N = sc.nextInt();
        // K <= N
        int K = sc.nextInt();
        // (1, 7] -> [2, 7]
        int P = sc.nextInt();

        // N = 以K个数字为底, 指数为P的和. 这K个数字非递减, 可以相等
        // 如果有多个: 第二标尺: 底数和最大. 如果还相等, 第三标尺: 底数序列字典和最大

        // 初始化__fac
        __fac = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (Math.pow(i, P) > N) {
                break;
            }
            __fac.add((int) Math.pow(i, P));
        }


        dfs();





    }

    /**
     *
     * dfs遍历
     *
     * @param index 当前处理到几号位置
     * @param nowK  当前选择了几个数字
     * @param nowSum 当前选择的数字之和,底数的P次方
     * @param nowFacSum 当前选择了数字的底数之和(第二标尺)
     */
    private static void dfs(int index, int nowK, int nowSum, int nowFacSum) {



    }
}

package pat.advanced.tree.heap;


/**
 * 大顶堆为例, 结合< 算法笔记 > P335的流程图来看
 * create, insert, delete
 * <p>
 * downAdjust, upAdjust
 */
public class HeapDemo {

    private static final int MAXN = 100;

    // 定义堆数组
    private static int[] __heap;

    // 元素个数
    private static int __n;

    public static void main(String[] args) {
        __heap = new int[MAXN];
        __heap[1] = 85;
        __heap[2] = 55;
        __heap[3] = 82;
        __heap[4] = 57;
        __heap[5] = 68;
        __heap[6] = 92;
        __heap[7] = 99;
        __heap[8] = 98;
        __heap[9] = 66;
        __heap[10] = 56;

        __n = 10;

        heapSort();
    }

    /**
     * 堆排序
     */
    private static void heapSort() {
        // 建大顶堆
        createHeap();

        // 倒着枚举, 直到堆中只有一个元素
        for (int i = __n; i > 1; i--) {
            // 交换heap[i]和栈顶
            swap(i, 1);
            // 调整栈顶
            downAdjust(1, __n - 1);
        }
        for (int i = 1; i < __n; i++) {
            System.out.println(__heap[i]);
        }
        int a = 0;
    }

    /**
     * 对heap数组在范围[low, high]中向下调整
     *
     * @param low  要调整结点的数组下标
     * @param high 一般为堆的最后一个结点下标
     */
    private static void downAdjust(int low, int high) {
        // cur 为要调整的结点坐标
        int cur = low;
        // child先初始化为左孩子
        int child = low * 2;

        // 当存在孩子结点的时候
        while (child <= high) {
            // ------------------------ 将child更新为值大的孩子 ------------------------
            // 因为child初始化为左孩子
            // 右孩子存在 && 右孩子的权值 > 左孩子的权值 -> 更新cur为右孩子
            if (child + 1 <= high && __heap[child + 1] > __heap[child]) {
                // 更新child存储右孩子坐标
                child = child + 1;
            }

            // ------------------------ 做调整 ------------------------
            if (__heap[cur] < __heap[child]) {
                // 下沉
                // 交换最大权值的孩子和欲调整的结点i大小
                swap(cur, child);

                // cur结点此时调整到了之前的child的位置
                // 要保持cur为欲调整结点, child为cur的左孩子, 进入下一轮
                cur = child;
                child = cur * 2;
            } else {
                // 孩子的权值比欲调整结点的权值小, 不需要调整
                break;
            }

        }
    }

    /**
     * 完全二叉树的叶子结点个数为upbound(n/2),
     * 因此数组下标在[1, upbound(n/2)]范围内的结点都是非叶子结点。
     * <p>
     * 于是可以从downbound(n/2)号位置开始倒着枚举结点，对每个遍历到的结点i进行[i,n]范围的调整。
     * <p>
     * <p>
     * **为什么要倒着枚举呢?**
     * 这是因为每次调整完一个结点后，当前子树中权值最大的结点就会处在根结点的位置，
     * 这样当遍历到其父亲结点时，就可以直接使用这个结果。
     * 也就是说，这种做法**保证每个结点都是以其为根结点的子树中的权值最大的结点**
     */
    private static void createHeap() {
        for (int i = __n / 2; i >= 1; i--) {
            downAdjust(i, __n);
        }
    }

    /**
     * 删除堆顶元素
     */
    private static void deleteTop() {
        // 用最后一个元素覆盖堆顶元素, 元素个数-1
        __heap[1] = __heap[__n--];

        // 向下调整堆顶元素
        downAdjust(1, __n);
    }

    /**
     * 添加元素
     * 元素放到数组最后, 然后向上调整
     * <p>
     * 向上调整: 欲调整结点与父亲结点比较权值,
     * 如果权值大于父亲结点, 就交换该结点与父亲结点
     * 反复比较, 直到到达堆顶或者比父亲结点小
     *
     * @param x
     */
    private static void insert(int x) {
        // 数组个数+1, 在末尾插入新增元素
        __heap[++__n] = x;
        // 向上调整
        upAdjust(1, __n);
    }

    /**
     * 对heap数组在[low，high]范围进行向上调整
     * <p>
     * 向上调整: 欲调整结点与父亲结点比较权值,
     * 如果权值大于父亲结点, 就交换该结点与父亲结点
     * 反复比较, 直到到达堆顶或者比父亲结点小
     *
     * @param low  low一般设置为1，堆顶位置
     * @param high high表示欲调整结点的数组下标
     */
    private static void upAdjust(int low, int high) {
        // cur设置为欲比较结点
        int cur = high;
        // 父亲结点
        int father = cur / 2;

        // 父亲结点下标在[low, high]范围内
        while (father >= low) {
            // 父亲结点的权值 < 欲调整结点的权值
            if (__heap[father] < __heap[cur]) {
                // 欲比较的结点与父亲交换位置
                swap(father, cur);
                // 欲比较的结点更新到父亲上去
                cur = father;
                father = cur / 2;
            } else {
                // 父亲结点的权值 > 欲调整结点的权值
                // 无需调整了
                break;
            }
        }
    }

    /**
     * 交换元素
     */
    private static void swap(int x, int y) {
        int temp = __heap[x];
        __heap[x] = __heap[y];
        __heap[y] = temp;
    }
}

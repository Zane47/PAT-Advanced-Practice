package pat.advanced.graph;

/**求图最短路径dijkstra
 * Dijkstra算法使用了广度优先搜索解决赋权有向图或者无向图的单源最短路径问题，
 * 算法最终得到一个最短路径树。该算法常用于路由算法或者作为其他图算法的一个子模块。
 *
 * 该算法的时间复杂度是n的平方，可以使用堆优化。
 *
 * 但是，要注意一点，Dijkstra算法只能适用于权值为正的情况下；如果权值存在负数，则不能使用。
 *
 * Dijkstra算法的思想
 * 1.设置两个顶点集S和T，集合S中存放已经找到最短路径的顶点，集合T中存放着当前还未找到最短路径的顶点；
 *
 * 2.初始状态下，集合S中只包含源点V1，T中为除了源点之外的其余顶点，
 *   此时源点到各顶点的最短路径为两个顶点所连的边上的权值，如果源点V1到该顶点没有边，则最小路径为无穷大；
 *
 * 3.从集合T中选取到源点V1的路径长度最短的顶点Vi加入到集合S中；
 *
 * 4.修改源点V1到集合T中剩余顶点Vj的最短路径长度。
 *   新的最短路径长度值为Vj原来的最短路径长度值与顶点Vi的最短路径长度加上Vi到Vj的路径长度值中的较小者；
 *
 * 5.不断重复步骤3、4，直至集合T的顶点全部加入到集合S中。
 * ————————————————
 * 版权声明：本文为CSDN博主「Yngz_Miao」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_38410730/article/details/79587768
 *
 *
 *
 */

public class dijkstra {

    public static void main(String[] args) {
        // Test test = new Test();
        int MAX=Integer.MAX_VALUE-10000;
        int[][] weight={
            {0,1,12,MAX,MAX,MAX},
            {MAX,0,9,3,MAX,MAX},
            {MAX,MAX,0,MAX,5,MAX},
            {4,0,13,15,MAX,MAX},
            {MAX,MAX,MAX,MAX,0,4},
            {MAX,MAX,MAX,MAX,MAX,0}
        };
        int start=0;  //选择出发点
        // int[] sp = test.getShortPath(weight,start);
        // System.out.println(Arrays.toString(sp));
    }








}

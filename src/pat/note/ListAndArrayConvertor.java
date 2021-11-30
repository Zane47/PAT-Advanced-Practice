package pat.note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数组和list互转
 */
public class ListAndArrayConvertor {
    public static void main(String[] args) {
        // 1. list转数组
        // 1.1
        List<String> testList = new ArrayList() {{
            add("aa");
            add("bb");
            add("cc");
        }};
        //初始化需要得到的数组
        String[] array = new String[testList.size()];

        //使用for循环得到数组
        for (int i = 0; i < testList.size(); i++) {
            array[i] = testList.get(i);
        }
        //打印数组
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        // 1.2 使用toArray()方法
        // 需要特别注意，不能这样写：
        ArrayList<String> list = new ArrayList<>();
        String strings[] = (String[]) list.toArray();

        // 这样写编译没有什么问题，但是运行时会报ClassCastException，
        // 这是因为Java中允许向上和向下转型，
        // 但是这个转型是否成功是根据Java虚拟机中这个对象的类型来实现的。
        // Java虚拟机中保存了每个对象的类型。
        // 而数组也是一个对象。数组的类型是java.lang.Object。
        // 把java.lang.Object转换成java.lang.String是显然不可能的事情，
        // 因为这里是一个向下转型，而虚拟机只保存了这是一个Object的数组，
        // 不能保证数组中的元素是String的，所以这个转型不能成功。
        // 数组里面的元素只是元素的引用，不是存储的具体元素，
        // 所以数组中元素的类型还是保存在Java虚拟机中的。
        //————————————————
        //版权声明：本文为CSDN博主「zhaojiaxing0216」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        //原文链接：https://blog.csdn.net/zjx2016/article/details/78273192

        // 因此正确的方法是这样的：
        //使用toArray(T[] a)方法
        String[] array2 = testList.toArray(new String[testList.size()]);

        //打印该数组
        for(int i = 0; i < array2.length; i++){
            System.out.println(array2[i]);
        }

        // 2. 数组转List
        // 2.1 for 循环
        //需要转换的数组
        String[] arrays = new String[]{"aa","bb","cc"};
        //初始化list
        List<String> stringList = new ArrayList<String>();
        //使用for循环转换为list
        for(String str : arrays){
            stringList.add(str);
        }
        //打印得到的list
        System.out.println(stringList);


        // 2.2使用asList()
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arrays));

        // 2.3使用asList(), 同方法二一样使用了asList()方法。 推荐使用方法二
        // 这不是最好的，因为asList()返回的列表的大小是固定的。
        // 事实上，返回的列表不是java.util.ArrayList，而是定义在java.util.Arrays中一个私有静态类。
        // 我们知道ArrayList的实现本质上是一个数组，
        // 而asList()返回的列表是由原始数组支持的固定大小的列表。
        // 这种情况下，如果添加或删除列表中的元素，程序会抛出异常UnsupportedOperationException。
        List<String> list2 = Arrays.asList(arrays);


        // 2.4 使用Collections.addAll()
        List<String> list3 = new ArrayList<String>(arrays.length);
        Collections.addAll(list3, arrays);

        // ps:
        // 数组转集合可以用stream
        //八种基本数据类型的话需要用到boxed转换一下
        int[] nums1 = new int[4];
        List<Integer> collect = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        System.out.println(collect);
        //非八种基本数据类型直接转换
        String[] nums2 = new String[4];
        List<String> collect1 = Arrays.stream(nums2).collect(Collectors.toList());
        System.out.println(collect1);


    }


}

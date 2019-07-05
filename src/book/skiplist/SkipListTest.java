package book.skiplist;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class SkipListTest {
    public static void main(String[] args){
        SkipList skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(5);
        skipList.insert(7);
        skipList.insert(1);
        skipList.find(5);
        skipList.printAll();

        skipListMapshow();

        SkipListNode<Integer> node1 = new SkipListNode<Integer>(2,2);
        SkipListNode<String> node2 =new SkipListNode<String>(2,"2");
        System.out.println(node1.equals(node2));
    }


    public static void skipListMapshow(){
        Map<Integer,String> map= new ConcurrentSkipListMap<>();

        map.put(1, "1");
        map.put(23, "23");
        map.put(3, "3");
        map.put(2, "2");

        /*输出是有序的，从小到大。
         * output
         * 1
         * 2
         * 3
         * 23
         *
         */
        for(Integer key : map.keySet()){
            System.out.println(map.get(key));
        }
    }

}

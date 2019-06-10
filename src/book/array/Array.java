package book.array;

import java.util.ArrayList;
import java.util.List;

public class Array {
    private int[] items;
    //容量
    private int capacity ;

    //实际存放的元素个数
    private int count;
    public Array(int capacity){
        items = new int[capacity];
        this.capacity = capacity;
    }
    public boolean insert(int idx,int val){
        if(count==capacity){
            System.out.println("数组已满");
            return false;
        }
        if(idx<0 || idx>count){
            System.out.println("位置不合法");
            return false;

        }
        //平均时间复杂度O(n)
//        for(int i=count;i>idx;i--){
//            items[i] = items[i-1];
//        }
        //时间复杂度O(1)
        items[count] = items[idx];
        items[idx] = val;
        count++;
        return true;
    }
    public int find(int idx){
        if(idx<0 || idx>=count){
            System.out.println("位置不合法");
            return -1;
        }
        return items[idx];
    }

    /**
     *
     * @param idx
     * @return
     */
    public boolean delete(int idx){
        if(idx<0 || idx>=count){
            System.out.println("位置不合法");
            return false;

        }
        //平均时间复杂度O(n)
        for(int i = idx+1;i<count;i++){
            items[i-1] = items[i];
        }


        count--;
        return true;
    }

    public void printAll(){
        for (int i = 0; i < count; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args){
        Array array = new Array(5);
        array.printAll();
        array.insert(0,3);
        array.insert(6,6);
        array.insert(0,4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        array.printAll();

        array.delete(0);
        array.delete(6);
        array.delete(5);
        array.printAll();


        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.set(4,3);

    }
}

package book.heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 最大堆
 * @param <T>
 */
public class MaxHeap<T extends Comparable> {
    private T[] nums;
    private int count;//元素个数
    private int size;
    public MaxHeap(int size){
        this.nums = (T[])new Comparable[size+1];
        this.size = size;
    }

    public void insert(T data){
        if(count>=size) return;
        ++count;
        nums[count] = data;
        int idx = count;
        while(idx/2>=0 &&  nums[idx/2]!=null && data.compareTo(nums[idx/2])>0){
            nums[idx] = nums[idx/2];
            idx = idx/2;
        }
        nums[idx] = data;
    }

    public T removeTop(){
        T val = nums[1];
        nums[1] = nums[count];
        nums[count] = null;
        count--;
        //从上往下堆化
        int idx = 1;
        T data = nums[1];
        while(idx<=count){
            int switchPos = idx;
            if(2*idx<=size && data.compareTo(nums[2*idx])<0){
                switchPos = 2*idx;
            }
            if(2*idx+1<=size && nums[switchPos].compareTo(nums[2*idx+1])<0){
                switchPos = 2*idx+1;
            }
            if(switchPos==idx){
                break;
            }else{
                nums[idx] = nums[switchPos];
                idx = switchPos;
            }
        }
        return val;
    }

    public void levelPrint(){
        Queue<Integer> queue = new LinkedList<>();
        if(nums[1]!=null){
            queue.offer(1);
        }
        List<List<T>> valueList = new ArrayList<List<T>>();
        while (!queue.isEmpty()){
            int size = queue.size();
            List<T> list = new ArrayList<T>();
            for(int i=0;i<size;i++){
                int idx = queue.poll();
                if(idx<=count && nums[idx]!=null){
                    list.add(nums[idx]);
                    queue.offer(2*idx);
                    queue.offer(2*idx+1);
                }

            }
            if(!list.isEmpty())
                valueList.add(list);
        }
        for(List<T> list : valueList){
            System.out.println(list);
        }
    }
}

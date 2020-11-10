package homework.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个最小堆
 * @param <T>
 */
public class Heap2<T extends Comparable> {
    //容量
    private int len;
    //元素个数
    private int size;

    private Object[] queue;
    public Heap2(int size){
        len = size;
        queue = new Object[size];
    }

    public T peek(){
        return this.size>=0?(T)queue[0]:null;
    }

    /**
     * 删除一个元素
     */
    public T poll() {
        if(size == 0){
            return null;
        }
        T r = (T) queue[0];
        queue[0] = queue[size-1];
        queue[size-1] = null;
        size--;
        shiftDown(0);
        return r;
    }

    /**
     * 向下堆化
     * @param index
     */
    private void shiftDown(int index) {
        while(index<size){
            int leftIndex = 2*index+1;
            int minIndex = index;
            T minValue = (T)queue[index];
            if(leftIndex<size && ((Comparable)queue[index]).compareTo(queue[leftIndex])>0){
                minValue = (T) queue[leftIndex];
                minIndex = leftIndex;
            }
            int rightIndex = 2*index+2;
            if(rightIndex<size && minValue.compareTo(queue[rightIndex])>0){
                minIndex = rightIndex;
                minValue = (T)queue[rightIndex];
            }
            if(minIndex == index){
                break;
            }else{
                switchData(minIndex,index);
                index = minIndex;
            }
        }
    }

    /**
     * 添加一个元素
     * @param data
     */
    public void offer(T data) {
        if(data==null){
            throw new NullPointerException();
        }
        if(size==len){
            throw new RuntimeException("空间已满");
        }
        queue[size] = data;
        shiftUp(size);
        size++;
    }

    /**
     * 向上堆化
     * 对于下标i，左子节点是2i+1,右子节点是2i+2
     * @param index
     */
    private void shiftUp(int index) {
        while(index>0){
            int parent = (index-1)>>>1;
            if(((Comparable)queue[parent]).compareTo(queue[index])>0){
                switchData (parent,index);
                index = parent;
            }else{
                break;
            }
        }
    }

    private void switchData(int i1, int i2) {
        Object tmp = queue[i1];
        queue[i1] = queue[i2];
        queue[i2] = tmp;
    }

    public void topMaxK(T data){
        if(this.size < this.len){
            this.offer(data);
        }else{
            if(data.compareTo(this.peek())>0){
                this.poll();
                this.offer(data);
            }
        }
    }
    public List<T> topMaxKList(){
        List<T> list = new ArrayList<>();
        for(int i=0;i<size;i++){
            list.add((T)queue[i]);
        }
        return list;
    }
    public static void main(String[] args){
        Heap2<Integer> heap2 = new Heap2<>(10);
        heap2.offer(10);
        heap2.offer(8);
        heap2.offer(6);
        heap2.offer(12);

        System.out.println(heap2.poll());
        System.out.println(heap2.poll());
        System.out.println(heap2.poll());
        System.out.println(heap2.poll());
        System.out.println(heap2.poll());

        int k = 3;
        Heap2<Integer> heap3 = new Heap2<>(k);
        heap3.topMaxK(12);
        heap3.topMaxK(17);
        heap3.topMaxK(10);
        heap3.topMaxK(13);
        heap3.topMaxK(5);
        heap3.topMaxK(28);
        System.out.println(heap3.topMaxKList());
    }


}

package homework2.heap;

public class Heap<T extends Comparable> {
    //堆容量
    private int size;
    //元素个数
    private int count;
    private T[] array;
    public Heap(int size){
        this.size = size;
        array = (T[])new Comparable[size];
    }
    /**
     * 插入元素
     * @param data
     */
    public void insert(T data){
        if(this.count >= this.size) throw new RuntimeException("空间不足");
        array[count] = data;
        count++;
        int i = count - 1;
        while((i-1)/2 >=0 && array[(i-1)/2].compareTo(array[i]) < 0){
            swap(array, i,(i-1)/2);
            i = (i-1)/2;
        }
    }

    private void swap(T array[], int i,int j){
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * 删除堆顶元素
     * @return
     */
    public T remove(){
        if(count == 0) throw new IllegalArgumentException("没有可以删除的元素");
        T r = array[0];
        array[0] = array[count-1];
        array[count - 1] = null;
        count--;
        heapify(array,0,count-1);
        return r;
    }


    /**
     * 返回堆顶元素
     * @return
     */
    public T getTop(){
        return count>0? array[0] :null;
    }


    /**
     * 从上到下堆化
     * @param a
     * @param s
     *          从下标=s的元素开始堆化
     * @param max
     *          下标最大值
     */
    private void heapify(T[] a, int s, int max) {
        int i = s;
        while(true){
            int maxPos = i;
            if(2*i+1<=max && a[i].compareTo(a[2*i+1])<0){
                maxPos = 2*i+1;
            }
            if(s*i+2<=max && a[maxPos].compareTo(a[2*i+2])<0){
                maxPos = 2*i  + 2;
            }
            if(maxPos == i) break;
            swap(a, maxPos, i);
        }
    }
}

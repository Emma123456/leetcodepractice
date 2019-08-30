package homework.array;

/**
 * 一个大小固定的有序的数组
 */
public class SortedArray<T extends Comparable> {
    //存储数据
    private T[] data;

    //元素个数
    private int size;
    //容量
    private int n;

    public SortedArray(){
        this(10);
    }

    public SortedArray(int n){
        this.n = n;
        data = (T[])new Comparable[n];
    }

    /**
     * 插入元素
     * @param element
     */
    public void add(T element){
        checkCapacity();
        int idx = size;
        for(int i=0;i<size;i++){
            if(element.compareTo(data[i])<0){
                idx = i;
                break;
            }
        }
        for(int i=size-1;i>=idx;i--){
            data[i+1] = data[i];
        }
        data[idx] = element;
        size++;
    }

    /**
     * 检测容量
     */
    private void checkCapacity() {
        if(this.size== this.n){
            throw new RuntimeException("空间不足");
        }
    }

    /**
     * 返回数组大小
     * @return
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 删除下标idx的元素
     * @param idx
     * @return
     */
    public T delete(int  idx){
        checkIdx(idx);
        T tmp = data[idx];
        for(int i=idx+1;i<size;i++){
            data[i-1] = data[i];
        }
        size --;
        return tmp;
    }

    /**
     * 返回下标idx的元素
     * @param idx
     * @return
     */
    public T get(int idx){
        checkIdx(idx);
        return data[idx];
    }

    private void checkIdx(int idx){
        if(idx<0 || idx>=n){
            throw new IllegalArgumentException(idx+" 不是有效的下标位置");
        }
    }
}

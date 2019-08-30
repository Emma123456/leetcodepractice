package homework.array;

/**
 * 一个支持动态扩容的数组
 */
public class GenericArray<T> {
    //存储数据
    private T[] data;

    //元素个数
    private int size;
    //容量
    private int n;

    public GenericArray(){
        this(10);
    }

    public GenericArray(int n){
        this.n = n;
        data = (T[])new Object[n];
    }

    /**
     * 插入元素
     * @param element
     */
    public void add(T element){
        checkCapacity();
        data[size++] = element;
    }

    public void add(int idx,T element){
        checkCapacity();
        checkIdx(idx);
        for(int i=size-1;i>idx;i--){
            data[i+1] = data[i];
        }
        data[idx] = element;
        size++;
    }

    /**
     * 检查容量
     */
    private void checkCapacity() {
        if(size==n){
            T[] tmp = (T[])new Object[2*size];
            System.arraycopy(data,0,tmp,0,size);
            n = tmp.length;
            data = tmp;
        }
    }

    private void checkIdx(int idx){
        if(idx<0 || idx>=n){
            throw new IllegalArgumentException(idx+" 不是有效的下标位置");
        }
    }

    /***
     * 在某个位置插入元素
     * @param idx
     * @param element
     */
    public void set(int idx,T element){
        checkIdx(idx);
        data[idx] = element;
    }

    /**
     * 删除并且返回下标idx的元素
     * @param idx
     * @return
     */
    public T delete(int idx){
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

    /**
     * 获取数组元素个数
     * @return
     */
    public int getSize(){
        return this.size;
    }
}

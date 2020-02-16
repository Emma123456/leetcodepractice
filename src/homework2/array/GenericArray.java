package homework2.array;

/**
 * 一个支持动态扩容的数组
 * @param <T>
 */
public class GenericArray<T> {
    //存放元素的数组
    private T[] data;
    //已经插入元素的个数
    private int size;


    /**
     * 初始化大小
     * @param size
     */
    public GenericArray(int size){
        data = (T[])new Object[size];
    }

    public GenericArray(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    /**
     * 添加数据
     * @param n
     */
    public void add(T n){
        this.add(size,n);
    }

    /**
     * 在index位置添加元素t，其他元素向后移动
     * @param index
     * @param t
     */
    public void add(int index, T t){
        if(index>size){
            throw new IllegalArgumentException(index+"不是一个有效的索引位置");
        }
        checkCapacity();
        moveArray(index);
        data[index] = t;
        size++;
    }

    /**
     * 将数组从index的元素向右迁移一位
     * @param index
     */
    private void moveArray(int index) {
        for(int i =size-1;i>=index;i--){
            data[i+1] = data[i];
        }
    }

    /**
     * 检查容量
     */
    private void checkCapacity() {
        if(this.size == data.length){
            T[] tmp = (T[])new Object[this.size << 1];
            for(int i=0;i<size;i++){
                tmp[i] = data[i];
            }
            this.data = tmp;
        }
    }

    /**
     * 删除位置index的元素
     * @param index
     * @return
     */
    public T delete(int index){
        checkIdx(index);
        return null;
    }

    /**
     * 修改位置index的元素为t
     * @param index
     * @param t
     */
    public void set(int index ,T t){
        checkIdx(index);
        data[index] = t;
    }

    /**
     * 返回位置index的元素
     * @param index
     * @return
     */
    public T get(int index){
        checkIdx(index);
        return  data[index];
    }


    private void checkIdx(int index){
        if(index>=size){
            throw new IllegalArgumentException(index+"不是一个有效的索引位置");
        }
    }

    public static void main(String[] args){
        int r = 2<<1;
        System.out.println(r);
    }
}

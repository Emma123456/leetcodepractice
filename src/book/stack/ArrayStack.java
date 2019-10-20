package book.stack;

/**
 * 数组实现的栈，在一端插入和删除，那就对数组的最后一个元素操作吧
 */
public class ArrayStack {
    //存储数据的值
    private int[] items;
    //容量
    private int n;
    //存储元素个数
    private int size;

    /**
     *
     * @param capacity
     *          容量
     */
    public ArrayStack(int capacity){
        this.n = capacity;
        items = new int[n];
    }

    /**
     * 添加元素，如果还有空间则添加成功，返回true。否则返回false。
     * @param val
     * @return
     */
    public boolean push(int val){
        if(this.size >=n) return false;
        items[size++] = val;
        return true;
    }

    /**
     * 栈内元素个数
     * @return
     */
    public int size(){
        return this.size;
    }

    /**
     * 删除栈顶元素。如果当前栈内没有元素，则抛出异常。在调用pop之前先调用size()吧。
     * @return
     */
    public int pop(){
        if(size==0) throw new IllegalArgumentException("栈目前没有任何元素，不能弹出元素");
        return items[--size];
    }
}

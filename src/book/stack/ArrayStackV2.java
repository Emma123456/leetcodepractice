package book.stack;

public class ArrayStackV2 {
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
    public ArrayStackV2(int capacity){
        this.n = capacity;
        items = new int[n];
    }

    /**
     * 添加元素
     * @param val
     */
    public void push(int val){
        if(this.size >=n) {
            grow();
        }
        items[size++] = val;
    }

    private void grow() {
        int newSize = 2*size;
        int[] newItmes = new int[newSize];
        System.arraycopy(this.items,0,newItmes,0,size);
        this.n = newItmes.length;
        this.items = newItmes;
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

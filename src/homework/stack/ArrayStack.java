package homework.stack;

/**
 * 顺序栈.栈：后进先出，用数组实现，在数组末尾添加、删除元素
 */
public class ArrayStack {
    private int[] items;
    //容量
    private int n;
    //元素个数
    private int size;

    public ArrayStack(int n){
        items = new int[n];
        this.n = n;
    }

    public ArrayStack(){
        this(10);
    }

    /**
     * 入栈
     */
    public void push(int value){
        checkCapacity();
        items[size++] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(isEmpty()) return -1;
        return items[--size];
    }

    public void printAll(){
        for(int i=0;i<size;i++){
            System.out.print(items[i]+"\t");
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return size==0;
    }


    /**
     * 检查容量
     */
    private void checkCapacity() {
        if(size==n){
            int[] tmp = new int[2*size];
            System.arraycopy(items,0,tmp,0,size);
            n = tmp.length;
            items = tmp;
        }
    }
}

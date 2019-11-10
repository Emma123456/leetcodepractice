package homework.queue;

/***
 * 顺序队列
 */
public class ArrayQueue {

    //存放元素
    private String[] items;
    //容量
    private int size;
    //队列头部
    private int head;
    //队列尾部
    private int tail;
    public ArrayQueue(int size){
        items = new String[size];
        this.size = size;

    }

    /**
     * 入队：在队尾插入数据
     * @param value
     * @return
     */
    public boolean enqueue(String value){
        if(isFull()) return false;
        items[tail++] = value;
        return true;
    }

    /**
     * 出队：从对头删除数据
     * @return
     */
    public String dequeue(){
        if(isEmpty()) return null;
        return items[head++];
    }


    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return head == tail;
    }

    /**
     * 判断队列是不是满了
     * @return
     */
    private boolean isFull(){
        return tail == size;
    }
}

package book.queue;

/**
 * 基于数组实现的循环队列
 */
public class RoundRobinArray {
    private String[] items;
    private int n = 0;
    private int head;
    private int tail;
    public RoundRobinArray(int capacity){
        this.n = capacity;
        items = new String[this.n];
        head = tail = 0;
    }

    /**
     * 在队尾插入一个元素
     * @param value
     * @return
     */
    public  boolean enqueue(String value){
        if((tail+1)%n==head){
            return false;
        }
        items[tail] = value;
        tail = (tail+1)%n;
        return true;
    }

    /**
     * 出队：从对头删除一个元素
     * @return
     */
    public String dequeue(){
        if(head==tail) return null;
        String ret = items[head];
        head++;
        return ret;
    }
}

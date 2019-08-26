package book.queue;

public class ArrayQueue {
    private String[] items;
    private int n = 0;
    private int head;
    private int tail;
    public ArrayQueue(int capacity){
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
        if(tail==n){
            if(head==0) return false;
            for(int i=head;i<tail;i++){
                items[i-head] = items[i];
            }
            tail = tail-head;
            head = 0;
        }
        items[tail] = value;
        tail++;
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

package homework.queue;

public class CircularDeque641 {
    public static void main(String[] args){
        MyCircularDeque circularDeque = new MyCircularDeque(8); // set the size to be 3

        circularDeque.insertFront(5);
        System.out.println(circularDeque.getFront());
        System.out.println(circularDeque.isEmpty());
        circularDeque.insertLast(2);
        circularDeque.insertFront(3);
        circularDeque.insertFront(4);
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());
        circularDeque.insertFront(4);
        System.out.println(circularDeque.getFront());
    }



}

class MyCircularDeque {
    private int[] items;
    //指向队列的第一个元素
    private int head;
    //指向队列的最后一个元素的下一个位置
    private int tail;
    private int n;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        n = k+1;
        items = new int[n];

    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()) return false;
        if(isEmpty()){
            //初始化的时候，head和tail指向相同的位置，所以需要特殊处理
            items[head] = value;
            tail = head+1;
        }else{
            //当指针做减1操作的时候，注意越界
            head = (head-1+n)%n;
            items[head]=value;
        }
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()) return false;
        items[tail] = value;
        tail = (tail+1)%n;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()) return false;
        head = (head+1)%n;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()) return false;
        tail = (tail-1+n)%n;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty()?-1:items[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty()?-1:items[(tail-1+n)%n];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return head==tail;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (tail+1)%n==head;
    }
}

package book.queue;

public class QueueBasedOnLinkedList {
    private Node head;
    private Node tail;

    public QueueBasedOnLinkedList(){
        head = new Node("dummy");
        tail = head;

    }
    /**
     * 入队
     * @param value
     */
    public void enqueue(String value){
        if(tail ==null){
            tail = new Node(value);
            head = tail;

        }else{
            tail.next = new Node(value);
            tail = tail.next;
        }

    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if(head==null) return null;
        String res = head.data;
        head = head.next;
        if(head == null){
            tail = null;
        }
        return res;
    }

    class Node{
        private String data;
        private Node next;
        public Node(String data){
            this.data = data;
        }
    }
}

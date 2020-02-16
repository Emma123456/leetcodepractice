package homework2.linkedlist;

/**
 * 单向链表，支持添加、删除操作
 */
public class SinglyLinkedList {
    private Node head;

    /**
     * 在链表头部插入一个元素
     * @param value
     */
    public void insertToHead(int value){
        if(head == null){
            head = new Node(value);
        }else{
            Node node = new Node(value);
            node.next = head;
            head = node;
        }
    }

    /**
     * 在链表尾部插入一个元素
     * @param value
     */
    public void insertToTail(int value){
        if(head == null){
            head = new Node(value);
        }else{
            Node node = head;
            while(node.next!=null){
                node = node.next;
            }
            node.next = new Node(value);
        }
    }

    /**
     * 删除某个元素
     * @param value
     */
    public void delete(int value){
        Node node = head;
        Node pre = null;
        while(node !=null && node.value != value){
            pre = node;
            node = node.next;
        }
        if(node !=null){
            if(pre == null){
                head = null;
            }else{
                pre.next = node.next;
            }
        }
    }

    /**
     * 遍历、打印链表
     */
    public void print(){
        Node node = head;
        System.out.println();
        while(node!=null){
            System.out.print(node.value +"\t");
            node = node.next;
        }
        System.out.println();
    }


    /**
     * 单链表逆转
     */
    public void reverse(){
        Node node = head;
        Node pre = null;
        while(node!=null){
            Node tmp = node.next;
            node.next = pre;
            pre = node;
            node = tmp;
        }
        head = pre;
    }

    public int  findMiddle(){
        if(head == null){
            throw new RuntimeException("列表为空，不支持此操作");
        }
        Node slow = head,fast = head;
        while(fast!=null  && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.value;
    }

    class Node{
        int value;
        Node next;

        Node(int value){
            this.value = value;
        }
    }
}

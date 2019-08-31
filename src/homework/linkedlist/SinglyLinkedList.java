package homework.linkedlist;

/**
 * 单向链表，支持添加、删除操作
 * 支持反转、合并两个有序链表，求中间节点
 */
public class SinglyLinkedList {
    //头结点
    private Node head;
    /**
     * 添加到链表末尾
     * @param data
     */
    public void insertToTail(int data){
        Node newNode = new Node(data);
        if(head==null){
            head = newNode;
        }else{
            Node node = head;
            while(node.next!=null){
                node = node.next;
            }
            node.next = newNode;
        }
    }

    /***
     * 在p节点后插入数据
     * @param p
     * @param value
     */
    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value);
        Node tmp = null;
        if(p.next!=null){
            newNode.next = p.next.next;
        }
        p.next = newNode;
    }


    /**
     * 删除某个节点
     * @param p
     */
    public void deleteByNode(Node p) {
        if(p==null || head==null) return;
        if(p==head){
            head = head.next;
        }else{
            Node node = head;
            while(node!=null && node.next !=p){
                node = node.next;
            }
            if(node!=null){
                node.next = node.next.next;
            }
        }

    }

    public void deleteByValue(int value) {
        Node node = find(value);
        deleteByNode(node);
    }

    public Node find(int value){
        Node node = head;
        while(node!=null && node.data!=value){
            node = node.next;
        }
        return node;
    }


    public void printAll() {
        Node node = head;
        while(node!=null){
            System.out.print(node.data+"\t");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 单链表反转
     */
    public void reverse(){
        Node current = head;
        Node pre = null;
        while(current!=null){
            Node next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        head = pre;
    }

    /**
     * 查找中间节点,当长度为4的时候，返回的是第3个节点。
     * @return
     */
    public Node findMiddle(){
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 检测是否有环
     * 快慢指针，如果相遇在有环。
     * @return
     */
    public  boolean checkCircle(){
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }
}

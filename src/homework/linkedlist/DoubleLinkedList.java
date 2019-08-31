package homework.linkedlist;

/**
 * 双向链表，支持新增、删除
 */
public class DoubleLinkedList {
    private Node head;
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
            newNode.pre = node;
        }
    }

    /***
     * 在p节点后插入数据
     * @param p
     * @param value
     */
    public void insertAfter(Node p, int value) {
        if(p==null || head ==null) return;
        Node newNode = new Node(value);
        Node nextOfp = p.next;
        newNode.next = nextOfp;
        nextOfp.pre = newNode;
        newNode.pre = p;
        p.next = newNode;
    }


    /**
     * 删除某个节点
     * @param p
     */
    public void deleteByNode(Node p) {
        if(p==null) return;
        if(p==head){
            head = head.next;
        }else if(p.next == null){
            p.pre.next = null;
        }else{
            p.pre.next = p.next;
        }
    }

    public void deleteByValue(int value) {
        deleteByNode(find(value));
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
    class Node{
        private int data;
        private Node pre;
        private Node next;
        Node(int data){
            this.data = data;

        }
    }
}

package book.linkedlist;

/**
 * 单链表插入、删除、查找
 * 存储的是int类型的数据
 */
public class SinglyLinkedList {
    private Node head;
    public Node findByValue(int value) {
        Node node = head;
        while(node!=null){
            if(node.data == value){
                return node;
            }
            node = node.next;
        }

        return null;
    }
    public Node findByIndex(int index){
        int cnt = 0;
        Node node = head;
        while(node!=null && cnt<index){
            node = node.next;
        }

        if(cnt==index){
            return node;
        }else{
            return null;
        }
    }

    //在头部插入数据
    public void insertToHead(int value) {
        Node valueNode = new Node(value,null);
        insertToHead(valueNode);

    }

    private void insertToHead( Node newNode) {
        if(head!=null){
            newNode.next = head;
            head = newNode;
        }else{
            head = newNode;
        }
    }
    //在尾部插入数据
    public void insertTail(int value){
        if(head == null){
            head = new Node(value,null);
        }else{
            Node node = head;
            Node p = null;
            while(node!=null){
                p = node;
                node = node.next;
            }
            p.next = new Node(value,null);
        }

    }

    public void insertAfter(Node p, int value) {

        insertAfter(p,new Node(value,null));
    }

    public void insertAfter(Node p, Node newNode) {
        Node tmp = p.next;
        p.next = newNode;
        newNode.next = tmp;
    }

    public void insertBefore(Node p, int value) {

        insertAfter(p,new Node(value,null));
    }

    public void insertBefore(Node p, Node newNode) {

        if(p==null) return;
        if(p==head){
            insertToHead(newNode);
        }else{
            Node q = p;
            while(q!=null && q.next!=p){
                q = q.next;
            }

            if(q!=null){
                q.next = newNode;
                newNode.next = p;
            }
        }
    }



    public void deleteByNode(Node p) {
        if(p==null || head==null)return;
        if(p==head){
            head = head.next;
        }else{
            Node q = p;
            while(q!=null && q.next!=p){
                q = q.next;
            }
            if(q!=null){
                q.next = p.next;
            }
        }
    }

    public void deleteByValue(int value) {
        deleteByNode(findByValue(value));
    }

    public void printAll() {
        Node node = head;
        while(node!=null){
            System.out.print(node.data+"\t");
            node = node.next;
        }
        System.out.println();
    }

    //判断节点是否相同
    public boolean TFResult(Node left, Node right){

        Node l = left,r = right;
        while(l!=null && r!=null){
            if(l.data==r.data){
                l =l.next;
                r = r.next;
            }else{
                return  false;

            }
        }
        return l==null && r==null;
    }

    //判断是否是回文
    public boolean palindrome(){

        if(head==null) return true;
        //查询找到中间节点
        Node p = head;
        Node q = head;
        if(p.next==null){
            //只包含头节点一个元素
            return true;
        }
        while(p!=null && p.next!=null){
            p = p.next.next;
            q = q.next;
        }

        //奇数个节点，偶数个节点分别测试
        if(p!=null){
            Node rightHead = q.next;
            Node leftHead = inverse(q).next;
            return TFResult(leftHead,rightHead);
        }else{
            Node rightHead = q;
            Node leftHead = inverse(q).next;
            return TFResult(leftHead,rightHead);
        }
    }

    private Node inverse(Node q) {
        if(q==null) return null;
        Node newHead = new Node(q.data,null);
        Node node = head;
        while(node!=null && node!=q){
            Node newNode = new Node(node.data,newHead.next);
            newHead.next = newNode;
            node = node.next;
        }
        return newHead;
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
    public static void main(String[] args){
        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.insertTail(1);
        list1.insertTail(4);
        list1.insertTail(4);
        list1.insertTail(1);
        /*list1.insertTail(1);*/
        list1.printAll();

        boolean r = list1.palindrome();
        System.out.print(r);
    }
}

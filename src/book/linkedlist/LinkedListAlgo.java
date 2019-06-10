package book.linkedlist;

public class LinkedListAlgo {
    // 单链表反转
   public static Node reverse(Node list) {
        Node current = list,pre = null;
        while(current!=null){
            Node next  = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return  pre;
    }

    // 检测环

    /***
     * 检测环，快慢指针，如果有环，快慢指针会相遇。
     * 扩展问题：环上节点的个数
     * 扩展问题：链表的长度
     * @param list
     * @return
     */
    public static boolean checkCircle(Node list) {
        if(list==null) return false;
        Node fast = list;
        Node slow = list;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow) {
                return true;
            }
        }
        return false;
    }

    // 有序链表合并
    public static Node mergeSortedLists(Node la, Node lb) {
        Node nodea = la , nodeb = lb;
        Node dummyHead = new Node(1,null);
        Node current = dummyHead;
        while(nodea!=null && nodeb!=null){
            if(nodea.data<nodeb.data){
                current.next = nodea;
                nodea = nodea.next;
            }else{
                current.next = nodeb;
                nodeb = nodeb.next;
            }
            current = current.next;
        }
        if(nodea!=null){
            current.next = nodea;
        }
        if(nodeb!=null){
            current.next = nodeb;
        }
        return dummyHead.next;
    }

    // 删除倒数第K个结点
    public static Node deleteLastKth(Node list, int k) {
        Node fast = list;
        int i = 1;
        while(fast!=null && i<k){
            fast = fast.next;
            i++;
        }
        if(fast==null) return list;
        Node pre = null;
        Node slow = list;
        while(fast.next!=null){
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        if(pre ==null){
            return list.next;
        }else{
            pre.next = pre.next.next;
            return list;
        }
    }
    // 求中间结点
    public static Node findMiddleNode(Node list) {
        if(list==null) return null;
        Node slow = list;
        Node fast = list;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void printAll(Node list) {
        Node node = list;
        while(node!=null){
            System.out.print(node.data+"\t");
            node = node.next;
        }
        System.out.println();
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public String toString(){
            String res = data+"\t";
            if(this.next!=null){
                res += this.next.toString();
            }
            return res;
        }
    }

}

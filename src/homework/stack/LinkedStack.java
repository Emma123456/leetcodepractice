package homework.stack;

/**
 * 链式栈
 */
public class LinkedStack {
    private Node top;

    public void push(String value){
        Node newNode = new Node(value);
        if(top == null){
            top = newNode;
        }else{
            newNode.next = top;
            top = newNode;
        }
    }

    /**
     * 出栈
     * @return
     */
    public String pop(){
        if(top == null) return null;
        String val = top.data;
        top = top.next;
        return val;
    }

    /**
     * 这和正常的颠倒了顺序
     */
    public void printAll(){
        Node current = top;
        while(current!=null){
            System.out.print(current.data+"\t");
            current = current.next;
        }
        System.out.println();
    }

    public boolean isEmpty(){
        return top ==null;
    }

    /**
     * 清空
     */
    public void clear() {
        top = null;
    }


    class Node{
        String data;
        Node next;
        Node(String data){
            this.data = data;
        }
    }
}

package homework2.linkedlist;

import java.util.LinkedList;

public class SinglyLinkedListTest {
    public static void main(String[] args){
        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.insertToTail(1);
        list1.insertToHead(4);
        list1.insertToTail(3);
        list1.insertToTail(8);

        list1.print();


        //list1.reverse();
        //list1.print();
        System.out.println(list1.findMiddle());
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
    }
}

package homework.linkedlist;

public class SinglyLinkedListTest {
    public static void main(String[] args){
        SinglyLinkedList list1 = new SinglyLinkedList();
        list1.insertToTail(1);
        list1.insertToTail(4);
        list1.insertToTail(3);
        list1.insertToTail(8);

        SinglyLinkedList.Node node = list1.find(8);
        list1.deleteByNode(node);
        list1.printAll();

        list1.insertToTail(100);
        list1.insertToTail(2);
        list1.deleteByValue(100);
        list1.printAll();

        list1.reverse();
        list1.printAll();

        SinglyLinkedList list2 = new SinglyLinkedList();
        list2.reverse();
        list2.printAll();

        list2.insertToTail(100);
        list2.reverse();
        list2.printAll();

        list2.insertToTail(3);
        list2.reverse();
        list2.printAll();

        System.out.println(list1.findMiddle().data);
        list1.deleteByValue(4);
        System.out.println(list1.findMiddle().data);
    }
}

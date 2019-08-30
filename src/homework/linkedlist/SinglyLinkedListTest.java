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
        list1.deleteByValue(2);
        list1.printAll();
    }
}

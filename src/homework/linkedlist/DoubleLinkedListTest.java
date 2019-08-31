package homework.linkedlist;

public class DoubleLinkedListTest {
    public static void main(String[] args){
        DoubleLinkedList list1 = new DoubleLinkedList();
        list1.insertToTail(1);
        list1.insertToTail(4);
        list1.insertToTail(3);
        list1.insertToTail(8);

        DoubleLinkedList.Node node = list1.find(8);
        list1.deleteByNode(node);
        list1.printAll();

        list1.insertToTail(100);
        list1.insertToTail(2);
        list1.printAll();
        list1.deleteByValue(100);
        list1.printAll();
    }
}

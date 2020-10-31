package homework;

public class SkipListTest {
    public static void main(String[] args){
        SkipList skipList = new SkipList();
        skipList.insert(5);
        skipList.insert(8);
        skipList.insert(4);
        skipList.printList();
        System.out.println(skipList.search(5));
        System.out.println(skipList.search(8));
        System.out.println(skipList.search(4));
        System.out.println(skipList.search(10));
        skipList.remove(4);
        skipList.remove(5);
        System.out.println(skipList.search(8));
        System.out.println(skipList.search(5));
    }
}

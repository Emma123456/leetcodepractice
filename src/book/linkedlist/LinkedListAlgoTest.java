package book.linkedlist;

import java.util.Arrays;
import java.util.List;

public class LinkedListAlgoTest {
    public static void main(String[] args){
        for(LinkedListAlgo.Node list : createTestData()){
            LinkedListAlgo.printAll(list);

        }

    }
    public static void middleTest(String[] args){
        for(LinkedListAlgo.Node list : createTestData()){
            LinkedListAlgo.Node node = LinkedListAlgo.findMiddleNode(list);
            if(node!=null){
                System.out.println(node.data);
            }

        }

    }
    public static void deleteTest(String[] args){
        for(LinkedListAlgo.Node list : createTestData()){
            LinkedListAlgo.Node reversedList = LinkedListAlgo.deleteLastKth(list,1);
            System.out.println(reversedList);
        }
        for(LinkedListAlgo.Node list : createTestData()){
            LinkedListAlgo.Node reversedList = LinkedListAlgo.deleteLastKth(list,2);
            System.out.println(reversedList);
        }
    }
    public static void mergeTest(String[] args){
        LinkedListAlgo.Node list = LinkedListAlgo.mergeSortedLists(createTestData().get(2), createTestData().get(3));
        System.out.println(list);
    }
    public static void circleTest(String[] args){
        for(LinkedListAlgo.Node list : createTestData()){
            //boolean result = LinkedListAlgo.checkCircle(list);
            //System.out.println(result);
        }


        boolean result = LinkedListAlgo.checkCircle(createCircleList1());
        System.out.println(result);

        boolean result2 = LinkedListAlgo.checkCircle(createCircleList2());
        System.out.println(result2);

    }
    public static void reverseTest(String[] args){
        for(LinkedListAlgo.Node list : createTestData()){
            LinkedListAlgo.Node reversedList = LinkedListAlgo.reverse(list);
            System.out.println(reversedList);
        }
    }

    public static List<LinkedListAlgo.Node> createTestData(){
        LinkedListAlgo.Node empty = new LinkedListAlgo.Node(1,null);
        LinkedListAlgo.Node list1 = new LinkedListAlgo.Node(1,null);
        LinkedListAlgo.Node tmp = list1;
        tmp.next = new LinkedListAlgo.Node(2,null);;
        tmp = tmp.next;


        LinkedListAlgo.Node list2 = new LinkedListAlgo.Node(1,null);
        tmp = list2;
        tmp.next = new LinkedListAlgo.Node(2,null);;
        tmp = tmp.next;
        tmp.next = new LinkedListAlgo.Node(3,null);;
        tmp = tmp.next;


        return Arrays.asList(null,empty,list1,list2);
    }

    private static LinkedListAlgo.Node createCircleList1(){
        LinkedListAlgo.Node list2 = new LinkedListAlgo.Node(1,null);
        LinkedListAlgo.Node tmp = list2;
        tmp.next = new LinkedListAlgo.Node(2,null);;
        tmp = tmp.next;
        tmp.next = new LinkedListAlgo.Node(3,null);;
        tmp = tmp.next;
        tmp.next = list2;
        return list2;
    }

    private static LinkedListAlgo.Node createCircleList2(){
        LinkedListAlgo.Node list2 = new LinkedListAlgo.Node(1,null);
        LinkedListAlgo.Node tmp = list2;
        tmp.next = new LinkedListAlgo.Node(2,null);;
        tmp = tmp.next;
        tmp.next = new LinkedListAlgo.Node(3,null);;
        tmp = tmp.next;
        tmp.next = new LinkedListAlgo.Node(4,null);;
        tmp = tmp.next;
        tmp.next = list2.next;
        return list2;
    }
}

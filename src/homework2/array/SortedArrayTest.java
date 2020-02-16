package homework2.array;


public class SortedArrayTest {
    public static void main(String[] args){
        SortedArray array = new SortedArray<Integer>(10);
        array.add(1);
        array.add(10);
        array.add(8);
        array.add(100);
        array.add(19);
        array.print();
        System.out.println(array.get(3));
        array.delete(3);
        System.out.println(array.get(3));

        array.print();
        System.out.println();
        System.out.println(array.find(1));
        System.out.println(array.find(100));
        System.out.println(array.find(19));
        System.out.println(array.find(10));
        System.out.println(array.find(8));
    }
}

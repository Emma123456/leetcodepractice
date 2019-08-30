package homework.array;

public class SortedArrayTest {
    public static void main(String[] args){
        SortedArray array = new SortedArray<Integer>();
        array.add(1);
        array.add(10);
        array.add(8);
        array.add(100);
        array.add(19);
        System.out.println(array.get(3));
        array.delete(3);
        System.out.println(array.get(3));
    }
}

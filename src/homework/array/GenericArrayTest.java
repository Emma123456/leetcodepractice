package homework.array;

public class GenericArrayTest {
    public static void main(String[] args){
        GenericArray array = new GenericArray<Integer>();
        for(int i=0;i<20;i++){
            array.add(i);
        }
        System.out.println(array.get(18));
        array.delete(18);
        System.out.println(array.get(18));
    }
}

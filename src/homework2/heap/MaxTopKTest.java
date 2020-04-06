package homework2.heap;

public class MaxTopKTest {
    public static void main(String[] args) {
        MaxTopK top = new MaxTopK(3);
        System.out.println(top.top());
        top.insert(3);
        top.insert(4);
        System.out.println(top.top());
        top.insert(5);
        System.out.println(top.top());

        top.insert(1);
        top.insert(8);
        System.out.println(top.top());

        top.insert(4);
        System.out.println(top.top());
    }
}

package homework2.heap;

public class HeapTest {
    public static void main(String[] args) {
        Heap2<Integer> heap = new Heap2<>(10);
        heap.insert(3);
        heap.insert(10);
        heap.insert(40);
        heap.insert(31);
        heap.insert(50);
        heap.insert(0);
        System.out.println(heap.getTop());
        heap.remove();
        System.out.println(heap.getTop());
        heap.remove();
        heap.remove();
        System.out.println(heap.getTop());

    }
}

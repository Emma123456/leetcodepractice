package book.tree;

public class MaxHeapTest {
    public static void main(String[] args){
        MaxHeap<Integer> mheap = new MaxHeap<>(5);
        mheap.insert(5);
        mheap.insert(7);
        mheap.insert(4);
        mheap.insert(90);
        mheap.insert(100);
        mheap.insert(110);
        mheap.levelPrint();
    }
}

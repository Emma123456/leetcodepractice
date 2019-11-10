package homework.tree;

import java.util.PriorityQueue;

public class HeapTest {
    public static void main(String[] args){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(100);
        System.out.println(pq.poll());
        System.out.println(pq.poll());


        Heap<Integer> heap = new Heap<>();
        heap.offer(1);
        heap.offer(10);
        heap.offer(100);
        heap.offer(50);
        heap.offer(40);
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }
}

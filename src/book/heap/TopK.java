package book.heap;

import java.util.PriorityQueue;

public class TopK {
    public int[] topk(int[] data, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k);
        for(int num : data){
            if(queue.size()<k){
                queue.offer(num);
            }else if(queue.peek()<num){
                queue.poll();
                queue.offer(num);
            }
        }

        int[] result = new int[k];
        for(int i=k-1;i>=0;i--){
            result[i] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args){
        TopK top = new TopK();
        int[] data = new int[]{7,9,8,2,3,4,10,90,87,3,4};
        int k = 5;
        int[] result = top.topk(data,k);
        for(int a : result){
            System.out.print(a+"\t");
        }
    }
}

package homework.queue;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SlidingWindow239 {

    /**
     * 暴力O(Nk)
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n =nums.length;
        if(n==0) return new int[]{};
        int[] result = new int[n-k+1];
        int idx = 0;
        for(int i=0;i<=n-k;i++){
            int max = Integer.MIN_VALUE;
            for(int j =i ;j<i+k;j++){
                max= Math.max(max,nums[j]);
            }
            result[idx++] = max;
        }

        return result;
    }


    /**
     * 使用优先队列O(Nlogk)
     * 维持一个大小为k的最大堆。堆顶元素就是最大值。
     * 每次插入元素，获取最大值之后，就把窗口最左端元素删除，保证堆内元素是在窗口范围内。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowV2(int[] nums, int k) {

        int n =nums.length;
        if(n==0) return new int[]{};
        int[] result = new int[n-k+1];
        int idx = 0;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,new Comparator<Integer>(){
            @Override
            public int compare(Integer i1,Integer i2){
                return i2-i1;
            }
        });
        for(int i=0;i<n;i++){
            if(i<k-1){
                maxHeap.offer(nums[i]);
            }else{
                maxHeap.offer(nums[i]);
                result[idx++] = maxHeap.peek();
                //这是一个O(n)的操作，所以我认为时间复杂度是O(n^2)
                maxHeap.remove(nums[i-k+1]);

            }

        }
        return result;
    }

    /**
     * 使用双端队列,队列中的元素是数组的值。队列中元素从大到小排序。
     * 每次插入的时候，从尾部插入，插入的时候将比自己小的元素删除，最后插入自己。这样保证队列头部的元素是最大值。
     * 如何保证队列中的元素是窗口范围内的元素：每次插入一个元素之后，将窗口最左端的元素删除。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowV3(int[] nums, int k) {

        int n =nums.length;
        if(n==0) return new int[]{};
        int[] result = new int[n-k+1];
        int idx = 0;

        Monoque queue = new Monoque();

        for(int i=0;i<n;i++){
            if(i<k-1){
                queue.push(nums[i]);
            }else{
                queue.push(nums[i]);
                result[idx++] = queue.getMax();
                queue.pop(nums[i-k+1]);
            }

        }
        return result;
    }

    class Monoque{
        private Deque<Integer> queue = new LinkedList<>();

        public void push(Integer n){
            while(!queue.isEmpty() && queue.peekLast()<n){
                queue.pollLast();
            }
            queue.offerLast(n);
        }

        public int getMax(){
            return queue.peekFirst();
        }

        public void pop(int n){
            if(queue.peekFirst()==n){
                queue.pollFirst();
            }
        }
    }


    /**
     * 使用双端队列，队列中存放的是元素的下标。队列需要按照元素值从大到小排序。
     * 每次插入的时候，从尾部插入，插入的时候将比自己小的元素删除，最后插入自己的下包。这样保证队列头部的元素是最大值的下标。
     * 如何保证队列中的元素是窗口范围内的元素的下标：每次插入一个元素之后，将窗口最左端的元素下标删除。
     * 删除的时候有个技巧是，如果在队列头部就删除，不在队列头部可以暂时保留，我们只要保证队列头部的元素下标在窗口范围内即可。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowV4(int[] nums, int k) {

        int n =nums.length;
        if(n==0) return new int[]{};
        int[] result = new int[n-k+1];
        int idx = 0;

        Deque<Integer> queue = new LinkedList<>();

        for(int i=0;i<n;i++){
            while(!queue.isEmpty() && nums[queue.peekLast()]<nums[i]){
                queue.pollLast();
            }
            queue.offerLast(i);
            System.out.println(queue);
            if(i>=k-1){
                result[idx++]=nums[queue.peekFirst()];
                if(queue.peekFirst()==i-k+1){//超出窗口
                    queue.pollFirst();
                }
            }

        }
        return result;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,3,-1,-3,-2,3,6,7};
        int k = 3;
        new SlidingWindow239().maxSlidingWindowV4(nums,k);
    }
}

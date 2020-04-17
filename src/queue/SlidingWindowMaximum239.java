package queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum239 {

    private int[] nums;
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length==0) return new int[]{};
        this.nums = nums;
        int n = nums.length;
        if(n*k == 0) return new int[]{0};
        Deque<Integer> deQue = new ArrayDeque<Integer>();
        for(int i=0;i<k;i++){
            cleanDque(i,k,deQue);
            deQue.offerLast(i);

        }
        int[] maxs = new int[n-k+1];
        for(int i=k;i<n;i++){
            maxs[i-k] = nums[deQue.peekFirst()];
            cleanDque(i,k,deQue);
            deQue.offerLast(i);
        }
        maxs[n-k] = nums[deQue.peekFirst()];
        return maxs;
    }

    private void cleanDque(int i,int k,Deque<Integer> deQue){
        if(!deQue.isEmpty() && deQue.getFirst() == i-k){
            deQue.pollFirst();
        }
        while(!deQue.isEmpty() && nums[deQue.peekLast()] < nums[i]){
            deQue.pollLast();
        }
    }
    
    public int[] maxSlidingWindowV2(int[] nums, int k) {
        if(nums == null || nums.length==0) return new int[]{};
        if(k==1) return nums;
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = nums[0];
        for(int i=1;i<n;i++){
            if(i%k==0){
                left[i] = nums[i];
            }else{
                left[i] = Math.max(nums[i],left[i-1]);
            }
        }

        right[n-1] = nums[n-1];
        for(int i=n-2;i>=0;i--){
            if((i+1)%k==0){
                right[i] = nums[i];
            }else{
                right[i] = Math.max(right[i+1],nums[i]);
            }
        }

        int[] output = new int[n-k+1];
        for(int i=0;i<n-k+1;i++){
            output[i] = Math.max(right[i],left[i+k-1]);
        }
        return output;
    }
}

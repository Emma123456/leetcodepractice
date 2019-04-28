package array;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate217 {
    public boolean containsDuplicate(int[] nums) {
        boolean r = false;
        Set<Integer> distinct = new HashSet<Integer>();
        for(int i=0;i<nums.length;i++){
            if(distinct.contains(nums[i])){
                return true;
            }else{
                distinct.add(nums[i]);
            }
        }

        return r;
    }

    /**
     * 位运算，更快，内存更小
     * @param nums
     * @return
     */
    public boolean containsDuplicateV2(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for(int num : nums){
            maxValue = Math.max(maxValue,num);
            minValue = Math.min(minValue,num);
        }
        BitSet bitset = new BitSet(maxValue-minValue+1);
        for(int num : nums){
            if(bitset.get(num-minValue)){
                return true;
            }else{
                bitset.set(num-minValue);
            }
        }
        return false;
    }
}

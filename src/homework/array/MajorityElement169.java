package homework.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement169 {
    /**
     * 用哈希计算个数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int num : nums){
            if(map.get(num)==null){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }
        int target = nums.length/2;
        for(Integer key : map.keySet()){
            if(map.get(key)>target){
                return key;
            }
        }
        return -1;
    }

    /**
     * Boyer-Moore 投票算法
     * 出现次数大于n/2的元素，出现次数和剩余的其他元素的出现次数相互抵消，最后还多一个。
     * 这个题目保证了一定有众数。
     * @param nums
     * @return
     */
    public int majorityElementV2(int[] nums) {
        int num = nums[0];
        int count = 1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=num){
                if(count==0){
                    num=nums[i];
                    count = 1;
                }else{
                    count--;
                }
            }else{
                count++;
            }
        }
        return num;
    }


    /**
     * 分治法：找到左右两边两个子数组的众数a和b。如果a=b，则众数=a；如果a！=b，谁的个数多，谁是众数。
     * 有个问题是：子数组中不一定有众数。如果a的个数和b的个数一样多，怎么办？
     * @param nums
     * @return
     */
    public int majorityElementV3(int[] nums) {
        //
        return findMajorityElement(nums,0,nums.length-1);
    }

    private int findMajorityElement(int[] nums,int low ,int high){
        if(low==high) return nums[low];
        int middle = (low+high)/2;
        int a = findMajorityElement(nums,low,middle);
        int b = findMajorityElement(nums,middle+1,high);
        if(a==b) return a;
        int countA = countInRange(nums,low,middle,a);
        int countB = countInRange(nums,middle+1,high,b);
        return countA>countB?a:b;
    }
    private int countInRange(int[] nums,int low,int high,int val){
        int count = 0;
        for(int i=low;i<=high;i++){
            if(nums[i]==val){
                count++;
            }
        }
        return count;
    }

    /**
     * 排序之后，众数一定位于n/2的位置
     * @param nums
     * @return
     */
    public int majorityElementV4(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


    /**
     * 统计nums数组中每一个数的二进制位，
     * @param nums
     * @return
     */
    public int majorityElementV5(int[] nums) {
        int[] bits = new int[32];
        for(int num : nums){
            for(int i=0;i<32;i++){
                if(((num>>i) & 1)==1){
                    bits[i]++;
                }
            }
        }
        int r = 0;
        for(int i=31;i>=0;i--){
            if(bits[i]>nums.length/2){
                r =(r<<1)+1;
            }else{
                r =(r<<1)+0;
            }
        }
        return r;
    }

    public static void main(String[] args){
        new MajorityElement169().majorityElementV3(new int[]{3,2,3});
    }
}

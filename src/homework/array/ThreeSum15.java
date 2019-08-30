package homework.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum15 {
    /**
     * 暴力
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>  result = new ArrayList<List<Integer>>();
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<n;j++){
                if(j>i+1 && nums[j]==nums[j-1]){
                    continue;
                }
                for(int k=j+1;k<n;k++){
                    if(k>j+1 && nums[k]==nums[k-1]){
                        continue;
                    }
                    if(nums[i]+nums[j]+nums[k]==0){
                        result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        return result;
    }

    /***
     * 使用两个指针，寻找和等于目标值的两个数
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumV2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>  result = new ArrayList<List<Integer>>();
        int n = nums.length,i=0;
        while(i<n){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            int j = i+1,k=n-1;
            int target = 0-nums[i];
            while(j<k){
                int twoSum = nums[j]+nums[k];
                if(twoSum>target){
                    k--;
                }else if(twoSum<target){
                    j++;
                }else{
                    result.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    while(j<k && nums[j-1]==nums[j]) j++;
                    k--;
                    while(j<k && nums[k+1]==nums[k]) k--;
                }
            }
            i++;
            while(i<n && nums[i-1]==nums[i]) i++;
        }
        return result;
    }


    /**
     * 在使用两个指针的同时，加上一些逻辑判断，减少计算量
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumV3(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null)
            return result;
        int len = nums.length;
        if (len < 3)
            return result;
        Arrays.sort(nums);
        int target = 0;
        int k = 3;
        int maxValue = nums[len - 1];
        if (k * nums[0] > target || k * nums[len - 1] < target)
            return result;
        int num;
        for (int i = 0; i < len; i++) {
            num = nums[i];
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            if (num + 2 * maxValue < target)
                continue;
            if (3 * num > target)
                continue;
            twoSum(nums, i + 1, len - 1, target - num, num, result);
        }
        return result;
    }

    private void twoSum(int[] nums, int low, int high, int target, int num, List<List<Integer>> solution) {
        int i = low;
        int j = high;
        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                solution.add(Arrays.asList(num, nums[i], nums[j]));
                int x = nums[i];
                while (++i < j && nums[i] == x)
                    ;
                int y = nums[j];
                while (i < --j && y == nums[j])
                    ;

            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
    }
}

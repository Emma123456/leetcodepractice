package homework.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Permutation46 {
    private List<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<List<Integer>>();
        boolean[] flag = new boolean[nums.length];
        f(nums,new Stack<Integer>(),0,flag);
        return result;
    }

    //确定第idx个数，可以选择的范围是flag[i]=false，的nums[i]。
    private void f(int[] nums, Stack<Integer> values, int idx, boolean[] flag){
        if(idx==nums.length){
            result.add(new ArrayList<Integer>(values));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(flag[i]==false){
                values.push(nums[i]);
                flag[i]=true;
                f(nums,values,idx+1,flag);
                //状态重置
                flag[i]=false;
                values.pop();
            }
        }
    }
}

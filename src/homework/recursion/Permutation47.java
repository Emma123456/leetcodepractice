package homework.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Permutation47 {
    private List<List<Integer>> result;
    public List<List<Integer>> permuteUnique(int[] nums) {
        //首先排序
        Arrays.sort(nums);
        result = new ArrayList<List<Integer>>();
        boolean[] flag = new boolean[nums.length];
        f(nums,new Stack<Integer>(),0,flag);
        return result;
    }

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
                int tmp = i;
                //在这里去重,元素值相同的元素，在同一层只能出现一次
                while(i+1<nums.length && nums[i+1]==nums[i]){
                    i++;
                }
                //状态重置
                flag[tmp]=false;
                values.pop();
            }
        }
    }



    public List<List<Integer>> permuteUniqueV2(int[] nums) {
        Arrays.sort(nums);
        result = new ArrayList<List<Integer>>();
        boolean[] flag = new boolean[nums.length];
        //选中元素的下标
        int[] indexArray = new int[nums.length];
        robot(0,indexArray,nums,flag);
        return result;
    }

    private void robot(int idx,int[] indexArray,int[] nums,boolean[] flag){
        if(idx>=nums.length){
            List<Integer> values = new ArrayList<>(nums.length);
            for(int i=0;i<nums.length;i++){
                values.add(nums[indexArray[i]]);
            }
            result.add(values);
            return;
        }
        boolean select = false;
        for(int i=0;i<nums.length;i++){
            if(!flag[i] && (!select || nums[i]!=nums[indexArray[idx]])){
                select = true;
                indexArray[idx] = i;
                flag[i] = true;
                robot(idx+1,indexArray,nums,flag);
                flag[i] = false;
            }
        }
    }

}

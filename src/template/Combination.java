package template;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int[] nums;

    /**
     * 返回从n个元素中取m个数的组合
     * @param nums
     * @param m
     * @return
     */
    public List<List<Integer>> combination(int[] nums, int m){
        result.clear();
        this.nums = nums;
        if(nums==null || nums.length==0) return result;
        dfs(m,0,new ArrayList<Integer>());
        return result;
    }

    private void dfs(int m, int start, ArrayList<Integer> list) {
        if(m == 0) {
            //注意结果需要完全拷贝
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i< nums.length;i++){
            list.add(nums[i]);
            dfs(m-1,i+1,list);
            list.remove(list.size() - 1);
        }
    }
}

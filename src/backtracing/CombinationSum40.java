package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum40 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int[] nums;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result.clear();
        this.nums = candidates;
        Arrays.sort(nums);
        if(nums==null || nums.length==0) return result;
        dfs(target,0,new ArrayList<Integer>());
        return result;
    }

    private void dfs(int target, int start, ArrayList<Integer> list) {
        if(target == 0) {
            //注意结果需要完全拷贝
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i< nums.length;i++){
            //元素太大，剪枝
            if(nums[i]> target){
                break;
            }
            //重复元素,剪枝
            if(i>start && nums[i] == nums[i-1]){
                continue;
            }
            list.add(nums[i]);
            dfs(target-nums[i],i+1,list);
            list.remove(list.size() - 1);
        }
    }
}

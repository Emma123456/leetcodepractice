package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets90 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int[] nums;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result.clear();
        this.nums = nums;
        Arrays.sort(nums);
        if(nums==null || nums.length==0) return result;
        for(int m = 0;m<=nums.length;m++){
            dfs(m,0,new ArrayList<Integer>());
        }
        return result;
    }
    private void dfs(int m, int start, ArrayList<Integer> list) {
        if(m == 0) {
            //注意结果需要完全拷贝
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i< nums.length;i++){
            if(i>start && nums[i] == nums[i-1]) continue;
            list.add(nums[i]);
            dfs(m-1,i+1,list);
            list.remove(list.size() - 1);
        }
    }
}

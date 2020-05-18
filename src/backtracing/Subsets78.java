package backtracing;

import java.util.ArrayList;
import java.util.List;

public class Subsets78 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int[] nums;
    public List<List<Integer>> subsets(int[] nums) {
        result.clear();
        this.nums = nums;
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
            list.add(nums[i]);
            dfs(m-1,i+1,list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> subsetsV2(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums==null || nums.length==0) return result;
        int n = nums.length;
        for(int i =0;i<(1<<n);i++){
            List<Integer> list = new ArrayList<Integer>();
            for(int j=0;j<n;j++){
                if((i&(1<<j))>0){
                    list.add(nums[j]);
                }
            }
            result.add(list);
        }
        return result;
    }
}

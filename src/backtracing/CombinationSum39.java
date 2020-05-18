package backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum39 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int[] candidates;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result.clear();
        Arrays.sort(candidates);
        this.candidates = candidates;
        if(candidates==null || candidates.length==0) return result;
        dfs(0,target,new ArrayList<Integer>());
        return result;
    }
    private void dfs(int start, int target,ArrayList<Integer> list) {
        if(target == 0) {
            //注意结果需要完全拷贝
            result.add(new ArrayList<Integer>(list));
            return;
        }
        if(start>= candidates.length || target<0) return;
        for(int i = start;i<candidates.length;i++){
            if(candidates[i] > target) break;
            list.add(candidates[i]);
            dfs(i,target-candidates[i],list);
            list.remove(list.size() - 1);
        }

    }
}

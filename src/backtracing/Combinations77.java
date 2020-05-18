package backtracing;

import java.util.ArrayList;
import java.util.List;

public class Combinations77 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int n;
    public List<List<Integer>> combine(int n, int k) {
        result.clear();
        this.n = n;
        dfs(k,1,new ArrayList<Integer>());
        return result;
    }
    private void dfs(int m, int start, ArrayList<Integer> list) {
        if(m == 0) {
            //注意结果需要完全拷贝
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i<=n;i++){
            list.add(i);
            dfs(m-1,i+1,list);
            list.remove(list.size() - 1);
        }
    }
}

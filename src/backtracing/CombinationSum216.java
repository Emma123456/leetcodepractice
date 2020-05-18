package backtracing;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum216 {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        result.clear();
        dfs(k,1,n,new ArrayList<Integer>());
        return result;
    }

    private void dfs(int m, int start, int target, ArrayList<Integer> list) {
        if(m == 0) {
            if(target==0){
                result.add(new ArrayList<Integer>(list));
            }
            return;
        }
        for(int i = start; i<= 9;i++){
            list.add(i);
            dfs(m-1,i+1,target-i,list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3V2(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int target = n;
        n = 9;
        int total = (1 << n) - 1;// 2^n-1
        for (int i = 0; i <= total; i++) {
            List<Integer> list = new ArrayList<Integer>();
            int sum = 0;
            for(int j=0;j<n;j++){
                if(((i>>j) &1)==1){
                    list.add(j+1);
                    sum += j+1;
                }
            }
            if(list.size()==k && sum==target){
                result.add(list);
            }

        }

        return result;
    }
}

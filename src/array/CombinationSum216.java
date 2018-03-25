package array;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum216 {
	private boolean[] path;
    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        path = new boolean[10];
        resultList.clear();
        robot(1,k,n);
        return resultList;
    }
    private void robot(int idx,int k,int n){
        if(n==0 && k==0){
            List<Integer> result = new ArrayList<Integer>();
            for(int i=0;i<path.length;i++){
                if(path[i]){
                    result.add(i);
                }
            }
            resultList.add(result);
        }
        
        if(n>0 && idx<path.length && idx<=n && k>0){
            path[idx]=true;
            robot(idx+1,k-1,n-idx);
            path[idx]=false;
            robot(idx+1,k,n);
        }
        
    }
}

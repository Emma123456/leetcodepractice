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


    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    /**
     * 按照递归树每一层可以选择的元素选择
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3V2(int k, int n) {
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

    /**
     * bit
     * i从0到(2^n-1)，第0位=1，表示选择1；第1位=1，表示选择2....
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3V3(int k, int n) {
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

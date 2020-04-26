package backtracing;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberOfSquarefulArrays996 {
    private int result;
    private int[] nums;
    public int numSquarefulPerms(int[] A) {
        result = 0;
        Arrays.sort(A);
        this.nums = A;
        if(nums==null || nums.length==0) return result;
        boolean[] visited = new boolean[nums.length];
        dfs(A.length,visited,new ArrayList<Integer>());
        return result;
    }

    private void dfs(int m,boolean[] visited, ArrayList<Integer> list) {
        if(m == 0){
            result++;
            return;
        }
        int pre = -1;
        for(int i =0;i<nums.length;i++){
            if(visited[i]==false){
                //在同一层，使用了相同的元素，略过
                if(pre!=-1 && nums[i] == pre){
                    continue;
                }
                if(list.isEmpty() || isPerfectSquareElement(list.get(list.size()-1),nums[i])){
                    visited[i] = true;
                    list.add(nums[i]);
                    dfs(m-1,visited,list);
                    pre = list.remove(list.size()-1);
                    visited[i] = false;
                }

            }
        }
    }

    private boolean isPerfectSquareElement(int  i,int  j){
        int sum = i+j;
        int a = (int)Math.sqrt(sum);
        return a*a == sum;
    }
}

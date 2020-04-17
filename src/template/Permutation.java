package template;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    private int[] nums;
    /**
     * 计算从n个数中取m个数的排列
     * @param nums
     * @param m
     * @return
     */
    public List<List<Integer>> permutation(int[] nums, int m){
        result.clear();
        this.nums = nums;
        if(nums==null || nums.length==0) return result;
        boolean[] visited = new boolean[nums.length];
        dfs(m,visited,new ArrayList<Integer>());
        return result;
    }

    /**
     * dfs递归调用
     * @param m
     *          还需要取几个元素
     * @param visited
     *           哪些元素已经被取了，不能再取l
     * @param list
     *          已经访问的元素
     */
    private void dfs(int m,boolean[] visited, ArrayList<Integer> list) {
        if(m == 0){
            //注意结果需要完全拷贝
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i =0;i<nums.length;i++){
            if(visited[i]==false){
                visited[i] = true;
                list.add(nums[i]);
                dfs(m-1,visited,list);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }
}

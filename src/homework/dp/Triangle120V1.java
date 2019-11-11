package homework.dp;

import java.util.List;

public class Triangle120V1 {
    private List<List<Integer>> triangle;
    private int n;
    private int minSum;
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null || triangle.size()==0) return 0;
        this.triangle = triangle;
        this.n = triangle.size();
        minSum = Integer.MAX_VALUE;

        walk(0,0,0);
        return minSum;
    }
    private void walk(int i,int j,int sum){
        //System.out.println(i+" "+j+" "+sum);
        if(i==n) {
            minSum = Math.min(minSum,sum);
            return;
        };
        if(j>=triangle.get(i).size()) return;
        walk(i+1,j,sum+triangle.get(i).get(j));
        walk(i+1,j+1,sum+triangle.get(i).get(j));
    }
}

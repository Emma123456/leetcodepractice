package array;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FourSum454 {
	private int count;
	/**
	 * O(n^4)
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        count = 0;
        int[] indexes = new int[]{0,0,0,0};
        dfs(A,B,C,D,indexes,0);
        return count;
    }
    private void dfs(int[] A, int[] B, int[] C, int[] D,int[] indexes ,int idx){
        if(idx==4){
            if(A[indexes[0]]+B[indexes[1]]+C[indexes[2]]+D[indexes[3]]==0){
                count++;
            }
            return;
        }
        for(int i=0;i<A.length;i++){
            indexes[idx] = i;
            dfs(A,B,C,D,indexes,idx+1);
        }
    }
    
    /**
     * O(n^2)
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCountV2(int[] A, int[] B, int[] C, int[] D) {
    	Map<Integer,Integer> sumMap = new HashMap<Integer,Integer>();
    	int target = 0;
    	for(int i = 0;i<A.length;i++){
    		for(int j=0;j<B.length;j++){
    			int sum = A[i]+B[j];
    			if(sumMap.containsKey(sum)){
    				sumMap.put(sum, sumMap.get(sum)+1);
    			}else{
    				sumMap.put(sum, 1);
    			}
    		}
    	}
    	int count = 0;
    	for(int i = 0;i<C.length;i++){
    		for(int j=0;j<D.length;j++){
    			int sum = C[i]+D[j];
    			if(sumMap.containsKey(target-sum)){
    				count += sumMap.get(target-sum);
    			}
    		}
    	}
    	return count;
    }
    
    /**
     * 有错误
     * https://leetcode.com/problems/4sum-ii/discuss/114123/Java-solution-using-sort-not-map-beats-93-O(n2*log(n))-time
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCountV3(int[] A, int[] B, int[] C, int[] D) {
    	int[] sum1 = new int[A.length*B.length];
    	int idx = 0;
    	for(int i = 0;i<A.length;i++){
    		for(int j=0;j<B.length;j++){
    			sum1[idx++] = (A[i]+B[j]);
    		}
    	}
    	
    	int[] sum2 = new int[C.length * D.length];
    	idx = 0;
    	for(int i = 0;i<C.length;i++){
    		for(int j=0;j<D.length;j++){
    			sum2[idx++]=(-(C[i]+D[j]));//这一步的处理没想到
    		}
    	}
    	
    	Arrays.sort(sum1);
    	Arrays.sort(sum2);
    	int count = 0;
    	int i = 0,j = 0;
		while (i < sum1.length && j < sum2.length) {
			if (sum1[i] < sum2[j]) {
				i++;
			}else if(sum1[i]>sum2[j]){
				j++;
			}else{
				//这一步处理，也出乎意料
				int count1 = 1,count2 = 1;
				while(++i<sum1.length && sum1[i-1]==sum1[i]) count1++;
				while(++j<sum2.length && sum2[j-1]==sum2[j]) count2++;
				count += count1*count2;
			}
		}
    	return count;
    }
	public static void main(String[] args) {
		int[] A = new int[]{1,2};
		int[] B = new int[]{-2,-1};
		int[] C = new int[]{-1,2};
		int[] D = new int[]{0,2};
		new FourSum454().fourSumCountV3(A, B, C, D);
		
		Date date = new Date(1522678303000L);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	}

}

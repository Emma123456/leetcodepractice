package array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges228 {
	public List<String> summaryRanges(int[] nums) {
        if(nums==null) return null;
        int n = nums.length;
        List<String> list = new ArrayList<String>();
        
        int start = 0;
        int end = 0;
        while(end<n){
            if(end==n-1 || nums[end+1]-nums[end]!=1){
                if(start==end){
                    list.add(nums[start]+"");
                }else{
                    list.add(nums[start]+"->"+nums[end]);
                }
                start = end+1;
            }
            end++;
            
        }
        return list;
    }
	public static void main(String[] args) {
		int[] nums = new int[]{-2147483648,-2147483647,2147483647};
		new SummaryRanges228().summaryRanges(nums);
	}

}

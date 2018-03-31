package array;

public class NumberSubarrays795 {
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
        int n = A.length;
        int count=0;
        for(int start=0;start<n;start++){
            int maxVal = A[start];
            for(int end = start;end<n;end++){
                if(A[end]<=R){
                    maxVal = Math.max(maxVal,A[end]);
                    if(maxVal>=L){
                        count++;
                    }
                }else{
                    break;
                }
            }
        }
        return count;
    }
	
	public int numSubarrayBoundedMaxV2(int[] A, int L, int R) {
        int n = A.length;
        int count=0;
        int res = 0;
        int start = 0;
        for(int i=0;i<n;i++){
            if(A[i]>=L && A[i]<=R){
            	res += (i-start+1);
            	count = (i-start+1);
            }else if(A[i]<L){
            	res += count;
            }else{
            	start = i+1;
            	count=0;
            }
        }
        return res;
    }
	public static void main(String[] args) {
		int[] A = new int[]{2,1,4,3};
		int r = new NumberSubarrays795().numSubarrayBoundedMax(A, 2, 3);
	}

}

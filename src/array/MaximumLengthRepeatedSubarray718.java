package array;

public class MaximumLengthRepeatedSubarray718 {
	/**
	 * 这是一个暴力枚举。每个A的下标i，分别与B的每个下标j作为起点，判断最长的重复数组长度
	 * @param A
	 * @param B
	 * @return
	 */
	public int findLength(int[] A, int[] B) {
		int len1 = A.length;
        int len2 = B.length;
        int maxLen = 0;
        for(int i=0;i<len1;i++){
            int j = 0;
            while(j<len2){
                if(A[i] == B[j]){
                    int count = 0;
                    int idxA = i;
                    int idxB = j;
                    while(idxA<len1 && idxB <len2 && A[idxA++] == B[idxB++]) count++;
                    maxLen = Math.max(maxLen,count);
                }
                j++;
            }
        }
        return maxLen;
	}
	
	/**
	 * 动态规划：A数组的每个下标i，分别与B数组的每个下标j作为子数组的终点，计算最长子数组重复数组的长度
	 * @param A
	 * @param B
	 * @return
	 */
	public int findLengthV2(int[] A, int[] B) {
		if (A == null || B == null)
			return 0;
		int m = A.length;
		int n = B.length;
		int[][] dp = new int[m][n];
		int maxLen = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (j == 0 || i == 0) {
					dp[i][j] = (A[i] == B[j] ? 1 : 0);
				} else if (A[i] == B[j]) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					maxLen = Math.max(maxLen, dp[i][j]);
				}
			}
		}
		return maxLen;
	}
	
	public int findLengthV3(int[] a, int[] b) {
        int m = a.length, n = b.length;
        if (m == 0 || n == 0) return 0;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--)
                max = Math.max(max, dp[i][j] = a[i] == b[j] ? 1 + dp[i + 1][j + 1] : 0);
        return max;        
    }
	/**
	 * 这种方式不太好理解，所以暂时不管
	 * @param a
	 * @param b
	 * @return
	 */
	public int findLengthV4(int[] a, int[] b) {
		int m = a.length, n = b.length;
        if (m == 0 || n == 0) return 0;
        int[] dp = new int[n + 1];
        int max = 0;
       /* for (int i = m - 1; i >= 0; i--)
            for (int j = 0; j <n ; j++)
                max = Math.max(max, dp[j] = a[i] == b[j] ? 1 + dp[j + 1] : 0);*/
        
        for (int i = m - 1; i >= 0; i--)
        	 for (int j = n - 1; j >= 0; j--)
                max = Math.max(max, dp[j] = a[i] == b[j] ? 1 + dp[j + 1] : dp[j]);
        return max;
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{1,2,3,2,1};
		int[] B = new int[]{3,2,1,4,7};
		int r = new MaximumLengthRepeatedSubarray718().findLengthV4(A, B);
		System.out.println(r);
	}
}

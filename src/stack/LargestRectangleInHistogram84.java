package stack;

public class LargestRectangleInHistogram84 {
	/**
	 * 本题解题思路一开始是想下标从0开始，如果使用heights[0]，最大面积是多少；如果使用 heights[0],heights[1]得到的举行，最大面积是多少；例如当计算完(2,1,5)这前三个bar的最大
	 * 举行面积，再计算(2,1,5,6)这四个bar的矩形面积。这其实是一种动态规划的思想。但是这个递推式是写不出来的。
	 * 应该使用的思路是全局。还是下标从0开始，当整个矩形的高度是heights[0]的时候，最大面积是多少；当整个矩形的高度是heights[1]的时候，最大矩形面积是多少。
	 * 如果这样想，对于某一个特定的高度heights[i]，最大的矩形面积是(heights[i]*(rights[i]-lefts[i]+1))。rights[i]就是heigths[i]的高度能达到的最右边的下标。
	 * lefts[i]=是heights[i]能达到的最左边的下标。
	 * 下面的代码在实现过程中做了一点变化。lessFromLeft[i] = 比i小，最靠近i的 && 使得heights[lessFromLeft[i]]< heights[i]
	 * lessFromRight[i] = 比i大，最靠近i的 && 使得heights[lessFromRight[i]]<heights[i]
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n==0) return 0;
        int[] lessFromLeft = new int[n];//对于下标i，从左边开始，最靠近i的idx使得heights[lessFromLeft[i]]< heights[i]
        int[] lessFromRight = new int[n];//对于下标i，从右边开始数，最靠近i的idx使得heights[lessFromRight[i]]<heights[i]
        int ans = 0;
        lessFromLeft[0] = -1;//边界值处理
		for (int i = 1; i < n; i++) {
			int p = i - 1;
			while (p >= 0 && heights[p] >= heights[i]) {
				p = lessFromLeft[p];
			}
			lessFromLeft[i] = p;
		}

		lessFromRight[n - 1] = n;
		for (int i = n - 2; i >= 0; i--) {
			int p = i +1;
			while(p < n && heights[p]>=heights[i]){
				p = lessFromRight[p];
			}
			lessFromRight[i] = p;
		}
		for(int i=0;i<n;i++){
			ans = Math.max(ans, (lessFromRight[i] - lessFromLeft[i]-1)*heights[i]);
		}
        return ans;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] heights = new int[]{2,1,5,6,2,3};
		int ans = new LargestRectangleInHistogram84().largestRectangleArea(heights);
		System.out.println(ans);
		
	}

}

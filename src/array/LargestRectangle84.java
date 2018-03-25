package array;

public class LargestRectangle84 {
	/**
	 * 暴力枚举: 枚举左右边界
	 * O(n^2)
	 * @param heights
	 * @return
	 */
	public int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		for(int start = 0;start<heights.length;start++){
			int minHeight = heights[start];
			for (int end = start; end < heights.length; end++) {
				minHeight = Math.min(minHeight, heights[end]);
				maxArea = Math.max((end-start+1)*minHeight,maxArea);
			}
		}
		return maxArea;
	}

	/**
	 * 枚举短板
	 * O(n^2)
	 * @param heights
	 * @return
	 */
	public int largestRectangleAreaV2(int[] heights) {
		int[] left = new int[heights.length];
		int[] right = new int[heights.length];
		right[0] = 0;
		for (int i = 0; i < heights.length; i++) {
			for(int j=i;j<heights.length;j++){
				if(heights[j]>=heights[i]){
					right[i] = j;
				}else{
					break;
				}
			}
			for (int j = i; j >= 0; j--) {
				if (heights[j] >= heights[i]) {
					left[i] = j;
				} else {
					break;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < heights.length; i++) {
			max = Math.max(max, heights[i]*(right[i]-left[i]+1));
		}
		return max;
	}
	
	/**
	 * 在枚举短板的时候，执行跳跃，省掉一些计算
	 * 例如找左边最远：如果当前设置变量j = i；如果heights[i]<=heights[j-1]，那直接跳到left[j+1]；j=left[j-1]，循环再继续判断
	 * 例如找右边最远：如果当前设置变量j = i；如果heights[i]<=heights[j+1]，那直接跳到right[j+1]；j=right[j+1],循环再继续判断
	 * @param heights那直接跳到left[i-1]；j=left[j-1]，
	 * @return
	 */
	public int largestRectangleAreaV3(int[] heights) {
		int n = heights.length;
		if(n==0) return 0;
		int[] left = new int[n];
		left[0] = 0;
		for (int i = 1; i < n; i++) {
			int j = i ;
			while(j>0 && heights[i]<=heights[j-1]){
				j = left[j-1];
			}
			left[i] = j;
		}
		int[] right = new int[n];
		right[n-1] = n-1;
		for (int i = n-2; i >=0 ; i--) {
			int j = i ;
			while (j + 1 < n && heights[i] <= heights[j + 1]) {
				j = right[j + 1];
			}
			right[i] = j;
		}
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, heights[i]*(right[i]-left[i]+1));
		}
		return max;
	}

	public static void main(String[] args) {
		int[] heights = new int[]{2,1,5,6,2,3};
		int r = new LargestRectangle84().largestRectangleAreaV3(heights);
		System.out.println(r);
	}

}

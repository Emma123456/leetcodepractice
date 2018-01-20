package stack;

public class MaximalRectangle85 {
	/**
	 * 暴力搜索吧
	 * 矩阵中：只包含1的矩阵
	 * 计算个数的方法：1 一个一个元素遍历；2 利用矩阵长*宽;3 利用上一步计算的矩形的值
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
		int r = 0;
		int m = matrix.length;
		if(m == 0) return 0;
		int n = matrix[0].length;
		int[][] sums = new int[m][];
		for(int i=0;i<m;i++){
			sums[i] =  new int[n];
		}
		int right = n;
		for (int topx = 0; topx < m; topx++) {
			for(int topy = 0; topy < n ; topy++){
				if(matrix[topx][topy]=='1'){
					sums[topx][topy] = 1;
					r = sums[topx][topy]>r?sums[topx][topy]:r;
					right = n;
					for(int endx = topx;endx < m;endx++){
						int endy = topy;
						for( ; endy<right && matrix[endx][endy]=='1' ;endy++){
							//(topx,topy)  (endx,endy) 包含关系
							if(endy==topy){
								if(endx !=topx){
									sums[endx][endy] = sums[endx-1][endy]+1;
								}
							}else{
								sums[endx][endy] = sums[endx][endy-1]+(endx-topx+1);
							}
							//int sum = (endx-topx+1)*(endy-topy+1);
							r = sums[endx][endy]>r?sums[endx][endy]:r;
						}
						right = Math.min(endy, n);
					}
				}
			}
		}
		return r;
	}

	private int sum(int topx, int topy, int endx, int endy,char[][] matrix) {
		int sum = 0;
		for(int i=topx;i<=endx;i++){
			for(int j = topy;j<=endy;j++){
				sum += (matrix[i][j]=='1'?1:0);
			}
		}
		//System.out.println(topx+" "+topy+" "+endx+" "+endy+" "+sum);
		return sum;
	}
	
	/**
	 * 动态规划的思想
	 * https://leetcode.com/problems/maximal-rectangle/discuss/29054
	 * area[i][j]= [right(i,j) - left(i,j)]*height(i,j).
	 * left(i,j) = max(left(i-1,j), cur_left),cur_left can be determined from the current row
	 * right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
	 * height(i,j) = height(i-1,j) + 1, if matrix[i][j]==‘1’;
	 * height(i,j) = 0, if matrix[i][j]==‘0’
	 * @param matrix
	 * @return
	 */
	public int maximalRectangleV2(char[][] matrix) {
		int m = matrix.length;
		if (m == 0)
			return 0;
		int n = matrix[0].length;
		int[] left = new int[n], right = new int[n], height = new int[n];
		for(int i=0;i<n;i++){
			right[i] = n;
		}
		int maxA = 0;
		for (int i = 0; i < m; i++) {
			int cur_left = 0, cur_right = n;
			for (int j = 0; j < n; j++) { // compute height (can do this from either side)
				if (matrix[i][j] == '1')
					height[j]++;
				else
					height[j] = 0;
			}
			for (int j = 0; j < n; j++) { // compute left (from left to right)
				if (matrix[i][j] == '1')
					left[j] = Math.max(left[j], cur_left);
				else {
					left[j] = 0;
					cur_left = j + 1;
				}
			}
			// compute right (from right to left)
			for (int j = n - 1; j >= 0; j--) {
				if (matrix[i][j] == '1')
					right[j] = Math.min(right[j], cur_right);
				else {
					right[j] = n;
					cur_right = j;
				}
			}
			// compute the area of rectangle (can do this from either side)
			for (int j = 0; j < n; j++)
				maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
		}
		return maxA;
	}
	
	/**
	 * 不知道怎么想出这个解法的
	 * @param matrix
	 * @return
	 */
	public int maximalRectangleV3(char[][] matrix) {
		int m = matrix.length;
		if (m == 0)
			return 0;
		int n = matrix[0].length;
		int[] left = new int[n], right = new int[n], height = new int[n];
		for(int i=0;i<n;i++){
			right[i] = n;
		}
		int maxA = 0;
		for(int i=0;i<m;i++){
			int curLeft = 0;
			for(int j=0;j<n;j++){
				if(matrix[i][j]=='1'){
					left[j] = Math.max(left[j], curLeft);//
				}else{
					left[j] = 0;
					curLeft=i+1;
				}
			}
			
			int cur_right = n;
			for(int j = n-1 ;j>=0;j--){
				if(matrix[i][j] == '1'){
					right[j] = Math.min(right[j], cur_right);
				}else{
					right[j] = n;
					cur_right = j-1;
				}
			}
			
			for(int j=0;j<n;j++){
				if(matrix[i][j] == '1'){
					height[j]++;
				}else{
					height[j] = 0;
				}
			}
			for(int j=0;j<n;j++){
				maxA = Math.max(maxA, (right[j]-left[j])*height[j]);
			}
		}
		return maxA;
	}

	public static void main(String[] args) {
		char[][] matrix = {
				new char[]{'1','0', '1', '0', '0'},
				new char[]{'1','0', '1', '1', '1'},
				new char[]{'1','1', '1', '1', '1'},
				new char[]{'1','0', '0', '1', '0'}
		};
		int r = new MaximalRectangle85().maximalRectangleV3(matrix);
		System.out.println(r);
		
	}

}

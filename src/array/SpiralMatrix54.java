package array;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix54 {
	/**
	 * 遍历上边、右边、下边、左边；去掉外层一圈，遍历里面一层。
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		if (matrix == null)
			return list;
		int m = matrix.length;
		if (m == 0)
			return list;
		int n = matrix[0].length;

		int dis = 0;
		int count = m * n;
		while (dis <= n / 2 && list.size() < count) {
			int i = 0 + dis;// i
			for (int j = 0 + dis; j < n - dis; j++) {
				list.add(matrix[i][j]);
			}
			int rightj = n - 1 - dis;// 1
			for (int righti = i + 1; righti < m - dis; righti++) {
				list.add(matrix[righti][rightj]);
			}
			int bottomi = m - 1 - dis;// 1
			for (int bottomj = n - 2 - dis; bottomj >= 0 + dis && bottomi > i; bottomj--) {
				list.add(matrix[bottomi][bottomj]);
			}
			int leftj = 0 + dis;
			for (int lefti = m - 2 - dis; lefti > 0 + dis && leftj < rightj; lefti--) {
				list.add(matrix[lefti][leftj]);
			}
			dis++;
		}

		return list;
	}

	/**
	 * 清晰的定义每一次遍历的行、列范围，代码更清楚
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrderV2(int[][] matrix) {
		List<Integer> list = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return list;
		int rowBegin = 0, rowEnd = matrix.length - 1, colBegin = 0, colEnd = matrix[0].length - 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// right
			for (int j = colBegin; j <= colEnd; j++) {
				list.add(matrix[rowBegin][j]);
			}
			rowBegin++;
			//down
			for(int i = rowBegin ; i<=rowEnd;i++){
				list.add(matrix[i][colEnd]);
			}
			colEnd--;
			//left，防止重复
			for(int j = colEnd;j>=colBegin && rowBegin<=rowEnd;j--){
				list.add(matrix[rowEnd][j]);
			}
			rowEnd--;
			
			//up,防止重复
			for(int i = rowEnd;i>=rowBegin && colBegin<=colEnd;i--){
				list.add(matrix[i][colBegin]);
			}
			colBegin++;
		}

		return list;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 2, 5, 8 }, { 4, 0, -1 } };
		List<Integer> list = new SpiralMatrix54().spiralOrderV2(matrix);
		System.out.println(list);
	}
}

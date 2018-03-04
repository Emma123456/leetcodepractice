package array;

import java.util.ArrayList;
import java.util.List;

public class SetMatrixZeroes73 {
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		List<Integer> rowList = new ArrayList<Integer>();
		List<Integer> colList = new ArrayList<Integer>();
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					if (!rowList.contains(i)) {
						rowList.add(i);
					}
					if (!colList.contains(j)) {
						colList.add(j);
					}
				}
			}
		}
		for (int i : rowList) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = 0;
			}
		}
		for (int j : colList) {
			for (int i = 0; i < m; i++) {
				if (!rowList.contains(i)) {
					matrix[i][j] = 0;
				}

			}
		}
	}

	/**
	 * 遍历如果某一行包含元素0，则修改matrix[i][0] =0;如果某一列包含元素0，则修改matrix[0][j]=0;
	 * 但是第0行和第0列会共享同一个空间matrix[0][0]，则需要添加一个变量col0，col0=0表示第0列为0；matrix[0][0]=0则表示第0行为0
	 * @param matrix
	 */
	public void setZeroesV2(int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return;
		int m = matrix.length;
		int n = matrix[0].length;
		int col0 = 1;
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0)
				col0 = 0;
			for (int j = 1; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;// 可能影响0行的判断
				}
			}
		}
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 1; j--) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
			if(col0==0) matrix[i][0] = 0;
		}
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 0, 0, 0, 5 }, { 4, 3, 1, 4 }, { 0, 1, 1, 4 }, { 1, 2, 1, 3 }, { 0, 0, 1, 1 } };
		new SetMatrixZeroes73().setZeroesV2(matrix);
	}
}

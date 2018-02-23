package array;

public class SpiralMatrix59 {
	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int number = 1;
		int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// right
			for (int j = colBegin; j <= colEnd; j++) {
				matrix[rowBegin][j] = number++;
			}
			rowBegin++;
			// down
			for (int i = rowBegin; i <= rowEnd; i++) {
				matrix[i][colEnd] = number++;
			}
			colEnd--;
			// left，防止重复
			for (int j = colEnd; j >= colBegin && rowBegin <= rowEnd; j--) {
				matrix[rowEnd][j] = number++;
			}
			rowEnd--;

			// up,防止重复
			for (int i = rowEnd; i >= rowBegin && colBegin <= colEnd; i--) {
				matrix[i][colBegin] = number++;
			}
			colBegin++;
		}

		return matrix;
	}
}

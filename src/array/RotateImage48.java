package array;

public class RotateImage48 {
	/**
	 * 假设数值>0
	 * 失败
	 * @param matrix
	 */
	public void rotate(int[][] matrix) {
		int m = matrix.length;
		if (m < 1)
			return;
		int n = matrix[0].length;
		int row = m-1;
		int col = 0;
		int count = 0;
		while (row>=0 && col<n && count<m*n) {
			int i = row;
			int j = col;
			int tmp = matrix[i][j];
			if(tmp>0){
				matrix[i][j] = Integer.MIN_VALUE;
			}
			int newRow = j;
			int newCol = m-1 - i;
			while (tmp > 0 ) {
				int tmp2 = matrix[newRow][newCol];
				matrix[newRow][newCol] = -tmp;
				count++;
				tmp = tmp2;
				i = newRow;
				j = newCol;
				newRow = j;
				newCol = m - 1 - i;
			}
			if (row > 0) {
				row--;
			} else if (col < n-1) {
				col++;
				row = m-1;
			}
		}
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				matrix[i][j] = (matrix[i][j]<0?-matrix[i][j]:matrix[i][j]);
			}
		}
	}
	
	/**
	 * 先做转置，再对调matrix[i][j] 与 matrix[i][n-1-j]
	 * @param matrix
	 */
	public void rotateV2(int[][] matrix) {
		int m = matrix.length;
		if (m < 1)
			return;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = i+1; j < n; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n/2; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[i][n-1-j];
				matrix[i][n-1-j] = temp;
			}
		}
	}
	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{5, 1, 9,11},
				{2, 4, 8,10},
				{13, 3, 6, 7},
				{15,14,12,16}
		};
		new RotateImage48().rotateV2(matrix);
		for(int[] val : matrix){
			System.out.println();
			for(int v : val){
				System.out.print(v+"\t");
			}
		}
	}

}

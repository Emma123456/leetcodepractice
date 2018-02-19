package array;

public class ImageSmoother661 {
	public int[][] imageSmoother(int[][] M) {
		if (M == null)
			return null;
		int rows = M.length;
		if (rows == 0)
			return new int[0][];
		int cols = M[0].length;

		int[][] r = new int[rows][cols];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < cols; column++) {
				int count = 0;
				int sum = 0;
				for(int incR : new int[]{-1,0,1}){
					for(int incC : new int[]{-1,0,1}){
						if(isValid(row+incR,column+incC,rows,cols)){
							count++;
							sum += M[row+incR][column+incC];
						}
					}
				}
				r[row][column] = sum/count;
			}
		}

		return r;
	}

	private boolean isValid(int i, int j, int rows, int cols) {
		return i>=0 && j>=0 && i<rows && j<cols;
	}

	public static void main(String[] args) {

	}

}

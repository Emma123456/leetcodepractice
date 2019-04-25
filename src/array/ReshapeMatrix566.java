package array;

public class ReshapeMatrix566 {
	public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums ==null) return nums;
        int initRow = nums.length;
        if(initRow==0) return nums;
        int initCol = nums[0].length;
        if(r*c!=initRow * initCol) return nums;
        
        int[][] result = new int[r][c];
        
        for(int i=0;i<r*c;i++){
            result[i/c][i%c] = nums[i/initCol][i%initCol];
        }
        return result;
    }
	
	 public int[][] matrixReshapeV2(int[][] nums, int r, int c) {
	        if(nums ==null) return nums;
	        int initRow = nums.length;
	        if(initRow==0) return nums;
	        int initCol = nums[0].length;
	        if(r*c!=initRow * initCol) return nums;
	        
	        int[][] result = new int[r][];
	        int idxRow = 0;
	        int idxCol = 0;
	        for(int i=0;i<r;i++){
	            result[i] = new int[c];
	            for(int j=0;j<c;j++){
	                result[i][j] = nums[idxRow][idxCol];
	                idxCol++;
	                if(idxCol==initCol){
	                    idxCol=0;
	                    idxRow++;
	                }
	            }
	        }
	        return result;
	  }

	/**
	 * 二维数组变一维数组 M[i][j]=M[i*n+j],n是列数；
	 * 一维变二维 M[i] = M[i/n][i%n],n是列数；
	 * @param nums
	 * @param r
	 * @param c
	 * @return
	 */
	public int[][] matrixReshapeV3(int[][] nums, int r, int c) {
		int[][] newNums = new int[r][c];
		int m = nums.length;
		if(m==0) return nums;
		int n = nums[0].length;
		if(r*c!=m*n) return nums;
		int[] array = new int[m*n];
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				array[i*n+j]=nums[i][j];
			}
		}

		for(int i=0;i<m*n;i++){
			newNums[i/c][i%c]=array[i];
		}
		return newNums;
	}
}

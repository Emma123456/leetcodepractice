package array;

public class ReshapeMatrix556 {
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
}

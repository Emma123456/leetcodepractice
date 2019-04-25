package array;

public class ToeplitzMatrix766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        if(m==0) return true;
        int n = matrix[0].length;
        for(int row = 0;row<m;row++){
            int val = matrix[row][0];
            for(int i=row,j=0;i<m &&j<n;i++,j++){
                if(matrix[i][j]!=val) return false;
            }
        }
        //上三角
        for(int col = 1;col<n;col++){
            int val = matrix[0][col];
            for(int j=col,i=0;j<n&&i<m;j++,i++){
                if(matrix[i][j]!=val) return false;
            }
        }
        return true;
    }
}

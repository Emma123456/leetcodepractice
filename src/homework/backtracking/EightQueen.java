package homework.backtracking;

import java.util.Arrays;

public class EightQueen {
    private int[] result = new int[8];
    private void cal8Queen(int row){
        if(row==8){
            printQueens(result);
        }else{
            for(int column = 0;column<8;column++){
                if(isOk(row,column)){
                    result[row] = column;
                    cal8Queen(row+1);
                }
            }
        }
    }

    /**
     * 一行，一列，对角线是不是满足
     * @param row
     * @param column
     * @return
     */
    private boolean isOk(int row, int column) {

        for(int i=row-1;i>=0;i--){
            if(result[i] == column) return false;//在同一列相遇了
        }
        //对角线  左上角、右下角
        int leftUp = column-1;
        for(int i=row-1;i>=0 && leftUp>=0;i--){
            if(result[i] == leftUp) return false;//在对角线相遇
            leftUp--;
        }


        //对角线 右上角、左下角
        int rightUp = column+1;
        for(int i=row-1;i>=0 && rightUp<8;i--){
            if(result[i] == rightUp) return false;
            rightUp++;
        }


        return true;
    }

    private void printQueens(int[] result) {
        System.out.println("=============");
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(result[i]==j){
                    System.out.print("Q ");
                }else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){

        EightQueen q = new EightQueen();
        Arrays.fill(q.result,-1);
        q.cal8Queen(0);

    }
}

package book.backtrace;

public class EightQueens {
    public static void main(String[] args){
        new EightQueens().eightQueens();
    }

    public void eightQueens(){
        cal8queens(0);
    }
    private int[] result = new int[8];//下标表示第几行，值表示皇后在第几列
    private void cal8queens(int row) {
        if(row==8){
            printResult();
        }else{
            for(int j=0;j<8;j++){
                if(isOk(row,j)){
                    result[row] = j;
                    cal8queens(row+1);
                }
            }
        }
    }

    private boolean isOk(int row, int col) {
        //不在同一列
        int leftUp = col-1,rightUp=col+1;
        for(int i=row-1;i>=0;i--){
            if(i!=row && result[i]==col) return false;
            if(leftUp>=0 && result[i]==leftUp) return false;
            if(rightUp<8 && result[i]==rightUp) return false;
            leftUp++;
            rightUp--;
        }
        return true;
    }

    private void printResult() {
        for(int i=0;i<result.length;i++){
            System.out.println();
            for(int j=0;j<8;j++){
                if(j==result[i]){
                    System.out.print("Q");
                }else{
                    System.out.print("*");
                }
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}

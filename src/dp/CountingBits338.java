package dp;

public class CountingBits338 {
    public int[] countBits(int num) {
        int[] answers = new int[num+1];
        for(int i=0;i<=num;i++){
            int count = 0;
            int r = i;
            for(int j=0;j<32 && r>0;j++){
                if(j>0){
                    r = r>>1;
                }
                if((r & 1) == 1 ){
                    count++;
                }
            }
            answers[i] = count;
        }
        return answers;
    }
    public int[] countBitsV2(int num) {
        int[] answers = new int[num+1];
        for(int i=1;i<=num;i++){
            answers[i] = answers[i>>1] + (i&1);
        }
        return answers;
    }
}

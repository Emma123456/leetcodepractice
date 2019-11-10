package homework.binarysearch;

public class Sqrt69 {
    public int mySqrt(int x) {
        long start = 0;
        long end = x>1?x>>1:x;
        long answer = start;
        while(start<=end){
            long middle = start+((end-start)>>1);
            //System.out.println(middle);
            long product = middle*middle;
            if(product==(long)x) return (int)middle;
            if(product<x){
                start = middle+1;
                answer = middle;
            }else{
                end = middle -1;
            }
        }
        return (int)answer;
    }
}

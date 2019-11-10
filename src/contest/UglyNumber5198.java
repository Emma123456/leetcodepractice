package contest;

public class UglyNumber5198 {
    public long gcd(long a,long b){
        if(a==0) return b;
        return gcd(b%a,a);
    }

    public long lcm(long a,long b){
        return a*b/gcd(a,b);
    }

    public long divTermCount(long a,long b,long c,long num){
        return num/a + num/b + num/c - num/lcm(a,b)-num/lcm(b,c)-num/lcm(a,c)+num/lcm(a,lcm(b,c));
    }

    /**
     * https://blog.csdn.net/u011240016/article/details/101172990
     * 奥数4个定理：容斥定理
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        long low = 1;
        long high = Math.min(a,Math.min(b,c))*n;
        System.out.println(high);
        while(low<high){
            long middle = low + ((high-low)>>1);
            long count = divTermCount(a,b,c,middle);
            if(count<n){
                low = middle+1;
            }else {
                high = middle;
            }
        }

        return (int)low;

    }
}

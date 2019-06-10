package book.binarysearch;

public class Sqrt {
    /**
     * 牛顿迭代法
     * 求一个数的平方根，精度精确到小数点后六位
     * @param x
     * @return
     */
    public static double sqrt(double x){
        double res = 1;

        while(Math.abs(res*res-x)>0.000001){
            res = (res + x/res)/2;
        }
        return res;
    }

    public static double sqrtByBinary(double x){
        double low = 0;
        double high = x;
        double power = low;
        double answer = low;
        while(Math.abs(power-x)>0.000001){
            double mid = (low+high)/2;
            power = mid*mid;
            if(power>x){
                high = mid-1;
            }else if(power<=x){
                low = mid+1;
                answer = mid;
            }
        }
        return answer;
    }

    public static void main(String[] args){
        System.out.println(sqrt(4));
        System.out.println(sqrt(8));
        System.out.println(sqrt(9));
        System.out.println(sqrt(10));



        System.out.println(sqrtByBinary(4));
        System.out.println(sqrtByBinary(8));
        System.out.println(sqrtByBinary(9));
        System.out.println(sqrtByBinary(10));

    }
}

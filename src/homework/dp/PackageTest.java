package homework.dp;

public class PackageTest {
    public static void main(String[] args){
        int[] weight =  new int[]{2,2,4,6,3};
        int maxWeight = 9;
        Package p = new Package(weight,maxWeight);
        int max = p.takeMaxV2();
        System.out.printf("最多可以放的重量是%d",max);
    }
}

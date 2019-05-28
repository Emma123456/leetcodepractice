package book.sort;

public class CountingSort {
    /**
     * 计数排序，数组中存储的是非负整数
     * 时间复杂度是mMax = Math.max(n,max)  O(mMax)
     * 计数排序的适用场景是数据范围的最大值max 不会比n大很多，所以时间复杂度可以近似为O(n)。
     * 根据年龄给500万用户数据排序，可以使用桶排序，也可以使用计数排序。
     * @param a
     * @param n
     */
    public static void countingSort(int[] a ,int n ){
        if (n<=1) return;
        //查找数组范围
        int max = a[0];
        for(int num : a){
            max = Math.max(num,max);
        }

        //申请一个计数数组c，下标[0,max]
        int[] c = new int[max+1];
        for(int num: a){
            c[num]++;
        }

        //依次累加
        for(int i=1;i<c.length;i++){
            c[i] += c[i-1];
        }

        //临时数组r，存储排序后的结果
        int[] r = new int[n];
        for(int i=n-1;i>=0;i--){
            int k = a[i];
            int index = c[k]-1;
            r[index] = k;
            c[k]--;
        }
        //将结果拷贝到数组a
        for(int i=0;i<n;i++){
            a[i] = r[i];
        }
    }
    public static void main(String[] args){
        int[] a = new int[]{2,5,3,0,2,3,0,3};
        int n = a.length;
        countingSort(a,n);
        for(int i=0;i<n;i++){
            System.out.print(a[i]+"\t");
        }
        System.out.println();
    }
}

package book.divideconquer;

public class ArrayReverseCount {
    private int num = 0;
    public int count(int[] a){
        int n = a.length;
        return mergeSortCounting(a,0,n-1);
    }

    private int mergeSortCounting(int[] a, int start, int end) {
        if(start>=end){
            return 0;
        }
        int q = (start+end)/2;
        int k1 = mergeSortCounting(a,start,q);
        int k2 = mergeSortCounting(a,q+1,end);
        int k3 = merge(a,start,q,end);
        return k1+k2+k3;
    }

    private int merge(int[] a, int start, int q, int end) {
        int[] temp = new int[end-start+1];
        int i= start;
        int j= q+1;
        int k = 0;
        int nums = 0;
        while(i<=q && j<=end){
            if(a[j]<a[i]){
                temp[k++]=a[j++];
                nums += q-i+1;
            }else{
                temp[k++] = a[i++];
            }
        }
        while(i<=q){
            temp[k++] = a[i++];
        }
        while(j<=end){
            temp[k++] = a[j++];
        }
        for(i=0;i<=end-start;i++){
            a[start+i]= temp[i];
        }
        return nums;
    }

    public static void main(String[] args){
        int[] a = new int[]{2,4,3,1,5,6};
        int r = new ArrayReverseCount().count(a);
        System.out.println(r);
    }
}

package homework.divideconquer;

/**
 * 数组逆序对个数
 */
public class ArrayInversePair {
    private int num = 0;
    public int count(int[] a){
        int n = a.length;
        num = 0;

        mergeSortCount(a,0,n-1);
        return num;
    }

    private void mergeSortCount(int[] a, int p, int r) {
        if(p>=r) return;
        int q = (p+r)/2;
        mergeSortCount(a,p,q);
        mergeSortCount(a,q+1,r);
        mergeCount(a,p,q,r);

    }

    private void mergeCount(int[] a, int p, int q, int r) {
        int[] tmp = new int[r-p+1];
        int i =p,j=q+1,k=0;
        while(i<=q && j<=r){
            if(a[i]<a[j]){
                tmp[k++] = a[i++];
            }else{
                num += (q-i+1);
                tmp[k++] = a[j++];
            }
        }
        while(i<=q){
            tmp[k++] = a[i++];
        }

        while(j<=r){
            tmp[k++] = a[j++];
        }

        for(i=0;i<q-p+1;i++){
            a[p+i] = tmp[i];
        }
    }

    public static void main(String[] args){
        int[] a = {2,4,3,1,5,6};
        int n = new ArrayInversePair().count(a);
        System.out.println(n);
    }
}

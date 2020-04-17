package homework2;

public class KMP {
    /**
     * 查找模式串b在a中出现的位置。如果不出现，则返回-1；
     * @param a
     * @param b
     * @return
     */
    public int find(char[] a, char[] b){
        int pos = -1;
        int[] next = findNext(b);
        int n = a.length;
        int m = b.length;
        int j = 0;
        for(int i =0 ;i < n; i++){
            while(j>0 && a[i] !=b[j]){
                j = next[j-1]+1;
            }
            if(a[i] == b[j]){
                j++;
            }
            if(j==m){
                return i-m+1;
            }
        }
        return -1;
    }

    private int[] findNext(char[] b) {
        int m = b.length;
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for(int i = 1;i< m;i++){
            while(k!=-1 && b[i] != b[k+1]){
                k = next[k];
            }
            if(b[k+1] == b[i]){
                k++;
            }
            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        int index = new KMP().find("aabaa".toCharArray(),"ba".toCharArray());
        System.out.println(index);
    }
}

package book.match;

public class KMP {
    // b 表示模式串，m 表示模式串的长度
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        int k = -1;
        next[0] = -1;
        for(int i=1;i<m;i++){
            while(k!=-1 && b[k+1]!=b[i]){
                k = next[k];
            }
            //假设next[i-1]=k,如果模式串S的k+1的字符与S的i是相同的
            if(b[k+1] == b[i]){
                k++;
            }
            next[i] = k;
        }
        return next;
    }

    // a, b 分别是主串和模式串；n, m 分别是主串和模式串的长度。
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b,m);
        int j = 0;
        for(int i=0;i<n;i++){
            //当字符不匹配的时候，将模式串的指针回退到最长可匹配前缀字符的结束下标位置
            while(j>0 && a[i]!=b[j]){
                j= next[j-1]+1;
            }
            //字符匹配的时候
            if(a[i]==b[j]){
                j++;
            }
            if(j==m){
                return i-m+1;
            }
        }
        return -1;
    }
    public static void  main(String[] args){
        String a = "abbaabbaaba";
        String b = "a";
        int p = kmp(a.toCharArray(),a.length(),b.toCharArray(),b.length());
        System.out.println(p);
    }
}

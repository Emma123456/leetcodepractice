package book.match;

import java.util.Arrays;

public class BM {

    public static final int SIZE = 256;
    private int[] bc = new int[SIZE];
    /**
     * 查找到字符串b在a中出现的位置。如果没有出现则返回-1.
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int bm(char[] a,int n,char[] b,int m){
        generateBC(b,m);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);

        int startIdx = 0;
        while(startIdx<=n-m){
            int si = -1;
            char badChar = 0;
            for(int i=m-1;i>=0;i--){
                if(a[startIdx+i] != b[i]){
                    si=i;
                    badChar = a[startIdx+i];
                    break;
                }
            }
            if(si==-1){
               return startIdx;
            }else{
                //bad char在模式串中是否存在
                int xi = bc[badChar];
                int x = si-xi;
                int y =0;
                if(si<m-1){//如果有好后缀
                    y = moveByGS(si,m,suffix,prefix);
                }
                startIdx += Math.max(x,y);
            }
        }
        return -1;
    }

    private int moveByGS(int si, int m, int[] suffix, boolean[] prefix) {
        int k = m-1-si;
        if(suffix[k]!=-1){
            return si - suffix[k]+1;
        }else {
            for(int r = si+2;r<=m-1;r++){
                if(prefix[m-r] == true){
                    return r;
                }
            }
        }
        return m;
    }

    //

    /**
     * b 表示模式串，m 表示长度，suffix，prefix 数组事先申请好了
     * suffix[k]表示长度为k的后缀，在b中出现的最大起始下标
     * prefix[k]表示长度为k的后缀子串，是不是有匹配的前缀子串
     * @param b
     * @param m
     * @param suffix
     * @param prefix
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {

        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        for(int i=0;i<m-1;i++){//b[0,i]是前缀字符串
            int j = i;
            int k = 0 ;
            while(j>=0 && b[j]==b[m-1-k]){//与b[0,m-1]求公共后缀子串
                suffix[k] = j;//j表示公共后缀淄川在b[0,i]中的起始下标
                j--;
                k++;
            }
            if(j==-1){
                prefix[k] = true;//公共后缀子串也是模式串的前缀子串
            }
        }

    }

    /**
     * 记录b中每个字符出现的最后位置
     * @param b
     * @param m
     */
    private void generateBC(char[] b, int m) {
        Arrays.fill(bc,-1);
        for(int i=0;i<m;i++){
            int ch = b[i];
            bc[ch] = i;
        }
    }

    public static void main(String[] args){
        char[] a = "abcxabd".toCharArray();
        char[] b = "abd".toCharArray();
        int pos = new BM().bm(a,a.length,b,b.length);
        System.out.println(pos);


        b = "abbcabc".toCharArray();
        int m = b.length;
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        new BM().generateGS(b,m,suffix,prefix);
        System.out.println(suffix);

        for(int i : suffix){
            System.out.print(i+"\t");
        }
        System.out.println();
        for(boolean i : prefix){
            System.out.print(i+"\t");
        }
        System.out.println();
    }

}

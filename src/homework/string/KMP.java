package homework.string;

public class KMP {

    /**
     * 使用kmp算法求字符串b在字符串a中的起始位置。如果找不到返回-1
     * @param a
     * @param b
     * @return
     */
    public static int kmp(String a, String b){
        char[] achar = a.toCharArray();
        char[] bchar = b.toCharArray();
        int m = bchar.length;

        int[] next = getNexts(bchar,b.length());
        int j = 0;
        for(int i=0;i<achar.length;i++){
            //如果两个字符 不同，则看看子串可以回退到哪个位置。回退之后再比较两个字符是不是相同
            while(j>0 && achar[i] != bchar[j]){
                j = next[j-1]+1;
            }
            //判断两个字符相同，则j++
            if(achar[i] == bchar[j]){
                j++;
            }
            if(j==m){
                return i-m+1;
            }
        }
        return -1;
    }

    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for(int i=1;i<m;i++){
            while(k!=-1 && b[i]!=b[k+1]){
                k = next[k];
            }
            if(b[i]==b[k+1]){
                k++;
            }
            next[i] = k;
        }
        return next;
    }


    public static void main(String[] args){
        String a = "main函数的调用方法";
        String b = "main";
        int r = KMP.kmp(a,b);
        System.out.println(r);
    }
}

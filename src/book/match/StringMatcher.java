package book.match;

public class StringMatcher {
    /**
     * 查找b在a中出现的位置。找不到则返回-1。
     * @param a
     * @param b
     * @return
     */
    public static int indexOf(String a,String b){
        int idx = -1;
        int n = a.length();
        int m = b.length();
        int i=0;
        while(i<n){
            int j = 0;
            int pos = i;
            while(pos<n && j<m && a.charAt(pos)==b.charAt(j)){
                pos++;
                j++;
            }
            if(j==m){
                idx = i;
                break;
            }else{
                i++;
            }
        }
        return idx;
    }

    public static void main(String[] args){
        String a = "ababaeabac";
        String b = "baba";
        int pos = indexOf(a,b);
        System.out.println(pos);
    }
}

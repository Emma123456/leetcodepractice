package book.dp;

public class LevenshteinDistance {
    private char[] a = "sea".toCharArray();
    private char[] b = "eat".toCharArray();
    private int n = a.length;
    private int m = b.length;
    private int minEdist = Integer.MAX_VALUE;
    private void lwstBT(int i,int j,int edist){
        if(i==n || j==m){
            if(j<m) {
                edist += m-j;
            }
            if(i<n){
                edist += n-i;
            }
            minEdist = Math.min(edist,minEdist);
            return;
        }
        if(a[i]==b[j]){
            lwstBT(i+1,j+1,edist);
        }else{
            lwstBT(i+1,j,edist+1);//删除a[i]
            lwstBT(i,j+1,edist+1);//在a[i]前面插入b[j]
            lwstBT(i+1,j+1,edist+1);//修改a[i]=b[j]
        }
    }

    public void lwstBT(){
        lwstBT(0,0,0);
    }


    /**
     * 我认为这个版本的代码是有问题的，homework.dp.LevenshteinDistance版本是正确的。
     * 因为minDist[i][j] 表示到达i,j这个状态的最短编辑距离。那么从(i,j-1)到达(i,j)状态，要不要加1，需要看a[i]是不是等于b[j]。如果等于则不加1。代码中是肯定加1。所以有误
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0){
                    minDist[0][0] = (a[0]==b[0]?0:1);
                }else{
                    minDist[i][j] = Integer.MAX_VALUE;
                    if(i>0 && j>0){
                        minDist[i][j] = Math.min(minDist[i][j],minDist[i-1][j-1]+(a[i]==b[j]?0:1));
                    }
                    if(j>0){
                        minDist[i][j] = Math.min(minDist[i][j],minDist[i][j-1]+1);
                    }

                    if(i>0){
                        minDist[i][j] = Math.min(minDist[i][j],minDist[i-1][j]+1);
                    }
                }
            }
        }

        return minDist[n-1][m-1];
    }


    public int lwstDPV2(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j-1]+1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i-1][0]+1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]);
                else minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]+1);
            }
        }

        for(int i=0;i<a.length;i++){
            for(int j=0;j<b.length;j++){
                System.out.print(minDist[i][j]+"\t");
            }
            System.out.println();
        }
        return minDist[n-1][m-1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }

    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxLcs = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0 && j==0){
                    maxLcs[0][0] = (a[0]==b[0]?1:0);
                }else{
                    maxLcs[i][j] = Integer.MIN_VALUE;
                    if(i>0 && j>0){
                        maxLcs[i][j] = Math.max(maxLcs[i][j],maxLcs[i-1][j-1]+(a[i]==b[j]?1:0));
                    }
                    if(j>0){
                        maxLcs[i][j] = Math.max(maxLcs[i][j],maxLcs[i][j-1]);
                    }

                    if(i>0){
                        maxLcs[i][j] = Math.max(maxLcs[i][j],maxLcs[i-1][j]);
                    }
                }
            }
        }

        return maxLcs[n-1][m-1];
    }
    public static void main(String[] args){
        LevenshteinDistance l =  new LevenshteinDistance();
        l.lwstBT();
        System.out.println(l.minEdist);

        int r = l.lwstDP(l.a,l.a.length,l.b,l.b.length);
        System.out.println(r);


        r = l.lwstDPV2(l.a,l.a.length,l.b,l.b.length);
        System.out.println(r);

        r = l.lcs(l.a,l.a.length,l.b,l.b.length);
        System.out.println(r);
    }
}

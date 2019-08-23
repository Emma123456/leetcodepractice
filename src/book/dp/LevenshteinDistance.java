package book.dp;

public class LevenshteinDistance {
    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
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

        r = l.lcs(l.a,l.a.length,l.b,l.b.length);
        System.out.println(r);
    }
}

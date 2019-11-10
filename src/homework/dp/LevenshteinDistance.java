package homework.dp;

import java.util.Arrays;

public class LevenshteinDistance {
    private char[] a;
    private char[] b;
    private int minDistance;
    public LevenshteinDistance(String a ,String b){
        this.a = a.toCharArray();
        this.b =b.toCharArray();
        this.minDistance = Math.max(a.length(),b.length());
    }

    public int lwstBT(){
        lwstBT(0,0,0);
        return minDistance;
    }

    private void lwstBT(int i, int j, int distance) {
        //退出条件
        if(i==a.length){
            minDistance = Math.min(minDistance,distance+(b.length-j));
            return;
        }
        if(j==b.length){
            minDistance = Math.min(minDistance,distance+(a.length-i));
            return;
        }
        if(a[i]==b[j]){
            lwstBT(i+1,j+1,distance);
        }else{
            lwstBT(i+1,j,distance+1);
            lwstBT(i,j+1,distance+1);
            lwstBT(i+1,j+1,distance+1);
        }

    }

    /**
     * 这个版本有问题。
     * @return
     */
    public int lwstDP(){
        int[][] minEdist = new int[a.length][b.length];//minEdist[i][j]表示到达i，j位置的最短编辑距离
        for(int[] array : minEdist){
            Arrays.fill(array,Integer.MAX_VALUE);
        }

        for(int j=0;j<b.length;j++){
            if(a[0]==b[j]){
                minEdist[0][j] = j;
            }else{
                if(j>0) minEdist[0][j] = minEdist[0][j-1]+1;
                else minEdist[0][j]=1;
            }
        }

        for(int i=1;i<a.length;i++){
            if(a[i] == b[0]){
                minEdist[i][0] = i;
            }else{
                minEdist[i][0] = minEdist[i-1][0]+1;
            }
        }
        for(int i=1;i<a.length;i++){
            for(int j=1;j<b.length;j++){
                if(i==0 && j==0){
                    minEdist[i][j] = (a[i]==b[j]?0:1);
                }else{
                    if(i-1>=0 && j-1>=0){
                        minEdist[i][j] = Math.min(minEdist[i][j],minEdist[i-1][j-1]+(a[i]==b[j]?0:1));
                    }
                    //从(i-1,j)到(i,j)一定有字符删减操作
                    if(i-1>=0){
                        minEdist[i][j] = Math.min(minEdist[i][j],minEdist[i-1][j]+1);
                    }
                    //从(i,j-1)到(i,j)一定有字符删减操作
                    if(j-1>=0){
                        minEdist[i][j] = Math.min(minEdist[i][j],minEdist[i][j-1]+1);
                    }
                }
            }
        }


        //debug
        for(int i=0;i<a.length;i++){
            for(int j=0;j<b.length;j++){
                System.out.print(minEdist[i][j]+"\t");
            }
            System.out.println();
        }

        return minEdist[a.length-1][b.length-1];
    }


    public int lwstDPV2(){
        int[][] minEdist = new int[a.length+1][b.length+1];//minEdist[i][j]表示到达i，j位置并且未处理的最短编辑距离
        for(int[] array : minEdist){
            Arrays.fill(array,Integer.MAX_VALUE);
        }
        for(int i=0;i<=a.length;i++){
            for(int j=0;j<=b.length;j++){
                if(i==0){
                    minEdist[i][j] = j;
                }else if(j==0){
                    minEdist[i][j] = i;
                }else{
                    if(i-1>=0 && j-1>=0){
                        minEdist[i][j] = Math.min(minEdist[i][j],minEdist[i-1][j-1]+(a[i-1]==b[j-1]?0:1));
                    }
                    if(i-1>=0){
                        minEdist[i][j] = Math.min(minEdist[i][j],minEdist[i-1][j]+1);
                    }
                    if(j-1>=0){
                        minEdist[i][j] = Math.min(minEdist[i][j],minEdist[i][j-1]+1);
                    }
                }
            }
        }
        return minEdist[a.length][b.length];
    }



    public static void main(String[] args){
        LevenshteinDistance l =  new LevenshteinDistance("sea","eat");
        l.lwstBT();
        System.out.println(l.minDistance);

        System.out.println(l.lwstDP());
        System.out.println(l.lwstDPV2());
    }
}

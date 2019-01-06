package bfs;

import java.util.*;

public class ShortestBridgeV2 {
    public int shortestBridge(int[][] A){
        int rows = A.length,cols = A[0].length;
        int[][] colors = getComponent(A);
        Queue<Node> queue = new LinkedList<Node>();
        Set<Integer> target = new HashSet<Integer>();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(colors[i][j]==1){
                    queue.offer(new Node(i,j,0));
                }else if(colors[i][j]==2){
                   target.add(i*cols+j);
                }
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Node node = queue.poll();
                if(target.contains(node.r*cols+node.c)){
                    return node.depth-1;
                }
                for(int newNode : neighbors(rows,cols,node.r,node.c)){
                    int nr = newNode/cols,nc = newNode%cols;
                    if(colors[nr][nc]!=1){
                        queue.offer(new Node(nr,nc,node.depth+1));
                        colors[nr][nc]=1;
                    }
                }
            }
        }
        return -1;
    }

    private int[][] getComponent(int[][] A) {
        int rows = A.length,cols = A[0].length;
        int[][] colors = new int[rows][cols];
        int flag = 1;
        for(int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if(colors[i][j]== 0 && A[i][j] ==1){
                    //start dfs
                    Stack<Integer> stack = new Stack<Integer>();
                    stack.push(i*cols+j);
                    colors[i][j] = flag;
                    while(!stack.isEmpty()){
                        int node = stack.pop();
                        int r = node/cols,c = node%cols;
                        for(int newNode : neighbors(rows,cols,r,c)){
                            int nr = newNode/cols,nc = newNode%cols;
                            if(A[nr][nc]==1 && colors[nr][nc]==0){
                                stack.push(newNode);
                                colors[nr][nc] = flag;
                            }
                        }
                    }
                    flag++;
                }
            }
        }
        return colors;
    }

    private List<Integer> neighbors(int rows,int cols,int r,int c){
        List<Integer> ans = new ArrayList();
        if(r-1>=0) ans.add((r-1)*cols+c);
        if(r+1<rows) ans.add((r+1)*cols+c);
        if(c-1>=0) ans.add(r*cols+c-1);
        if(c+1<cols) ans.add(r*cols+c+1);
        return ans;
    }

    class Node {
        int r, c, depth;
        Node(int r, int c, int d) {
            this.r = r;
            this.c = c;
            depth = d;
        }
    }

    public static void main(String[] args){
        int[][] A = new int[][]{{0,1},{1,0}};
        new ShortestBridgeV2().shortestBridge(A);
    }

}

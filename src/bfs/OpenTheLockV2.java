package bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/open-the-lock/discuss/192729/Java-BFS-%2B-Bit-Manipulation
 * 用二进制的方式改变状态，速度更快，但是需要理解是怎么做的
 * 结果不对
 */
public class OpenTheLockV2 {
    private final static int[] increments = new int[]{ 1, -1 };

    public int openLock(String[] deadends, String target) {
        int step = 0;
        Set<Integer> used = new HashSet<Integer>();
        String start = "0000";
        int startInt = 0;
        int targetInt = buildState(target);
        for(String deadend : deadends){
            used.add(buildState(deadend));
        }
        if(used.contains(startInt) || used.contains(targetInt)) return -1;
        used.add(startInt);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(startInt);

        while(!queue.isEmpty()){
            int size =queue.size();
            for(int i=0;i<size;i++){
                int nodeInt = queue.poll();
                if(nodeInt==targetInt) return step;
                for(int increment : increments){
                    for(int j =0;j<4;j++){
                        int newNode = updateState(nodeInt,j,increment);
                        if(!used.contains(newNode)){
                            used.add(newNode);
                            queue.offer(newNode);
                        }
                    }
                }
            }

            step++;
        }

        return -1;
    }




    private int updateState(int state, int d, int inc) {
        int mask = (1 << 4) - 1;
        int[] num = new int[]{
                (state >> 12) & mask,
                (state >> 8) & mask,
                (state >> 4) & mask,
                state & mask
        };
        int n = num[d];
        if (n == 0 && inc == -1) {
            num[d] = 9;
        } else if (n == 9 && inc == 1) {
            num[d] = 0;
        } else {
            num[d] += inc;
        }
        int res = 0;
        for (int i = 0; i<4; i++) {
            res <<= 4;
            res |= num[i];
        }
        return res;
    }

    private int buildState(String state) {
        char[] c = state.toCharArray();
        int res = 0;
        for (int i = 0; i < c.length; i++) {
            int d = c[i] - '0';
            res <<= 4;
            res |= d;
        }
        return res;
    }

    public void test(){

    }
    public static void main(String[] args){
        OpenTheLockV2 openLock = new OpenTheLockV2();
        int r = openLock.openLock(new String[]{"0201","0101","0102","1212","2002"},"0000");
        System.out.println(r);

        Map<String,String> doc = new HashMap<String,String>();
        doc.put("fileType","doc");
        doc.put("aa","aaa");
        doc.put("bbb","bbbbb");

        Map<String,String> dataMap = new HashMap<String,String>(doc);
        dataMap.put("fileType","pdf");

        System.out.println(dataMap);


        //做一件事情。node="1234"，转为int这个int ，从左开始， 最前4位=1，5-8位=2，9-12位=3，13-16位=4，
        String node = "1234";
        char[] c = node.toCharArray();
        int nodeInt = 0;
        for(int i=0;i<c.length;i++){
            int d = c[i] -'0';
            nodeInt <<=4 ;
            nodeInt |= d;
        }

        //将1变位2；
        int mask = (1 << 4) - 1;
        int[] num = new int[]{
                  (nodeInt >> 12) & mask
                ,(nodeInt >> 8) & mask
                ,(nodeInt >> 4) & mask
                ,nodeInt & mask
        };
        for(int a : num){
            System.out.println(a);
        }

        num[3] +=1 ;
        int res = 0;
        for (int i = 0; i <4 ; i++) {
            res <<= 4;
            res |= num[i];
        }
        System.out.println(nodeInt);
        System.out.println(res);
    }

}

package bfs;

import java.util.*;

/**
 * 在节点变化上可以优化：https://leetcode.com/problems/open-the-lock/discuss/192729/Java-BFS-%2B-Bit-Manipulation
 */
public class OpenTheLock752 {
    public int openLock(String[] deadends, String target) {
        List<String> deadEndList = new ArrayList<String>();
        for(String str : deadends){
            deadEndList.add(str);
        }
        int step = 0;
        Queue<String> queue = new LinkedList<String>();
        Set<String> set = new HashSet<String>();
        String start = "0000";
        if(!deadEndList.contains(start)){
            queue.add(start);
            set.add(start);
        }
        for(String str :deadEndList){
            if(str.equals(start)){
                return -1;
            }
            set.add(str);
        }

        while(!queue.isEmpty()){

            int size = queue.size();
            for(int i=0;i<size;i++){
                String node = queue.poll();
                if(node.equals(target)){
                    return step;
                }
                //变换
                for(int j=0;j<4;j++){
                    int val = node.charAt(j)-48;
                    int newVal = (val==9?0:val+1);
                    int newVal1 = (val==0?9:val-1);
                    String newNode = node.substring(0,j)+newVal+node.substring(j+1);

                    if(!set.contains(newNode)){
                        queue.offer(newNode);
                        set.add(newNode);
                    }

                    String newNode1 = node.substring(0,j)+newVal1+node.substring(j+1);
                    if(!set.contains(newNode1)){
                        queue.offer(newNode1);
                        set.add(newNode1);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    public static void main(String[] args){
        String node ="0110";
        int state = buildState(node);
        System.out.println(state);
    }

    private static int buildState(String state) {
        char[] c = state.toCharArray();
        int res = 0;
        for (int i = 0; i < c.length; i++) {
            int d = c[i] - '0';
            res <<= 4;
            res |= d;
        }
        return res;
    }
}

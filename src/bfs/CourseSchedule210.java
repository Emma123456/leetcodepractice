package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从不需要前提条件的课程开始
 */
public class CourseSchedule210 {
    private List<Integer> result = new ArrayList<Integer>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        result = new ArrayList<Integer>();
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }

        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == false && inDegree[i] == 0) {
                result.add(i);
                if (!dfs(graph, i, inDegree, visited)) {
                    break;
                }
            }
        }
        if (result.size() == numCourses) {
            int[] r = new int[numCourses];
            for(int i=0;i<numCourses;i++){
                r[i] = result.get(i);
            }
            return r;
        } else {
            return new int[]{};
        }
    }

    private boolean dfs(List<List<Integer>> graph, int num, int[] inDegree, boolean[] visited) {
        if(visited[num]) return false;
        visited[num] = true;
        for(Integer toCourse:graph.get(num)){
            inDegree[toCourse]--;
            if(inDegree[toCourse]==0){
                result.add(toCourse);
                if(!dfs(graph,toCourse,inDegree,visited)){
                    return false;
                }
            }

        }
        return true;
    }


    /**
     * bfs
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrderV2(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int idx = 0;
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }

        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                Integer course = queue.poll();
                result[idx++]=course;
                for(Integer toCourse : graph.get(course)){
                    inDegree[toCourse]--;
                    if(inDegree[toCourse]==0){
                        queue.offer(toCourse);
                    }
                }
            }
        }
        return idx == numCourses?result:new int[]{};
    }
}

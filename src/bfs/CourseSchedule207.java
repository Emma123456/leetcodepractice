package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule207 {
    /**
     * 思路：从课程i开始，前置课程是j，则查找j是否可以学习。DFS思路。这个图是从需要学习的课程指向前置条件，检测图中是不是有环。
     * 技巧在于visited[i] = 0 未处理；visited[i] = 1 可以学习；visited[i] = 2 正在处理中；visited[i] = -1 不能学习；
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,List<Integer>> prerequisiteMap = new HashMap<Integer,List<Integer>>();
        for(int i=0;i<numCourses;i++){
            prerequisiteMap.put(i,new ArrayList<Integer>());
        }
        int n = prerequisites.length;
        for(int i=0;i<prerequisites.length;i++){
            int target = prerequisites[i][0];
            for(int j=1;j<prerequisites[i].length;j++){
                prerequisiteMap.get(target).add(prerequisites[i][j]);
            }
        }

        int[] visited = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if(visited[i]==0){
                if(!dfs(prerequisiteMap,i,visited)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dfs(Map<Integer,List<Integer>> prerequisiteMap , int targetCourse,int[] visited){
        if(visited[targetCourse]==2){
            visited[targetCourse] = -1;
            return false;
        }
        if(visited[targetCourse]!=0){
            return visited[targetCourse]==1;
        }

        visited[targetCourse] = 2;
        for(int fromCourse : prerequisiteMap.get(targetCourse)){
            if(!dfs(prerequisiteMap,fromCourse,visited)){
                visited[targetCourse] = -1;
                return false;
            }
        }
        visited[targetCourse] = 1;
        return true;
    }


    /***
     * 先找不需要条件就可以开始学习的课程，图是从 课程i指向能达到的课程j
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishV2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        int[] visited = new int[numCourses];
        for(int i=0;i<numCourses;i++){
            if(!dfsStudy(i,graph,visited)) return false;
        }
        return true;
    }

    private boolean dfsStudy(int index, List<List<Integer>> toList, int[] visited) {
        if(visited[index]==2) return true;
        if(visited[index]==1) return false;
        visited[index]=1;
        for(Integer toCourse : toList.get(index)){
            if(!dfsStudy(toCourse,toList,visited)) return false;
        }
        visited[index]=2;
        return true;
    }
}

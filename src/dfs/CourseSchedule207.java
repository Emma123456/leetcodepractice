package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule207 {
	/**
	 * DFS 先建立好有向图，然后从第一个门课开始，找其可构成哪门课，暂时将当前课程标记为已访问，然后对新得到的课程调用DFS递归，直到出现新的课程已经访问过了，则返回false，没有冲突的话返回true，然后把标记为已访问的课程改为未访问。 
	 * O(n)
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] visited = new int[numCourses];
		List<List<Integer>> graph = new ArrayList<List<Integer>>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(graph, visited, i)) {
				return false;
			}
		}
		return true;
	}

	private boolean dfs(List<List<Integer>> graph, int[] visited, int index) {
		if(visited[index]==1) return false;
		if(visited[index]==2) return true;
		visited[index] = 1;
		for (Integer toCourse : graph.get(index)) {
			if (!dfs(graph, visited, toCourse)) {
				return false;
			}
		}
		visited[index] = 2;
		return true;
	}

	/**
	 * BFS O(n)
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinishV2(int numCourses, int[][] prerequisites) {
		int[] inDegree = new int[numCourses];
		List<List<Integer>> graph = new ArrayList<List<Integer>>(numCourses);
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			inDegree[prerequisites[i][0]]++;
			graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < numCourses; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		int count = 0;
		while (!queue.isEmpty()) {
			int course = queue.poll();
			count++;
			for (Integer toCourse : graph.get(course)) {
				inDegree[toCourse]--;
				if (inDegree[toCourse] == 0) {
					queue.add(toCourse);
				}
			}
		}

		return count == numCourses;
	}
	
	/**
	 * 基本上是 canFinishV2 的递归版本，这个版本速度更快
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinishV3(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        boolean[] visited = new boolean[numCourses];
        List<List<Integer>> graph = new ArrayList<List<Integer>>(numCourses);
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<prerequisites.length;i++){
            inDegree[prerequisites[i][0]]++;
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for(int i=0;i<numCourses;i++){
            if(visited[i]==false && inDegree[i]==0){
                if(!bfs(graph,visited,inDegree,i)){
                    return false;
                }
            }
        }
        for(int i=0;i<numCourses;i++){
            if(visited[i]==false) return false;
        }
        return true;
    }
	
	private boolean bfs(List<List<Integer>> graph,boolean[] visited,int[] inDegree,int index){
        if(visited[index]) return false;
        visited[index] = true;
        for(Integer toCourse : graph.get(index)){
            inDegree[toCourse]--;
            if(inDegree[toCourse]==0){
                if(!bfs(graph,visited,inDegree,toCourse)){
                    return false;
                }
            }
            
        }
        return true;
    }
}

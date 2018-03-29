package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler664 {
	/**
	 * 
	 * 每次应该选择剩余总数多的那个任务
	 * 没有考虑 n=0
	 * 没有考虑  每个任务数量不同的情况
	 * 思路：在本时刻能执行的任务中选择数量多的任务执行
	 * 超时
	 * @param tasks
	 * @param n
	 * @return
	 */
	public int leastInterval(char[] tasks, int n) {
        Map<Character,Integer> intervals = new HashMap<Character,Integer>();
        Map<Character,Integer> countMap = new HashMap<Character,Integer>();
        for(char ch : tasks){
            if(countMap.get(ch)==null){
                countMap.put(ch,1);
            }else{
                countMap.put(ch,countMap.get(ch)+1);
            }
        }
        int time = 0;
        while(!countMap.isEmpty()){
            //执行一次任务
            Character selectedTask = null;
            int taskCount = 0;
            for(Character task : countMap.keySet()){
                if(intervals.get(task)==null || intervals.get(task)<=0){
                	if(selectedTask==null){
                		selectedTask = task;
                		taskCount = countMap.get(task);
                	}else if(countMap.get(task)>taskCount){
                		selectedTask = task;
                		taskCount = countMap.get(task);
                	}
                }
            }
            if(selectedTask!=null){
            	//可以执行task
                intervals.put(selectedTask,n);
                if(countMap.get(selectedTask)==1){
                    countMap.remove(selectedTask);
                }else{
                    countMap.put(selectedTask,countMap.get(selectedTask)-1);    
                }
            }
            //System.out.print(selectedTask+"\t");
            //其余任务的intervals需要减1
            for(Character task : intervals.keySet()){
                if(selectedTask ==null || task!=selectedTask){
                    intervals.put(task,intervals.get(task)-1);
                }              
            }
            time++;
        }
        return time;
    }
	/**
	 * 思路：计算每个不同任务的数量。在达到n之前，优先选择数量多的任务。
	 * 例如 3个A  1个B  1个C 1个D 1个E ，n=2,最好的顺序是A->B->C->A->D->E->A->null->null->A
	 * 这里很神奇的地方是：只与不同任务的数量有关系，而与任务名称没有关系。用一个一个的数字表示不同的任务。这是我之前一直转不过弯的地方。
	 * @param tasks
	 * @param n
	 * @return
	 */
	public int leastIntervalV2(char[] tasks, int n) {
		int[] map = new int[26];
		for(char ch : tasks){
			map[ch-'A']++;
		}
		PriorityQueue < Integer > queue = new PriorityQueue < > (26, Collections.reverseOrder());
		for(int f : map){
			if(f>0){
				queue.add(f);
			}
		}
		int time = 0;
		while(!queue.isEmpty()){
			int i =0 ;
			List<Integer> tmp = new ArrayList<Integer>();
			while (i <= n) {
				if (!queue.isEmpty()) {
					if (queue.peek() > 1) {
						tmp.add(queue.poll() - 1);
					} else {
						queue.poll();
					}
				}
				time++;
				if(queue.isEmpty() && tmp.isEmpty()){
					break;
				}
				i++;
			}
			for(Integer l : tmp){
				queue.add(l);
			}
		}
		return time;
	}
	public int leastIntervalV3(char[] tasks, int n) {
		int[] map = new int[26];
		for (char ch : tasks) {
			map[ch - 'A']++;
		}
		int time = 0;
		Arrays.sort(map);
		while (map[25] > 0) {
			int i = 0 ;
			while(i<=n){
				if(i<26 && map[25-i]>0){
					map[25-i]--;
				}
				time++;
				if(map[25] == 0){
					break;
				}
				i++;
			}
			Arrays.sort(map);
		}
		return time;
	}
	/**
	 * 填充空格的思路
	 * @param tasks
	 * @param n
	 * @return
	 */
	public int leastIntervalV4(char[] tasks, int n) {
		int[] map = new int[26];
		for (char ch : tasks) {
			map[ch - 'A']++;
		}
		Arrays.sort(map);
		int idleSlot = (map[25] - 1) * n;//最后一行不需要空格
		int maxVal = map[25] - 1;
		for (int i = 24; i >= 0 && map[i] > 0; i--) {
			idleSlot -= Math.min(map[i], maxVal);
		}
		return idleSlot > 0 ? idleSlot + tasks.length : tasks.length;
	}
	public static void main(String[] args) {
		char[] tasks = new char[]{'A','A','A','A','A','B','C','D','E'};
		int n = 2;
		int r = new TaskScheduler664().leastIntervalV4(tasks, n);
		System.out.println(r);
	}

}

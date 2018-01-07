package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExclusiveTimeofFunctions636 {
	/**
	 * 代码写不对，是因为没有考虑所有的情况
	 * 1:start:2   前面一句是start怎么处理？前面一句是end怎么处理？
	 * 0:end:6 前面一句是start怎么处理？前面一句是end怎么处理？
	 * @param n
	 * @param logs
	 * @return
	 */
	public int[] exclusiveTime(int n, List<String> logs) {
		Stack<Integer> functionIdStack = new Stack<Integer>();
		int[] costTime = new int[n];
		int preTime = -1;
		boolean start = false;
		for(String log : logs){
			String[] strs = log.split(":");
			int functionId = Integer.parseInt(strs[0]);
			int time = Integer.parseInt(strs[2]);
			if("start".equals(strs[1])){
				if(start){
					costTime[functionIdStack.peek()] += time - preTime ;
				}else if(!functionIdStack.isEmpty()){
					costTime[functionIdStack.peek()] += time - preTime -1;
				}
				start = true;
				functionIdStack.push(functionId);
			}else{
				if(start){
					costTime[functionId]+= time - preTime + 1;
				}else{
					costTime[functionId]+= time -  preTime;
				}
				functionIdStack.pop();
				start = false;
			}
			preTime = time;
		}
		return costTime;
    }
	
	/**
	 * pre 在start和end做了不同处理
	 * @param n
	 * @param logs
	 * @return
	 */
	public int[] exclusiveTimeV2(int n, List<String> logs) {
	    // separate time to several intervals, add interval to their function
	    int[] result = new int[n];
	    Stack<Integer> st = new Stack<>();
	    int pre = 0;
	    // pre means the start of the interval
	    for(String log: logs) {
	        String[] arr = log.split(":");
	        if(arr[1].equals("start")) {
	            if(!st.isEmpty())  result[st.peek()] += Integer.parseInt(arr[2]) - pre;
	            // arr[2] is the start of next interval, doesn't belong to current interval.
	            st.push(Integer.parseInt(arr[0]));
	            pre = Integer.parseInt(arr[2]);
	        } else {
	            result[st.pop()] += Integer.parseInt(arr[2]) - pre + 1;
	            // arr[2] is end of current interval, belong to current interval. That's why we have +1 here
	            pre = Integer.parseInt(arr[2]) + 1;
	            // pre means the start of next interval, so we need to +1
	        }
	    }
	    return result;
	}
	public static void main(String[] args) {
		int n = 2;
		List<String> logs = new ArrayList<String>();
		logs.add("0:start:0");
		logs.add("0:start:2");
		logs.add("0:end:5");
		logs.add("0:start:6");
		logs.add("0:end:6");
		logs.add("0:end:7");
		
		int[] r = new ExclusiveTimeofFunctions636().exclusiveTimeV2(n, logs);
		for(int v : r){
			System.out.print(v+"\t");
		}
		System.out.println();
	}

}

package stack;

import java.util.Stack;

public class DailyTemperatures739 {
	/**
	 * 直觉版
	 * @param temperatures
	 * @return
	 */
	public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        for(int i=0;i<temperatures.length;i++){
        	int days = 0;
        	int j=i+1;
        	for(;j<temperatures.length && temperatures[i]>=temperatures[j];j++){
        		days++;
        	}
        	result[i] = (i==temperatures.length-1?days:(j==temperatures.length?0:days+1));
        }
        return result;
    }
	/**
	 * 自己也想过放栈里面，但是想着想着就不知道怎么做了，看着别人这么做，觉得好简单
	 * 
	 * @param temperatures
	 * @return
	 */
	public int[] dailyTemperaturesV2(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<temperatures.length;i++){
        	while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
        		int idx = stack.pop();
        		result[idx] = i - idx;
        	}
        	stack.push(i);
        }
        
        return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] t= new int[]{73,74,75,71,69,72,76,73};
		int[] r = new DailyTemperatures739().dailyTemperaturesV2(t);
		for(int a : r){
			System.out.print(a+" ");
		}
	}

}

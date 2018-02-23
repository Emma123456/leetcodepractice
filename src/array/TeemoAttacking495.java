package array;

public class TeemoAttacking495 {
	/**
	 * 根据题目含义，这是要处理重复线段的问题。
	 * 例如[1,2], 2；第一次起始时间是1，结束时间是3（或者2s的末尾）；第二次起始时间是2，结束时间是4（或者3s的末尾）
	 * 用第一次的结束时间-第二次的起始时间，就是重复的时间
	 * @param timeSeries
	 * @param duration
	 * @return
	 */
	 public int findPoisonedDuration(int[] timeSeries, int duration) {
	        if(timeSeries==null || timeSeries.length==0 || duration == 0) return 0;
	        int sum = duration;
	        for(int i=1;i<timeSeries.length;i++){
	            int lastEnd = timeSeries[i-1]+duration;
	            sum += duration;
	            if(lastEnd > timeSeries[i]){
	                sum -= (lastEnd-timeSeries[i]);
	            }
	        }
	        return sum;
	    }
}

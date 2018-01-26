package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DegreeOfArray697 {
	/**
	 * TLE
	 * @param nums
	 * @return
	 */
	public int findShortestSubArray(int[] nums) {
		// 数组的度
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		int degree = 0;
		for (int num : nums) {
			if (countMap.get(num) == null) {
				countMap.put(num, 1);
			} else {
				countMap.put(num, 1 + countMap.get(num));
			}
			degree = Math.max(degree, countMap.get(num));
		}

		// 找子数组
		int minlength = nums.length;
		for (int start = 0; start < nums.length; start++) {
			Map<Integer, Integer> subCoutMap = new HashMap<Integer, Integer>();
			int subDegree = 0;
			for (int end = start; end < nums.length; end++) {
				int num = nums[end];
				if (subCoutMap.get(num) == null) {
					subCoutMap.put(num, 1);
				} else {
					subCoutMap.put(num, 1 + subCoutMap.get(num));
				}
				subDegree = Math.max(subDegree, subCoutMap.get(num));
				if(subDegree == degree){
					minlength = Math.min(end-start+1, minlength) ;
					break;
				}
			}
		}
		return minlength;
	}
	
	/**
	 * 符合条件的子数组肯定包含频率最高的元素，但是当有多个频率最高的元素的时候就要比较哪个更短
	 * Input:
			[2,1,1,2,1,3,3,3,1,3,1,3,2]
		Output:
			10
		Expected:
		7
	 * @param nums
	 * @return
	 */
	public int findShortestSubArrayV2(int[] nums) {
		// 数组的度
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		int degree = 0;
		for (int num : nums) {
			if (countMap.get(num) == null) {
				countMap.put(num, 1);
			} else {
				countMap.put(num, 1 + countMap.get(num));
			}
			degree = Math.max(degree, countMap.get(num));
		}
		List<Integer> elementList = new ArrayList<Integer>();
		for(Integer num : countMap.keySet()){
			if(countMap.get(num)==degree){
				elementList.add(num);
			}
		}

		int minLength = nums.length;
		for(int element : elementList){
			int subDegree = 0;
			int start = -1;
			for (int i = 0; i < nums.length; i++) {
				if(nums[i] == element){
					if(start == -1){
						start = i;
					}
					subDegree++;
					if(subDegree == degree){
						minLength = Math.min(minLength, i-start+1);
						break;
					}
				}
			}
		}
		return minLength;
	}

	public int findShortestSubArrayV3(int[] nums) {
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer[]> numIndexMap = new HashMap<Integer, Integer[]>();
		int degree = 0;
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (countMap.get(num) == null) {
				countMap.put(num, 1);
			} else {
				countMap.put(num, 1 + countMap.get(num));
			}
			degree = Math.max(degree, countMap.get(num));
			if(numIndexMap.get(num)==null){
				numIndexMap.put(num, new Integer[]{i,i});
			}else{
				numIndexMap.get(num)[1] = i;
			}
		}
		int minLength = nums.length;
		for(int num : countMap.keySet()){
			if(countMap.get(num) == degree){
				minLength = Math.min(minLength, numIndexMap.get(num)[1] - numIndexMap.get(num)[0]+1);
			}
		}
		return minLength;
	}
	
	/**
	 * 先把数据准备好，再计算值
	 * @param nums
	 * @return
	 */
	public int findShortestSubArrayV4(int[] nums) {
		if (nums.length == 0 || nums == null) return 0;
		Map<Integer, int[]> numMap = new HashMap<Integer, int[]>();
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (numMap.get(num) == null) {
				numMap.put(num, new int[]{1,i,i});
			} else {
				int[] temp = numMap.get(num);
				temp[0]++;
				temp[2]=i;
			}
		}
		int degree = 0;
		int minLength = nums.length;
		for(int[] values : numMap.values()){
			if(degree < values[0]){
				degree = values[0];
				minLength = values[2]-values[1] +1;
			}else if(degree == values[0]){
				minLength = Math.min(minLength, values[2]-values[1] +1);
			}
		}
		return minLength;
	}
	public static void main(String[] args) {
		int[] nums = new int[]{2,1,1,2,1,3,3,3,1,3,1,3,2};
		int r = new DegreeOfArray697().findShortestSubArrayV3(nums);
		System.out.println(r);
	}

}

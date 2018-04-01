package array;

import java.util.Arrays;
import java.util.List;

public class MaximumSwap670 {
	/**
	 * 先把整数分解成一个一个的数，从0-n放着从最低位到最高位的数字
	 * 假设要替换的是最高位n-1,从0到n-2中查找是否有比nums[n-1]大的元素；如果有则替换，否则继续考虑替换n-2位。
	 * 比较的时候一定要从0位开始，因为如果 第1,2下标元素相同，把第1位的元素换到高位数字更大。样例数据1993，替换以后是9913。
	 * 
	 * @param num
	 * @return
	 */
	public int maximumSwap(int num) {
		int[] nums = new int[10];
		int n = 0;
		while (num > 0) {
			nums[n++] = num % 10;
			num = num / 10;
		}
		int switchedIndex = n-1;
		while(switchedIndex>=0){
			int max = nums[switchedIndex];
			int maxValOfIndex = switchedIndex;
			//需要首先和低位的元素替换
			for (int i = 0; i <switchedIndex ; i++) {
				if (nums[i] > max) {
					max = nums[i];
					maxValOfIndex = i;
				}
			}
			if(maxValOfIndex!=switchedIndex){
				nums[maxValOfIndex] = nums[switchedIndex];
				nums[switchedIndex] = max;
				break;
			}else{
				switchedIndex--;
			}
		}
		int i = n-1;
		int val = 0;
		while(i>=0){
			val = val*10+nums[i];
			i--;
		}
		return val;
	}

	/**
	 * 桶排序
	 * 首先，记录0-9这十个数字在num中的最低位数。
	 * 其次，遍历每个位置，找一找从9到digits[i]+1这几个数字中是不是存在可以替换的数值
	 * @param num
	 * @return
	 */
	public int maximumSwapV2(int num) {
		char[] digits = Integer.toString(num).toCharArray();
		//每个数字的最高位
		int[] nums = new int[10];
		for(int i=0;i<digits.length;i++){
			nums[digits[i]-'0'] = i;
		}
		//遍历每个位置，看是否存在一个数值大于这个位置的数值
		for (int i = 0; i < digits.length; i++) {
			for (int k = 9; k >digits[i]-'0'; k--) {
				if(nums[k]>i){
					char tmp = digits[i];
					digits[i] = digits[nums[k]];
					digits[nums[k]] =  tmp;
					return Integer.valueOf(new String(digits));
				}
			}
		}
		return num;
	}
	public static void main(String[] args) {
		List<Integer> numList = Arrays.asList(9973,2763,9913,1993,0);
		for(int num : numList){
			int r = new MaximumSwap670().maximumSwapV2(num);
			System.out.println(r);
		}
		
	}

}

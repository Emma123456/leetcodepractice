package array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * 杨辉三角形的性质
 * 1 杨辉三角形以正整数构成，数字左右对称，每行由1主键变大，然后变小，变回1；
 * 2 第n行，数字个数为n；
 * 3 第n行，第k个数字为组合数C (n-1)取(k-1)
 * 4 除每行最左侧与最右侧元素外，每个数字等于它的左上方与右上方数字之和。
 * 顶层被称为第0层
 * @author Administrator
 *
 */
public class PascalsTriangle118 {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>(numRows);
		for (int level = 0; level < numRows; level++) {
			List<Integer> values = new ArrayList<Integer>(level+1);
			values.add(1);
			//中间的元素,下标从1到level-1
			for(int idx = 1;idx<level;idx++){
				values.add(result.get(level-1).get(idx-1)+result.get(level-1).get(idx));
			}
			if(level>0){
				values.add(1);
			}
			result.add(values);
		}
		return result;
	}

	public List<List<Integer>> generateV2(int numRows) {
		List<List<Integer>> result = new ArrayList<List<Integer>>(numRows);
		if(numRows == 0){
			return result;
		}
		List<Integer> v1 = new LinkedList<Integer>();
		v1.add(1);
		result.add(v1);
		for (int level = 1; level < numRows; level++) {
			LinkedList<Integer> values = new LinkedList<Integer>();
			List<Integer> list = result.get(level - 1);
			values.addFirst(1);
			//中间的元素,下标从1到level-1
			for(int idx = 1;idx<level;idx++){
				values.addFirst(list.get(idx-1)+list.get(idx));
			}
			values.addFirst(1);
			result.add(values);
		}
		return result;
	}
	public static void main(String[] args) {

	}

}

package array;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle119 {
	public List<Integer> getRow(int rowIndex) {
		List<Integer> ans = new ArrayList<Integer>();
		if(rowIndex==0){
			return ans;
		}
		ans.add(1);
		for(int level = 1;level<rowIndex;level++){
			List<Integer> last = ans;
			ans = new ArrayList<Integer>();
			ans.add(1);
			//中间的元素,下标从1到level-1
			for(int idx = 1;idx<level;idx++){
				ans.add(last.get(idx-1)+last.get(idx));
			}
			ans.add(1);
		}
		return ans;
    }
	public static void main(String[] args) {
		
	}
}

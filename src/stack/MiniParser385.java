package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MiniParser385 {
	/**
	 * 分析：遇到每个字符应该做什么操作
	 * 本题和394题目很类似
	 * @param s
	 * @return
	 */
	public NestedInteger deserialize(String s) {
		Stack<NestedInteger> listNestedIntegers = new Stack<NestedInteger>();//存放的是list类型的NestedInteger
		int i = 0;
		int factor = 1;
		while(i<s.length()){
			char ch = s.charAt(i);
			if(ch == '['){
				listNestedIntegers.push(new NestedInteger());
			}else if(ch==']'){
				if(listNestedIntegers.size()>1){
					NestedInteger top = listNestedIntegers.pop();
					listNestedIntegers.peek().add(top);
				}
			}else if(ch>='0' && ch<='9'){
				int start = i;
				while(i+1 < s.length() && s.charAt(i+1)>='0' && s.charAt(i+1)<='9') {
					i++;
				}
				Integer value = Integer.parseInt(s.substring(start,i+1)) * factor;
				if(listNestedIntegers.isEmpty()){
					listNestedIntegers.push(new NestedInteger(value));
				}else{
					listNestedIntegers.peek().add(new NestedInteger(value));
				}
				factor = 1;
			}else if (ch=='-'){
				factor = -1;
			}
			i++;
		} 
		return listNestedIntegers.peek();
    }
	
	
	/**
	 * 递归思想来实现
	 * 1 如果s为空则直接返回
	 * 2 如果s的首字母不等于[，则直接返回结果
	 * 3 如果s的首字母是[，但长度只有2，说明是空，则直接返回
	 * 4  如果s的首字母是[，长度＞2，则从i=1开始遍历；start表示起始位置；cnt表示是否是同一深度
	 * 	  当cnt=0，或者到了字符串最末位置，则取出start到当前位置的字符串递归解析
	 * @param s
	 * @return
	 */
	public NestedInteger deserializeV3(String s) {
		if (s.length() == 0)
			return new NestedInteger();
		if (s.charAt(0) != '[')
			return new NestedInteger(Integer.parseInt(s));
		if (s.length() <= 2)
			return new NestedInteger();
		NestedInteger res = new NestedInteger();
		int start = 1, cnt = 0;
		for (int i = 1; i < s.length(); ++i) {
			if (cnt == 0 && (s.charAt(i) == ',' || i == s.length() - 1)) {
				res.add(deserializeV3(s.substring(start, i)));
				start = i + 1;
			} else if (s.charAt(i) == '[')
				++cnt;
			else if (s.charAt(i) == ']')
				--cnt;
		}
        return res; 
		
	}
	
	
	public static void main(String[] args) {
		List<String> dataList = new ArrayList<String>();
		//dataList.add("324");
		dataList.add("[123,[456,[789]]]");
		for(String s : dataList){
			NestedInteger r = new MiniParser385().deserializeV3(s);
			System.out.println(r.getInteger());
			System.out.println(r.getList());
		}
	}
}

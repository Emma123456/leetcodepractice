package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BaseballGame682 {
	/**
	 * 错误在+操作：top1 先弹出，top2 再弹出，还原到stack里面的时候，要先放top2，再放top1，最后放score = top1+top2
	 * @param ops
	 * @return
	 */
	public int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> scoreStack = new Stack<Integer>();
        for(String s : ops){
            if( s.equals("+")){
                int score = 0;
                int top1 = scoreStack.pop();
                score += top1;
                int top2 = scoreStack.pop();
                score += top2;
                sum += score;
                scoreStack.push(top2);
                scoreStack.push(top1);
                scoreStack.push(score);
                
            }else if(s.equals("D")){
                int lastScore = scoreStack.peek();
                sum += lastScore*2;
                scoreStack.push(lastScore*2);
            }else if(s.equals("C")){
                int removedScore = scoreStack.pop();
                sum -= removedScore;
            }else{
                int score = Integer.parseInt(s);
                sum += score;
                scoreStack.push(score);
            }
            
        }
        return sum;
    }
	
	/**
	 * 优点：谁说栈就只能用stack了？linkedList也可以；每种操作都优先考虑放入栈中，再考虑加入到sum中。这是我没有注意的，我的思考顺序就很随便了。
	 * 
	 * @param ops
	 * @return
	 */
	public int calPointsV2(String[] ops) {
		int sum = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for (String op : ops) {
            if (op.equals("C")) {
                sum -= list.removeLast();
            }
            else if (op.equals("D")) {
                list.add(list.peekLast() * 2);
                sum += list.peekLast();
            }
            else if (op.equals("+")) {
                list.add(list.peekLast() + list.get(list.size() - 2));
                sum += list.peekLast();
            }
            else {
                list.add(Integer.parseInt(op));
                sum += list.peekLast();
            }
        }
        return sum;
	}
	public static void main(String[] args) {
		List<String[]> caseList = new ArrayList<String[]>();
		caseList.add(new String[]{"5","2","C","D","+"});//30
		caseList.add(new String[]{"5","-2","4","C","D","9","+","+"});//27
		for(String[] ops : caseList){
			int sum = new BaseballGame682().calPoints(ops);
			System.out.println(sum);
		}
		
	}
}

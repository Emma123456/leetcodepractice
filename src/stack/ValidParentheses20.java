package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses20 {
	private static Map<Character,Character> matchedCharacter = new HashMap<Character,Character>();
	static{
		matchedCharacter.put(']', '[');
		matchedCharacter.put(')', '(');
		matchedCharacter.put('}', '{');
	}
	/**
	 * 如果遇到( { [ 则 压入栈里面；如果遇到 ) ] } 则从栈里面pop出一个字符，如果字符能对应起来，则正常，否则返回false，退出
	 * 没有意识到字符串中只包含字符：'(', ')', '{', '}', '[' and ']'
	 * charStack.isEmpty() 是后来加上的，没有考虑到
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		Stack<Character> charStack = new Stack<Character>();
		if (s != null && s.length() > 0) {
			for(Character ch : s.toCharArray()){
				if(ch == '(' || ch =='{' || ch=='['){
					charStack.push(ch);
				}else if(ch == ')' || ch == '}' || ch == ']' ){
					if(charStack.isEmpty()){
						return false;
					}
					Character topch = charStack.pop();
					if(topch != matchedCharacter.get(ch)){
						return false;
					}
				}
			}
		}
		return charStack.isEmpty();
	}

	/**
	 * 将对应的char放入stack，这是亮点
	 * @param s
	 * @return
	 */
	public boolean isValidV2(String s){
		Stack<Character> charStack = new Stack<Character>();
		for(char ch : s.toCharArray()){
			if(ch == '('){
				charStack.push(')');
			}else if (ch == '{'){
				charStack.push('}');
			}else if (ch == '['){
				charStack.push(']');
			}else if(charStack.isEmpty() || charStack.pop() != ch){
				return false;
			}
		}
		return charStack.isEmpty();
	}
	
	public static void main(String[] args) {
		List<String> caseList = Arrays.asList(""
				,"(]"
				,"()[]{}"
				,"("
				,"]");
		for(String s : caseList){
			boolean r = new ValidParentheses20().isValidV2(s);
			System.out.println(r);
		}
	}

}

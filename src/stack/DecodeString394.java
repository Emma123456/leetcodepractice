package stack;

import java.util.Stack;

public class DecodeString394 {
	/**
	 * 1 没有考虑2位的数字
	 * 
	 * @param s
	 * @return
	 */
	public String decodeString(String s) {
		Stack<String> stack = new Stack<String>();
		for (char ch : s.toCharArray()) {
			if (ch != ']') {
				stack.push(String.valueOf(ch));
			} else {
				String str = "";
				while (!stack.isEmpty()) {
					String top = stack.pop();
					if (!top.equals("[")) {
						str = top + str;
					} else {
						int number = getNumber(stack);
						String r = "";
						for (int i = 0; i < number; i++) {
							r += str;
						}
						stack.push(r);
						break;
					}
				}
			}
		}
		String r = "";
		for (String str : stack) {
			r = r + str;
		}
		return r;
	}

	private int getNumber(Stack<String> stack) {
		String str = "";
		while (!stack.isEmpty() && stack.peek().matches("[0-9]")) {
			str = stack.pop() + str;
		}
		return Integer.parseInt(str);
	}

	/**
	 * 分开数字与字符
	 * 问题：代码不够有没，resultStack.push("");有点奇怪
	 * @param s
	 * @return
	 */
	public String decodeStringV2(String s) {
		Stack<Integer> countStack = new Stack<Integer>();
		Stack<String> resultStack = new Stack<String>();
		int i = 0;
		resultStack.push("");
		while (i < s.length()) {
			char ch = s.charAt(i);
			if (ch >= '0' && ch <= '9') {
				int start = i;
				while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
					i++;
				countStack.push(Integer.parseInt(s.substring(start, i + 1)));
			} else if (ch == '[') {
				resultStack.push("");//分隔开不同的[]范围内的字符
			} else if (ch == ']') {
				String str = resultStack.pop();
				Integer count = countStack.pop();
				StringBuilder strb = new StringBuilder();
				for (int j = 0; j < count; j++) {
					strb.append(str);
				}
				resultStack.push(resultStack.pop()+strb.toString());//不断合并结果
			} else {
				resultStack.push(resultStack.pop() + ch);
			}
			i++;
		}
		return resultStack.pop();
	}
	
	/**
	 * 与第二版本总体思路相同，但是因为使用res变量，让整个代码变得更流畅了
	 * 与第一版本比较，我的思路还是比较狭窄
	 * @param s
	 * @return
	 */
	public String decodeStringV3(String s) {
		Stack<Integer> countStack = new Stack<Integer>();
		Stack<String> resultStack = new Stack<String>();
		int i = 0;
		String res = "";
		while (i < s.length()) {
			char ch = s.charAt(i);
			if (ch >= '0' && ch <= '9') {
				int start = i;
				while (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')
					i++;
				countStack.push(Integer.parseInt(s.substring(start, i + 1)));
			} else if (ch == '[') {
				resultStack.push(res);
				res = "";
			} else if (ch == ']') {
				StringBuilder strb = new StringBuilder(resultStack.pop());
				int count = countStack.pop();
				for(int j=0;j<count;j++){
					strb.append(res);
				}
				res = strb.toString();
			} else {
				res += ch;
			}
			i++;
		}
		return res;
	}

	public static void main(String[] args) {
		String s = "3[a]2[bc]";
		String r = new DecodeString394().decodeStringV3(s);
		System.out.println(r);
	}
}

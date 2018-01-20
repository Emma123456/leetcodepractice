package stack;

import java.util.Stack;

public class RemoveDuplicateLetters316 {
	/**
	 * 贪心：每次选择最小的一个字符。这里最小的前提条件是每个字符的count>0。
	 * @param s
	 * @return
	 */
	public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0;
        for(char ch : s.toCharArray()){
        	cnt[ch-'a'] ++;
        }
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i) < s.charAt(pos)) pos = i;
        	if(--cnt[s.charAt(i)-'a']==0){
        		break;
        	}
        }
        return s.length() == 0? "" : s.charAt(pos)+removeDuplicateLetters(s.substring(pos+1).replaceAll(""+s.charAt(pos), ""));
    }
	
	/**
	 * 使用栈的思想
	 * @param s
	 * @return
	 */
	public String removeDuplicateLettersV2(String s) {
		 int[] cnt = new int[26];
		 boolean[] visited = new boolean[26];
		 char[] chs = s.toCharArray();
		 for(char ch : chs){
	        	cnt[ch-'a'] ++;
	     }
		 Stack<Character> stack = new Stack<>();
		 for(char ch: chs){ 
			 int index = ch - 'a';
			 cnt[index] -- ;
			 if(visited[index]){
				 continue;
			 }
			 while(!stack.isEmpty() && ch < stack.peek() && cnt[stack.peek()-'a']>0){
				 visited[stack.pop()-'a'] = false;
			 }
			 visited[index]=true;
			 stack.push(ch);
		 }
		 String r = "";
		 while(!stack.isEmpty()){
			 r = stack.pop()+r;
		 }
		 return r;
	}
	public static void main(String[] args) {
		String s = "cbacdbdbc";
		String r =  new RemoveDuplicateLetters316().removeDuplicateLettersV2(s);
		System.out.println(r);
	}

}

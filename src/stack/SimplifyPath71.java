package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SimplifyPath71 {
	/**
	 * path = "/home/", => "/home" 
	 * path = "/a/./b/../../c/", => "/c"
	 * path = "/" => "/"
	 * path = "/..." => "/..."
	 * path = "/..hidden" => "/..hidden"
	 * 大小写区分
	 * "/字母"这是一级目录； "/."可以忽略 ；"/.."回退一级目录
	 * 
	 * 经过合并后发现：只要考虑字符不等于/的情况
	 * @param path
	 * @return
	 */
	public String simplifyPath(String path) {
		Stack<String> dirStack = new Stack<String>();
		int n = path.length();
		for (int i = 0; i < n; i++) {
			char ch = path.charAt(i);
			if (ch!='/') {
				int end = i + 1;
				while (end < n && path.charAt(end) !='/') {
					end++;
				}
				String str = path.substring(i,end);
				if(str.equals("..")){
					if(!dirStack.isEmpty()){
						dirStack.pop();
					}
				}else if(!str.equals(".")){
					dirStack.push(str);
				}
				i = (end > i + 1 ? end - 1 : i);
			}
		}
		String r = "";
		for(String dir : dirStack){
			r = r + "/"+dir;
		}
		return r==""?"/":r;

	}
	
	/**
	 * 应该从上个版本再过渡一下，可以把字符串按/分隔，分隔后的字符按整体来看
	 * "..",".",""是无效的目录
	 * 速度更慢了
	 * @param path
	 * @return
	 */
	public String simplifyPathV2(String path) {
		Deque<String> stack = new LinkedList<String>();
		for(String str : path.split("/")){
			if(str.equals("..") && !stack.isEmpty()){
				stack.pop();
			}else if(!str.equals("..") && !str.equals(".") && !str.equals("")){
				stack.push(str);
			}
		}
		String r = "";
		for(String dir : stack){
			r = "/"+dir+r;
		}
		return r==""?"/":r;
	}

	public static void main(String[] args) {
		String path = "/...";
		String r = new SimplifyPath71().simplifyPathV2(path);
		System.out.println(r);
	}

}

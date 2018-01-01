package stack;

public class RemoveKDigits402 {
	/**
	 * 每次删除一位数字，得到新的最小num，再删除，直到删除k个数字。
	 * 需要求每次删除一位数字，如果得到最小的num：从高位开始删除，遇到比当前最小值大的删除操作就停止。
	 * 
	 * @param num
	 * @param k
	 * @return
	 */
	public String removeKdigits(String num, int k) {
		for (int i = 0; i < k; i++) {
			String minVal = num;
			for (int j = 0; j < num.length() ; j++) {
				//删除掉下标为j的字母
				String newnum = j==0? num.substring(j+1): num.substring(0,j)+num.substring(j+1);
				if(newnum.length() == 0){
					minVal = "0";
					break;
				}
				if(newnum.length()<minVal.length() || newnum.compareTo(minVal)<=0){
					minVal = newnum;
				}else{
					break;
				}
			}
			num = minVal;
		}
		for(int i = 0;i<num.length();i++){
			if(num.charAt(i)!='0'){
				return num.substring(i);
			}
		}
		return "0";
	}
	
	/**
	 * 新的num长度=num.length-k
	 * 如果stk中的前几个字符比当前要处理的字符大，则移除它，放入当前字符。
	 * 这个操作只能操作k次。
	 * @param num
	 * @param k
	 * @return
	 */
	public String removeKdigitsV3(String num, int k) {
		int digits = num.length() - k;// 最后会留下digits长度的字符
		char[] stk = new char[num.length()];
		int top = 0;// 栈的顶端
		for (int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			while (top > 0 && stk[top - 1] > c && k > 0) {
				top -= 1;
				k -= 1;
			}
			stk[top++] = c;
		}
		int idx = 0;
		while (idx < digits && stk[idx] == '0')
			idx++;
		return idx == digits ? "0" : new String(stk, idx, digits - idx);
	}
	public static void main(String[] args) {
		String num = "1432219";
		int k = 3;
		String r = new RemoveKDigits402().removeKdigitsV3(num, k);
		System.out.println(r);
	}

}

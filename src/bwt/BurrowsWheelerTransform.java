package bwt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BurrowsWheelerTransform {
	public BurrowsWheelerTransform(String str){
		this.str = str;
	}
	private String str;
	private String formedString;
	private int originalIndex;
	/**
	 * bwt用于数据压缩，例如bzip2，于1994年，由 Michael Burrows and David Wheeler 发明。
	 * 经过bwt变换后，字符串按照字母顺序排列。
	 * 1 输入字符串str，长度为n；
	 * 2 旋转字符串str，n次；
	 * 3 按字典顺序排序旋转后的字符串；
	 * 4 取上一步每个字符串最后一位，组成新的字符串str1；
	 * 5 在3中查找原始字符串的位置，并且记下
	 * @param str
	 * @return
	 */
	public String encode() {
		List<String> rotatedList = new ArrayList<String>();
		String newStr = str;
		rotatedList.add(newStr);
		int n = str.length();
		for (int i = 0; i < n - 1; i++) {
			newStr = newStr.substring(n - 1) + newStr.substring(0, n - 1);
			rotatedList.add(newStr);
		}
		Collections.sort(rotatedList);
		formedString = "";
		for (int i = 0; i < rotatedList.size(); i++) {
			String rotatedStr = rotatedList.get(i);
			if(str.equals(rotatedStr)){
				originalIndex = i;
			}
			formedString += rotatedStr.substring(n - 1);
		}
		return formedString;
	}

	public String decode(){
		List<Character> characterList = new ArrayList<Character>();
		List<String> rotatedList = new ArrayList<String>();
		for (char ch : formedString.toCharArray()) {
			characterList.add(ch);
			rotatedList.add(String.valueOf(ch));
		}
		Collections.sort(rotatedList);
		int n = str.length();
		for (int i = 0; i < n - 1; i++) {
			for(int j=0;j<n;j++){
				rotatedList.set(j, characterList.get(j)+rotatedList.get(j));
			}
			Collections.sort(rotatedList);
		}
		return rotatedList.get(originalIndex);
	}
	public static void main(String[] args) {
		BurrowsWheelerTransform b = new BurrowsWheelerTransform("SIX.MIXED.PIXIES.SIFT.SIXTY.PIXIE.DUST.BOXES");
		String str = b.encode();
		System.out.println(str);
		str = b.decode();
		System.out.println(str);
	}
}

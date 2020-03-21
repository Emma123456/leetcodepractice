package homework.string;

public class ReverseWords {

	public static void main(String[] args) {
		String word = "123456  word1  eword! ";
		word = new ReverseWords().reverseWords(word);
		System.out.println(word);
	}

	/**
	 * 原地逆转 1 数组整体逆转，单词也被逆转了。 2 逆转单词 3 去除多余空格
	 * 
	 * @param s
	 * @return
	 */
	public String reverseWords(String s) {
		if (s == null)
			return null;
		char[] array = s.toCharArray();
		int n = array.length;
		reverseString(array, 0, n - 1);
		System.out.println(new String(array));
		reverseWord(array, n);
		int len = cleanSpaces(array, n);
		return new String(array, 0, len);
	}

	/**
	 * 删除多余空格,返回有效字符的长度
	 * 
	 * @param array
	 * @param n
	 */
	private int cleanSpaces(char[] array, int n) {
		int i = 0, j = 0;
		while (j < n) {
			while (j < n && array[j] == ' ') {
				j++;
			}
			while (j < n && array[j] != ' ') {
				array[i++] = array[j++];
			}
			array[i++] = ' ';
		}
		return array[i - 1] == ' ' ? i - 1 : i;
	}

	/**
	 * 将单词翻转回来
	 * 
	 * @param array
	 * @param n
	 */
	private void reverseWord(char[] array, int n) {
		for (int i = 0; i < n; i++) {
			if (array[i] != ' ') {
				int j = i;
				while (j < n && array[j] != ' ') {
					j++;
				}
				if (j - 1 > i) {
					reverseString(array, i, j - 1);
					i = j - 1;
				}
			}
		}
	}

	/**
	 * 逆转下标s到e的字符数组
	 * 
	 * @param array
	 * @param s
	 * @param e
	 */
	private void reverseString(char[] array, int s, int e) {
		while (s < e) {
			{
				char tmp = array[s];
				array[s] = array[e];
				array[e] = tmp;
				s++;
				e--;
			}
		}
	}
}

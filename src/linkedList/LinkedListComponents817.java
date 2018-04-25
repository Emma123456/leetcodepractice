package linkedList;

import java.util.ArrayList;
import java.util.List;

public class LinkedListComponents817 {
	public int numComponents(ListNode head, int[] G) {
		int result = 0;
		ListNode node = head;
		List<Integer> valueList = new ArrayList<Integer>(G.length);
		for (Integer val : G) {
			valueList.add(val);
		}
		while (node != null) {
			if (valueList.contains(node.val) && (node.next == null || !valueList.contains(node.next.val))) {
				result++;
			}
			node = node.next;
		}
		return result;
	}

	/**
	 * 更快
	 * @param head
	 * @param G
	 * @return
	 */
	public int numComponentsV2(ListNode head, int[] G) {
		int result = 0;
		ListNode node = head;
		List<Integer> valueList = new ArrayList<Integer>(G.length);
		for (Integer val : G) {
			valueList.add(val);
		}
		boolean in = false;
		while (node != null) {
			if (valueList.contains(node.val)) {
				in = true;
			} else {
				if (in)
					result++;
				in = false;
			}
			node = node.next;
		}
		if (in)
			result++;
		return result;
	}
}

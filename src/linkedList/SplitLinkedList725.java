package linkedList;

public class SplitLinkedList725 {
	public ListNode[] splitListToParts(ListNode root, int k) {
		int len = 0;
		ListNode node = root;
		while (node != null) {
			len++;
			node = node.next;
		}

		int width = len / k;
		int remainder = len % k;
		ListNode[] result = new ListNode[k];
		node = root;
		for (int i = 0; i < k; i++) {
			int size = width + (i < remainder ? 1 : 0);
			ListNode dummy = new ListNode(-1);
			ListNode node2 = dummy;
			for (int j = 0; j < size; j++) {
				node2.next = node;
				node = node.next;
				node2 = node2.next;
			}
			node2.next = null;
			result[i] = dummy.next;
		}
		return result;
	}
}

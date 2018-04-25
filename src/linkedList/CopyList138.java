package linkedList;

import java.util.HashMap;
import java.util.Map;

class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
};

public class CopyList138 {
	private Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return head;
		if (map.get(head) != null)
			return map.get(head);
		RandomListNode newHead = new RandomListNode(head.label);
		map.put(head, newHead);
		newHead.next = copyRandomList(head.next);
		newHead.random = copyRandomList(head.random);
		return newHead;
	}

	public RandomListNode copyRandomListV2(RandomListNode head) {
		if (head == null)
			return head;
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode iter = head;
		while (iter != null) {
			map.put(iter, new RandomListNode(iter.label));
			iter = iter.next;
		}
		iter = head;
		while (iter != null) {
			if (iter.next != null) {
				map.get(iter).next = map.get(iter.next);
			}
			if (iter.random != null) {
				map.get(iter).random = map.get(iter.random);
			}
			iter = iter.next;
		}
		return map.get(head);
	}
	
	public static void main(String[] args) {
		RandomListNode head = new RandomListNode(-1);
		RandomListNode node1 = new RandomListNode(1);
		head.next = node1;
		head.random = node1;
		new CopyList138().copyRandomList(head);
	}

}

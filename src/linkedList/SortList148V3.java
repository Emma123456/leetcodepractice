package linkedList;

import java.util.LinkedList;
import java.util.Queue;

public class SortList148V3 {
	public ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		Queue<ListNode> queue = new LinkedList<ListNode>();
		ListNode node = head;
		while (node != null) {
			ListNode tmp = node.next;
			node.next = null;
			queue.add(node);
			node = tmp;
		}
		while(queue.size()>1){
			queue.add(merge(queue.poll(), queue.poll()));
		}
		return queue.poll();
	}

	public ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}
		ListNode result = new ListNode(0);
		ListNode preNode = result;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				preNode.next = l1;
				l1 = l1.next;
			} else {
				preNode.next = l2;
				l2 = l2.next;
			}
			preNode = preNode.next;
		}
		if (l1 != null) {
			preNode.next = l1;
		}
		if (l2 != null) {
			preNode.next = l2;
		}
		return result.next;
	}
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(-1);
		ListNode node2 = new ListNode(5);
		ListNode node3 = new ListNode(8);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(0);
		ListNode node6 = new ListNode(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		ListNode head = new SortList148V3().sortList(node1);
		while(head!=null){
			System.out.print(head.val+"\t");
			head = head.next;
		}
	}
}

package linkedList;

public class ReverseLinkedList206 {
	public ListNode reverseList(ListNode head) {
		ListNode pre = null;
		ListNode current = head;
		while (current != null) {
			ListNode nextTmp = current.next;
			current.next = pre;
			pre = current;
			current = nextTmp;
		}
		return pre;
	}
}

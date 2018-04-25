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
	
	public ListNode reverseListV2(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		return newHead;
	}
}

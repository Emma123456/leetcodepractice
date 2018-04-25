package linkedList;

public class AddTwoNumbers445 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode p = reverseList(l1);
		ListNode q = reverseList(l2);
		ListNode dummy = new ListNode(-1);
		ListNode currNode = dummy;
		int carry = 0;
		while (p != null || q != null) {
			int sum = carry + (p != null ? p.val : 0) + (q != null ? q.val : 0);
			carry = sum / 10;
			currNode.next = new ListNode(sum % 10);
			currNode = currNode.next;
			p = p == null ? null : p.next;
			q = q == null ? null : q.next;
		}
		if (carry > 0) {
			currNode.next = new ListNode(carry);
		}
		return reverseList(dummy.next);
	}

	public ListNode reverseList(ListNode head) {
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

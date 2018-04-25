package linkedList;

public class AddTwoNumbers2 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode p = l1, q = l2;
		ListNode dummy = new ListNode(-1);
		ListNode currNode = dummy;
		int carry = 0;
		while (p != null || q != null) {
			int sum = carry + (p != null ? p.val : 0) + (q != null ? q.val : 0);
			carry = sum / 10;
			currNode.next = new ListNode(sum % 10);
			currNode = currNode.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			currNode.next = new ListNode(carry);
		}
		return dummy.next;
	}
}

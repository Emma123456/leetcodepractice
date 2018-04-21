package linkedList;

public class RotateList61 {
	public ListNode rotateRight(ListNode head, int k) {
		int len = 0;
		ListNode node = head;
		while (node != null) {
			len++;
			node = node.next;
		}
		k = k % len;
		if (k == 0) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode fast = dummy;
		for (int i = 0; i <= k; i++) {
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		ListNode newHead = slow.next;
		slow.next = null;
		node = newHead;
		while (node.next != null) {
			node = node.next;
		}
		node.next = dummy.next;
		dummy.next = newHead;
		return dummy.next;
	}

	/**
	 * 代码优化
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode rotateRightV2(ListNode head, int k) {
		if(head==null || head.next == null) return head;
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		int len = 0;
		ListNode fast = dummy;
		ListNode slow = dummy;
		for(;fast.next!=null;len++){
			fast = fast.next;
		}
		for(int i=len-k%len;i>0;i--){
			slow = slow.next;
		}
		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		// node5.next = node6;
		ListNode head = new RotateList61().rotateRightV2(node1, 2);
		while (head != null) {
			System.out.print(head.val + "\t");
			head = head.next;
		}
	}
}

package linkedList;

/**
 * 测试数据 [] [1] [1,2] [1,2,1] [1,2,2,1] [1,2,2,3] [1,2,2,2,1]
 * 
 * @author Administrator
 *
 */
public class PalindromeLinkedList234 {
	/**
	 * 查找到LinkedList的一半，然后把前一部分逆转；最后比较两部分是否相同
	 * 
	 * @param head
	 * @return
	 */
	public boolean isPalindrome(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null) {
			slow = slow.next;
		}
		slow = reverse(slow);
		ListNode node = head;
		while(slow!=null){
			if(node.val != slow.val){
				return false;
			}
			node = node.next;
			slow = slow.next;
		}
		return true;
	}

	private ListNode reverse(ListNode head) {
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

	public static void main(String[] args) {
		ListNode node = new ListNode(1);
		ListNode head = node;
		node.next = new ListNode(2);
		node = node.next;
		node.next = new ListNode(2);
		node = node.next;
		node.next = new ListNode(1);
		new PalindromeLinkedList234().isPalindrome(head);
	}
}

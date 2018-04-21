package linkedList;

public class ReverseLinkedList92 {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummyNode = new ListNode(-1);
		dummyNode.next = head;
		ListNode currentNode = head;
		ListNode preNode = dummyNode;
		int count = 1;
		while (count < m) {
			preNode = currentNode;
			currentNode = currentNode.next;
			count++;
		}
		ListNode startReversedNode = currentNode;
		ListNode preNodeTmp = preNode;
		preNode = null;
		while (count <= n) {
			ListNode tmp = currentNode.next;
			currentNode.next = preNode;
			preNode = currentNode;
			currentNode = tmp;
			count++;
		}
		startReversedNode.next = currentNode;
		preNodeTmp.next = preNode;
		return dummyNode.next;
	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		//2,4;1,4;2,5;1,5
		ListNode head = new ReverseLinkedList92().reverseBetween(node1, 2, 4);
		while(head!=null){
			System.out.print(head.val+"\t");
			head = head.next;
		}
	}
}

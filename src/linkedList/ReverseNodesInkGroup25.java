package linkedList;

public class ReverseNodesInkGroup25 {
	/**
	 * 思路1：在92. Reverse Linked List II我们实现了反转list中子列表的代码。只要找到每个长度为k的子串，反转就可以。
	 * 思路2：上面会有一些重复的跳过m个节点的操作。可以进一步优化。从head开始数，数够了k步就从head开始反转长度为k的子链表；接着再从当前节点开始数。够k个就反转。不够就退出。
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummyNode = new ListNode(-1);
		dummyNode.next = head;
		ListNode startReversedNode = head;
		ListNode preOfStart = dummyNode;
		ListNode preNode = null;
		ListNode currentNode = head;
		while(true){
			ListNode testNode = currentNode;
			int count = 0;
			while(testNode!=null && count<k){
				count++;
				testNode = testNode.next;
			}
			if (count < k) {
				break;
			}
			for (int i = 0; i < k; i++) {
				ListNode tmp = currentNode.next;
				currentNode.next = preNode;
				preNode = currentNode;
				currentNode = tmp;
			}
			preOfStart.next = preNode;
			startReversedNode.next = currentNode;
			
			preOfStart = startReversedNode;
			startReversedNode = currentNode;
		}
		return dummyNode.next;
    }
	
	public ListNode reverseKGroupV2(ListNode head, int k) {
		ListNode currentNode = head;
		int count =0;
		while(currentNode!=null && count!=k){
			count++;
			currentNode = currentNode.next;
		}
		if(count == k ){
			currentNode = reverseKGroupV2(currentNode,k);
			while(count>0){
				ListNode tmp = head.next;
				head.next = currentNode;
				currentNode = head;
				head = tmp;
				count--;
			}
			head = currentNode;
		}
		return head;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		node5.next = node6;
		//2,4;1,4;2,5;1,5
		ListNode head = new ReverseNodesInkGroup25().reverseKGroupV2(node1, 2);
		while(head!=null){
			System.out.print(head.val+"\t");
			head = head.next;
		}
	}

}

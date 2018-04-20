package linkedList;

public class RemoveNthNodeFromEndOfList19 {
	/**
	 * 从尾部开始计算，删除第n个节点。
	 * 如果是从头部开始计算，删除第n个节点就比较好处理了。那就把问题转换一下。从尾部开始的第n个节点=从头部开始的第几个节点。
	 * 经过画图举例知道 应该是从头部开始第(len-n+1)节点，len是list长度。
	 * 假设要删除的节点nodeA ，应该是nodeA的上一个节点.next = nodeA.next。需要处理nodeA的上一个节点不存在的情况，这里加入一个dummy节点。
	 * @param head
	 * @param n
	 * @return
	 */
	 public ListNode removeNthFromEnd(ListNode head, int n) {
		 ListNode dummy = new ListNode(0);
		 dummy.next = head;
		 int len = 0;
		 ListNode node = dummy;
		 while(node.next!=null){
			 len++;
			 node = node.next;
		 }
		 node = dummy;
		 for(int i=0;i<len-n;i++){
			 node = node.next;
		 }
		 node.next = node.next.next;
		 return dummy.next;
	 }
	 
	 /**
	  * 思路2：使用两个指针first、second。first先走n+1个节点（因为有dummy节点），然后second再走。当first到达队尾的时候，second距离队尾还有n个节点。
	  * @param head
	  * @param n
	  * @return
	  */
	 public ListNode removeNthFromEndV2(ListNode head, int n) {
		 ListNode dummy = new ListNode(0);
		 dummy.next = head;
		 ListNode first = dummy;
		 ListNode second = dummy;
		 for(int i=0;i<=n;i++){
			 first = first.next;
		 }
		 while(first!=null){
			 first = first.next;
			 second = second.next;
		 }
		 second.next = second.next.next;
		 return dummy.next;
	 }
	 public static void main(String[] args) {
		ListNode node = new ListNode(1);
		ListNode head = node;
		node.next = new ListNode(2);
		node = node.next;
		node.next = new ListNode(3);
		node = node.next;
		node.next = new ListNode(4);
		ListNode r = new RemoveNthNodeFromEndOfList19().removeNthFromEndV2(head, 4);
		while(r!=null){
			System.out.print(r.val+"\t");
			r = r.next;
		}
	}
}

package linkedList;

public class ReorderList143 {
	public void reorderList(ListNode head) {
        if(head==null || head.next==null) return;
        //算长度
        int len = 0;
        ListNode node = head;
        for(;node!=null;len++){
            node = node.next;
        }
        //node走到一半或者一半多一个节点
        node = head;
        ListNode preNode = null;
        for(int i=0;i<(len%2==0?len/2:len/2+1);i++){
        	preNode = node;
            node = node.next;
        }
        
        //反转后半个链表
        preNode.next = null;
        preNode = node;
        ListNode curr = node.next;
        preNode.next = null;
        while(curr!=null){
            ListNode tmp = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr = tmp;
        }
        ListNode secondHead = preNode;
        
        //合并两个列表
        ListNode dummyNode = new ListNode(-1);
        ListNode mergetNode = dummyNode;
        while(secondHead!=null){
            mergetNode.next = head;
            head = head.next;
            mergetNode = mergetNode.next;
            
            mergetNode.next = secondHead;
            secondHead = secondHead.next;
            mergetNode = mergetNode.next;           
        }
        while(head!=null){
            mergetNode.next = head;
            head = head.next;
            mergetNode = mergetNode.next;
        }
        head = dummyNode.next;
    }
	
	/**
	 * 代码优化
	 * @param head
	 */
	public void reorderListV2(ListNode head) {
		//找一半节点
		if(head==null || head.next==null) return;
		ListNode slow = head, fast = head;
		while(fast.next!=null && fast.next.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
		//反转
		ListNode pre = slow;
		ListNode curr = slow.next;
		pre.next = null;
		pre = null;
		while(curr!=null){
			ListNode tmp = curr.next;
			curr.next = pre;
			pre = curr;
			curr = tmp;
		}
		
		//合并
		ListNode secondHead = pre;
		ListNode curr1 = head;
		ListNode curr2 = secondHead;
		while (curr1 != null && curr2 != null) {
			ListNode next1 = curr1.next;
			ListNode next2 = curr2.next;

			curr1.next = curr2;
			curr2.next = next1;

			curr1 = next1;
			curr2 = next2;
		}
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
		//node5.next = node6;
		new ReorderList143().reorderListV2(node1);
		while(node1!=null){
			System.out.print(node1.val+"\t");
			node1 = node1.next;
		}
	}
}

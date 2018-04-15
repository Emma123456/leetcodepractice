package linkedList;

public class RemoveLinkedListElements203 {
	/**
	 * 考虑1 ： 可能有多个节点符合
	 * 考虑2：命中节点是head
	 * 考虑3：命中节点是尾节点
	 * 考虑4：命中节点是中间的普通节点
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElements(ListNode head, int val) {
        ListNode node = head;
        ListNode preNode = null;
        while(node!=null){
            if(node.val==val){
                if(preNode!=null){
                    preNode.next = node.next;
                    node = preNode.next;
                }else{
                    head = node.next;
                    node = head;
                }
            }else{
                preNode = node;
                node = node.next;
            }
        }
        return head;
    }
	
	/**
	 * 在linkedList 运用递归的思路还是很常见的，我还没有形成习惯 
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElementsV2(ListNode head, int val) {
		if(head==null) return null;
		head.next = removeElementsV2(head.next,val);
		if(head.val == val){
			head = head.next;
		}
		return head;
	}
	
	/**
	 * 因为第一版有head节点的特殊处理，可以采取的措施是：加一个假的head
	 * @param head
	 * @param val
	 * @return
	 */
	public ListNode removeElementsV3(ListNode head, int val) {
		ListNode dummy  = new ListNode(-1);
		dummy .next = head;
        ListNode node = head;
        ListNode preNode = dummy ;
        while(node!=null){
            if(node.val==val){
                preNode.next = node.next;
            }else{
                preNode = node;
            }
            node = node.next;
        }
        return dummy .next;
    }
}

package linkedList;

public class ConvertSortedListToBST109 {
	/**
	 * 初看题目是没有思路的。从左到右开始构建树，想什么情况下该把根节点变为左子树；左子树为空的时候不能加右子树，之类的....脑子里一直在想着数据结构
	 * 书中讲的从非平衡树到平衡树的转换。
	 * 学习：整体观察后发现链表的中间节点middle是根；middle的左侧是根的左子树；middle的右侧是根的右子树。
	 * @param head
	 * @return
	 */
	public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        if(head.next==null) return new TreeNode(head.val);
        ListNode slow = head,fast=head,pre = null;
        while(fast!=null && fast.next!=null){
            pre = slow;
            slow =slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }
}

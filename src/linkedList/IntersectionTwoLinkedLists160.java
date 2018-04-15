package linkedList;

public class IntersectionTwoLinkedLists160 {
	/**
	 * 要求：1 空间是O(1);2 时间是O(n)
	 * 难点：如果listA和listB有交集，那么从交集点开始之后所有的node是相等的，那么长度也是相同的。所以如果有长度差异，一定是在列表的前端。
	 * @param headA
	 * @param headB
	 * @return
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {     
        if(headA==null || headB==null){
            return null;
        }
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        if(lenA < lenB){
            nodeA = headB;
            nodeB = headA;
            int tmp = lenA;
            lenA = lenB;
            lenB = tmp;
        }
        for(int i=0;i<lenA-lenB;i++){
            nodeA = nodeA.next;
        }
        while(nodeA!=null && nodeB!=null){
            if(nodeA==nodeB){
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }
        return null;
    }
    
    private int getLength(ListNode head){
        int len = 0;
        while(head!=null){
            len++;
            head=head.next;
        }
        return len;
    }
}

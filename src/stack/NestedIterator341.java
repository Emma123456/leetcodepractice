package stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 本题要求把嵌套的int展开，重点在嵌套int的递归
 * @author Administrator
 *
 */
public class NestedIterator341 implements Iterator<Integer> {
	private int listIndex = 0;
	private int valueSize = 0;
	private List<Integer> valueList;
    public NestedIterator341(List<NestedInteger> nestedList) {
    	valueList = new ArrayList<Integer>();
    	visit(nestedList,0);
    	valueSize = valueList.size();
    }
    private void visit(List<NestedInteger> nestedList,int index) {
		if (index < nestedList.size()) {
			if(nestedList.get(index).isInteger()){
				valueList.add(nestedList.get(index).getInteger());
				visit(nestedList,index+1);
			}else{
				visit(nestedList.get(index).getList(),0);
				visit(nestedList,index+1);
			}
		}
	}

	@Override
    public Integer next() {
		return valueList.get(listIndex++);
    }

    @Override
    public boolean hasNext() {
		return listIndex < valueSize;
        
    }

	@Override
	public void remove() {
		
	}

	public static void main(String[] args) {
		List<NestedInteger> nestedList = new ArrayList<NestedInteger>();
		NestedInteger n1 = new NestedInteger();
		n1.add(new NestedInteger(1));
		n1.add(new NestedInteger(1));
		nestedList.add(n1);
		
		nestedList.add(new NestedInteger(2));
		
		NestedInteger n3 = new NestedInteger();
		n3.add(new NestedInteger(1));
		n3.add(new NestedInteger(1));
		nestedList.add(n3);
		
		NestedIterator341 i = new NestedIterator341(nestedList);
		while (i.hasNext()) System.out.println(i.next());
	}
}

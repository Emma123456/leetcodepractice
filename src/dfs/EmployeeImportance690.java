package dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
public class EmployeeImportance690 {
	 public int getImportance(List<Employee> employees, int id) {
	        Map<Integer,Employee> employeeMap = new HashMap<Integer,Employee>();
	        for(Employee e : employees){
	            employeeMap.put(e.id,e);
	        }
	        return count(id,employeeMap);
	    }
	    
	    private int count(int id, Map<Integer,Employee> employeeMap ){
	        Employee e = employeeMap.get(id);
	        int sum = e.importance;
	        if(e.subordinates!=null){
	            for(Integer subId : e.subordinates){
	                sum += count(subId,employeeMap);
	            }
	        }
	        return  sum;
	    }
	public int getImportanceV2(List<Employee> employees, int id) {
        Map<Integer,Employee> employeeMap = new HashMap<Integer,Employee>();
        for(Employee e : employees){
            employeeMap.put(e.id,e);
        }
        
        Stack<Integer> idStack = new Stack<Integer>();
        idStack.push(id);
        int sum = 0;
        while(!idStack.isEmpty()){
            Employee e = employeeMap.get(idStack.pop());
            sum += e.importance;
            if(e.subordinates!=null){
                for(Integer subId : e.subordinates){
                    idStack.push(subId);
                }
            }
        }
        return sum;
    }
}

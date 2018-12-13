package bfs;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.List;

public class EmployeeImportance690 {
    public int getImportance(List<Employee> employees, int id) {
        int value = 0;
        Map<Integer,Employee> idMap = new HashMap<Integer,Employee>();
        for(Employee employee : employees){

            idMap.put(employee.id,employee);
        }
        Queue<Integer> idQueue = new LinkedList<Integer>();
        idQueue.add(id);
        while(!idQueue.isEmpty()){
            int size = idQueue.size();
            for(int i=0;i<size;i++){
                int eid = idQueue.poll();
                value += idMap.get(eid).importance;
                idQueue.addAll(idMap.get(eid).subordinates);
            }

        }
        return value;
    }
}



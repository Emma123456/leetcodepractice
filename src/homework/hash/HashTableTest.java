package homework.hash;

import java.util.LinkedHashMap;
import java.util.Map;

public class HashTableTest {
    public static void main(String[] args){
        HashTable<String,Integer> table = new HashTable<>();
        for(int i=0;i<20;i++){
            table.put(i+"",i);
        }
        System.out.println(table.get("10"));
        table.put("10",100);
        System.out.println(table.get("10"));

        LinkedHashMap<String,Integer> v =new LinkedHashMap<String,Integer>();
        v.put("q",1);
        v.put("a",2);
        v.put("3",4);
        v.put("1",5);

        for(Map.Entry<String, Integer> e : v.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }


        System.out.println("======");
        LRUBaseHashTable<String,Integer> lru = new LRUBaseHashTable<>();
        lru.put("q",1);
        lru.put("a",2);
        lru.put("3",4);
        lru.put("1",5);

        lru.get("3");
        lru.get("q");
        lru.printAll();
        System.out.println("======");
        for(homework.hash.Map.Entry<String, Integer> e : lru.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }
}

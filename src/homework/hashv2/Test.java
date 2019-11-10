package homework.hashv2;

public class Test {
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>();
        for (int i = 0; i < 20; i++) {
            table.put(i + "", i);
        }
        System.out.println(table.get("10"));
        table.put("10", 100);
        System.out.println(table.get("10"));

        Object v = table.remove("1");
        System.out.println(v);

        table.remove("22");


        LRU<String,Integer> lru = new LRU<>();
        lru.put("q",1);
        lru.put("a",2);
        lru.put("3",4);
        lru.put("1",5);

        lru.get("3");
        lru.get("q");
        System.out.println("======");

        for(Map.Entry<String, Integer> e : lru.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }
}

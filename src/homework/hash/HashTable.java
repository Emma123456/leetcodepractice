package homework.hash;

import java.util.Objects;

/**
 * 基于链表法解决hash冲突的hash表
 * 近似相当于hashmap
 */
public class HashTable<K,V>  implements Map<K,V>{
    private static final int  DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    static class Node<K,V> implements Map.Entry<K,V> {
         int hash;
         K key;
        V value;
        Node<K,V> next;
        Node(K key, V value,int hash, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hash = hash;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }


    }
    Node<K,V>[] table;
    //实际存放元素个数
    int size;
    //table使用的个数
    int used;

    public HashTable(){
        table = new Node[16];
    }

    /**
     * 新增
     * @param key
     * @param value
     */
    public V put(K key, V value){
        if((used+0.0f)/table.length>LOAD_FACTOR){
            //扩容
            resize();
        }
        int hash = hash(key);
        int idx = hash & (table.length-1);
        V oldValue = null;
        if(table[idx]==null){
            used++;
            table[idx] = newNode(key,value,hash,null);
            size++;
        }else{
            Node<K,V> tmp = table[idx];
            Node<K,V> replaceNode = null;
            while(tmp!=null){
                if(tmp.hash == hash && ( tmp.key==key || (tmp.key!=null && tmp.key.equals(key)))){
                    replaceNode = tmp;
                    break;
                }
                if(tmp.next==null){
                    tmp.next = newNode(key,value,hash,null);
                    size++;
                    break;
                }
                tmp = tmp.next;
            }
            if(replaceNode!=null){
                oldValue = replaceNode.value;
                replaceNode.value = value;
                afterNodeAccess(replaceNode);
            }
        }
        afterNodeInsertion(true);
        return oldValue;
    }


    Node<K,V> newNode(K key, V value,int hash,  Node<K,V> next) {
        return new Node( key, value, hash,next);
    }

    /**
     * 扩容
     */
    private void resize() {
        int newSize = table.length << 1;
        Node<K,V>[] newTable = new Node[newSize];
        used = 0;
        for(int i=0;i<table.length;i++){
            Node<K,V> entry = table[i];
            while(entry!=null){
                int hash = hash(entry.key);
                int idx = hash & (newTable.length-1);
                if(newTable[idx]==null){
                    used++;
                    newTable[idx] = new Node<>(entry.key,entry.value,entry.hash,null);
                }else{
                    Node<K,V> tmp = newTable[idx];
                    while(tmp.next!=null){
                        tmp = tmp.next;
                    }
                    tmp.next = new Node<>(entry.key,entry.value,entry.hash,null);
                }
            }
        }
        table = newTable;
    }

    int hash(Object key){
        if(key==null) return 0;
        int h = key.hashCode();
        return (h>>>16) ^h;
    }

    void afterNodeInsertion(boolean evict) { }
    void afterNodeAccess(Node<K,V> replaceNode) {}

    /**
     * 删除，如果key存在，返回key对应的value值
     * @param key
     */
    public V remove(K key){
        int hash = hash(key);
        int idx = hash & (table.length-1);
        Node<K,V> tmp = table[idx];
        Node<K,V> pre = null;
        V value = null;
        while(tmp!=null){
            if(tmp.key==key || tmp.key.equals(key)){
                break;
            }else{
                pre = tmp;
                tmp = tmp.next;
            }
        }
        if(tmp!=null){
            value = tmp.value;
            if(pre==null){
                table[idx] = null;
                used --;
            }else{
                pre.next = tmp.next;
            }
            size --;
        }

        return value;
    }

    /**
     * 查询
     * @param key
     * @return
     */
    @Override
    public V get(K key){
        int hash = hash(key);
        Node<K,V> node = getNode(hash,key);
        return node!=null?node.value:null;
    }


    HashTable.Node<K,V> getNode(int hash, Object key) {
        int idx = hash & (table.length-1);
        Node<K,V> tmp = table[idx];
        while(tmp!=null){
            if(tmp.key==key || tmp.key.equals(key)){
                afterNodeAccess(tmp);
                break;
            }else{
                tmp = tmp.next;
            }
        }
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    public void clear() {
        Node<K,V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }
}

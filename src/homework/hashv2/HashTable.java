package homework.hashv2;

import java.util.Objects;

/**
 * 用链表法解决哈希冲突的哈希表
 * @param <K>
 * @param <V>
 */
public class HashTable<K,V> implements Map<K,V> {
    private static final float LOAD_FACTOR = 0.75f;

    static  class Node<K,V> implements Map.Entry<K,V>{
        K key;
        V value;
        int hash;
        Node next;

        public Node(K key, V value,int hash,Node next){
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public final boolean equals(Object o){
            if(o== this) return true;
            if(o instanceof Map.Entry){
                Map.Entry e = (Entry) o;
                return Objects.equals(this.key,((Entry) o).getKey()) && Objects.equals(this.value,((Entry) o).getValue());
            }
            return false;
        }

        public final int hashCode(){
            return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
        }
    }

    /*******静态方法区********/
    /**
     * 获取一个对象 的hashcode值
     * @param key
     * @return
     */
    static int hash(Object key){
        int h;
        return (key==null)?0:((h=key.hashCode())^(h>>>16));
    }


    //存放元素
    Node[] table;

    //元素个数
    int size;

    //table中已经有多少个位置被占用
    int used;

    //哈希表尺寸
    int capacity;

    public HashTable(){
        this(16);
    }

    public HashTable(int capacity){
        this.capacity = capacity;
        table = new Node[this.capacity];
    }

    /**
     * 先根据key的hashcode找到存储下标。如果存储的位置已经有元素则加入到元素列表末尾
     * 如果要放的元素的可以已经在table中存在，则替换值
     * 没哟考虑负载因子，超过负载因子需要扩容
     * @param key
     * @param value
     * @return
     */

    @Override
    public V put(K key, V value) {
        if(used/(table.length+0.0f)>LOAD_FACTOR){
            resize();
        }
        V oldValue = null;
        int hash = hash(key);
        int index = hash & (table.length -1);
        Node<K,V> e = table[index];
        if(e==null){
            table[index] = newNode(key,value,hash,null);
            size++;
            used++;
        }else{
            while(e!=null){
                if(Objects.equals(e.getKey(),key)){
                    oldValue = e.getValue();
                    e.setValue(value);
                    afterNodeAccess(e);
                    break;
                }else if(e.next==null){
                    e.next = newNode(key,value,hash,null);
                    size++;
                }
                e = e.next;
            }
        }
        return oldValue;
    }

    @Override
    public V get(K key) {
        Node<K,V> e = getNode(key);
        return e==null?null:e.getValue();
    }

    Node<K,V> getNode(K key){
        int hash = hash(key);
        int index = hash & (table.length -1);
        Node<K,V> e = table[index];
        if(e==null){
            return null;
        }else{
            while(e!=null){
                if(Objects.equals(e.getKey(),key)){
                    afterNodeAccess(e);
                    return e;
                }
                e = e.next;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        V val = null;
        int hash = hash(key);
        int index = hash & (table.length -1);
        Node<K,V> e = table[index];
        if(e==null){
            return null;
        }else{
            Node<K,V> pre = null;
            while(e!=null){
                if(Objects.equals(e.getKey(),key)){
                    val = e.getValue();
                    if(pre!=null){
                        pre.next = e.next;
                    }else{
                        table[index] = null;
                        used -- ;
                    }
                }
                pre = e;
                e = e.next;
            }
        }
        return val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size==0;
    }

    /**
     * 清空map
     */
    public void clear(){
        if(size>0){
            size =0;
            for(int i=0;i<table.length;i++){
                table[i] = null;
            }
        }
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

    /**
     * 节点访问之后要做的操作，用于LinkedHashMap调用
     * @param e
     */
    void afterNodeAccess(Node<K,V> e) { }

    /**
     * 创建一个新元素
     * @param key
     * @param value
     * @param hash
     * @param next
     * @return
     */
    Node<K,V> newNode(K key, V value, int hash, Node next) {
        return new Node<>(key,value,hash,next);
    }


}

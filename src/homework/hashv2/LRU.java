package homework.hashv2;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

public class LRU<K,V> extends HashTable<K,V> {
    static class Entry<K,V> extends HashTable.Node<K,V>{
        //上一个节点
        Entry befor;
        //下一个节点
        Entry after;

        public Entry(K key,V value,int hashCode,Node next){
            super(key,value,hashCode,next);
        }


    }
    //头指针
    Entry head;

    //尾指针
    Entry tail;

    Set<Map.Entry<K,V>> entrySet;

    public LRU(){
        super();
    }

    public LRU(int capacity){
        super(capacity);
    }

    Node<K,V> newNode(K key, V value, int hash, Node next) {
        Entry<K,V> e = new Entry<K,V>(key,value,hash,next);
        linkNodeLast(e);
        return e;
    }

    /**
     * 将e节点放在这个双向列表的末尾
     * @param e
     */
    private void linkNodeLast(Entry<K,V> e) {
        Entry last = tail;
        tail = e;
        if(last==null){
            head = e;
        }else{
            last.after = e;
            e.befor = last;
        }
    }

    public V get(K key) {
        Node<K,V> e = getNode(key);
        if(e!=null){
            afterNodeAccess(e);
        }
        return e==null?null:e.getValue();
    }

    public Set<Map.Entry<K,V>> entrySet(){
        if(entrySet==null){
            entrySet = new LinkedEntrySet();
        }
        return entrySet;
    }

    /**
     * 一个节点被访问，则移动到链表最后一位
     * @param e
     */
    void afterNodeAccess(Node<K,V> e) {
        Entry last = tail;
        if(tail!=e && e!=null){
            Entry p = (Entry)e;
            Entry a = p.befor,b=p.after;
            p.after = null;
            last.after = p;
            if(a==null){
                //要把头结点移动到末尾
                head = b;
            }else{
                a.after = b;
            }
            if(b==null){

            }
        }
    }
    class LinkedEntrySet extends AbstractSet<Map.Entry<K,V>> {

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedEntryIterator();
        }

        public int size() {
            return size;
        }
    }

    class LinkedEntryIterator implements Iterator<Map.Entry<K,V>>{
        LRU.Entry current;
        LRU.Entry next;
        public LinkedEntryIterator(){
            next = head;
        }
        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Map.Entry<K, V> next() {
            current = next;
            next = next.after;
            return current;
        }
    }
}

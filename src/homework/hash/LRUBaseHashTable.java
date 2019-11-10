package homework.hash;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 一个LRU缓存淘汰算法 = LinkedHashMap
 * LRU 有两个列表：一个是双向列表，一个是hash中的列表
 * 最新处理的节点放在列表末尾
 *
 * 结合？
 */
public class LRUBaseHashTable<K,V> extends HashTable<K,V>{

    static class Entry<K,V> extends HashTable.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super( key, value, hash,next);
        }
    }



    //accessOrder=true则按照访问顺序排序，否则按照插入顺序排序,默认为true
    boolean accessOrder = true;

    LRUBaseHashTable.Entry<K,V> head;
    LRUBaseHashTable.Entry<K,V> tail;
    Set<Map.Entry<K,V>> entrySet;


    public LRUBaseHashTable(){
        super();
    }

    HashTable.Node<K,V> newNode(K key, V value,int hash,  Node<K,V> next) {
        LRUBaseHashTable.Entry<K,V> p =
                new LRUBaseHashTable.Entry<K,V>(hash, key, value, next);
        linkNodeLast(p);
        return p;
    }

    //在链表末尾加入节点p
    private void linkNodeLast(Entry<K,V> p) {
        LRUBaseHashTable.Entry<K,V> last = tail;
        tail = p;
        if(last == null){
            head = p;
        }else{
            p.before = last;
            last.after = p;
        }
    }

    /**
     * 节点插入之后做的操作，相当于什么都没做
     * @param evict
     */
    void afterNodeInsertion(boolean evict) {
        LRUBaseHashTable.Entry<K,V> first;
        if(evict && (first=head)!=null && removeEldestEntry(first)){

        }
    }

    private boolean removeEldestEntry(Entry<K,V> first) {
        return false;
    }


    /**
     * 节点访问之后做的操作
     * @param e
     */
    void afterNodeAccess(Node<K,V> e) {
        Entry<K,V> last;
        //什么给tail赋值的?
        if(accessOrder &&  (last=tail)!=e){
            Entry<K,V> p = (Entry<K,V>) e;
            Entry<K,V> b = p.before, a=p.after;

            p.after = null;
            if(b==null){
                head = a;
            }else{
                b.after = a;
            }
            if(a!=null){
                a.before = b;
            }else{
                last = b;
            }

            if(last==null){
                head = p;
            }else{
                p.before = last;
                last.after = p;
            }
            tail = p;
        }
    }

    /**
     * 清空
     */
    public void clear() {
        super.clear();
        head = tail = null;
    }

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public V get(Object key) {
        Node<K,V> e;
        if ((e = getNode(hash(key), key)) == null)
            return null;
        if (accessOrder)
            afterNodeAccess(e);
        return e.value;
    }


    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es;
        return (es = entrySet)==null?entrySet = new LinkedEntrySet():es;
    }

    /**
     * 最小代价的一个set
     */
    class LinkedEntrySet extends AbstractSet<Map.Entry<K,V>> {
        public final int size()                 { return size; }
        public final void clear()               { LRUBaseHashTable.this.clear(); }
        public final Iterator<Map.Entry<K,V>> iterator() {
            return new LinkedEntryIterator();
        }
        public final boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>) o;
            Object key = e.getKey();
            Node<K, V> candidate = getNode(hash(key), key);
            return candidate != null && candidate.equals(e);
        }


        public final void forEach(Consumer<? super Map.Entry<K,V>> action) {
            if (action == null)
                throw new NullPointerException();
            for (Entry<K,V> e =  head; e != null; e = e.after)
                action.accept(e);

        }
    }


    // Iterators

    abstract class LinkedHashIterator {
        LRUBaseHashTable.Entry<K,V> next;
        LRUBaseHashTable.Entry<K,V> current;

        LinkedHashIterator() {
            next = head;
            current = null;
        }

        public final boolean hasNext() {
            return next != null;
        }

        final LRUBaseHashTable.Entry<K,V> nextNode() {
            LRUBaseHashTable.Entry<K,V> e = next;

            if (e == null)
                throw new NoSuchElementException();
            current = e;
            next = e.after;
            return e;
        }

        public final void remove() {
            Node<K,V> p = current;
            if (p == null)
                throw new IllegalStateException();

            current = null;
            K key = p.key;
            //removeNode(hash(key), key, null, false, false);
        }
    }

    final class LinkedEntryIterator extends LinkedHashIterator
            implements Iterator<Map.Entry<K,V>> {
        public final Map.Entry<K,V> next() {
            return nextNode();
        }
    }


    public void printAll(){
        for (LRUBaseHashTable.Entry<K,V> e = head; e != null; e = e.after)
            System.out.println(e.value);

    }
}

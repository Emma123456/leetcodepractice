package homework.hash;

public interface Map<K,V> {
    /**
     * 元素个数
     * @return
     */
    int size();

    /**
     * 数组个数
     * @return
     */
    boolean isEmpty();

    /**
     * 返回key对应的value
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 插入key - value
     * @param key
     * @param value
     * @return 对应key之前存储的值
     */
    V put(K key,V value);

    /**
     * 删除key，返回对应的value
     * @param key
     * @return
     */
    V remove(K key);

    interface Entry<K,V> {
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object o);
        int hashCode();
    }
}

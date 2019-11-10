package homework.hashv2;

public interface Map<K,V> {
    /**
     * 放入键值对
     * @param key
     * @param value
     * @return
     */
    V put(K key,V value);

    /**
     * 返回键值对
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 删除
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * 返回集合元素个数
     * @return
     */
    int size();

    /**
     * 集合是否为空
     * @return
     */
    boolean empty();

    interface  Entry<K,V>{
        K getKey();
        V getValue();
        V setValue(V  value);
        boolean equals(Object o);
        int hashCode();
    }

}

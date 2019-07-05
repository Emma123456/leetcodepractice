package book.skiplist;

public class SkipListNode<T> {
    public  int key;
    public T value;
    public SkipListNode<T> up,down,right,left;
    public static final int HEAD_KEY = Integer.MIN_VALUE;
    public static final int TAIL_KEY = Integer.MAX_VALUE;

    public SkipListNode(int key ,T v){
        this.key = key;
        this.value = v;
    }

    public int getKey() {
        return key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    public boolean equals(Object o){
        if(o== this) return true;
        if(o==null) return false;
        if(!( o instanceof SkipListNode)){
            return false;
        }

        SkipListNode<T> node = null;

        try {
            node = (SkipListNode<T>)  o; // 检测类型
        } catch (ClassCastException ex) {
            return false;
        }
        return node.key == this.key && node.value.equals(this.value);
    }

    @Override
    public String toString() {
        return "key-value:"+key+"-"+value;
    }
}

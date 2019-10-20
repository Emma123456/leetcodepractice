package book.tree;

public class BinarySearchTreeTest {
    public static void main(String[] args){
    BinarySearchTree<Integer> btree = new BinarySearchTree<>();
    btree.levelPrint();
    btree.insert(10);
    btree.insert(1);
    btree.insert(30);
    btree.insert(20);
    btree.levelPrint();
    btree.delete(10);
    btree.levelPrint();
    System.out.println(btree.findMax());
    System.out.println(btree.findMin());
}
}

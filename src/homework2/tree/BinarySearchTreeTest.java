package homework2.tree;



public class BinarySearchTreeTest {
    public static void main(String[] args){
    BinarySearchTree<Integer> btree = new BinarySearchTree<>();
    btree.levelPrint();
    btree.insert(10);
    btree.delete(10);
    btree.levelPrint();
    btree.insert(1);
        btree.insert(0);
    btree.insert(30);
    btree.insert(20);
        btree.insert(9);
    btree.levelPrint();
    btree.delete(10);
    btree.levelPrint();
    System.out.println();
    System.out.println(btree.findMax());
    System.out.println(btree.findMin());

        System.out.println(btree.find(10));
        System.out.println(btree.find(9));
        btree.preOrder();
        btree.middleOrder();
        btree.postOrder();
}
}

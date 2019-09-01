package homework.stack;

public class ArrayStackTest {
    public static void main(String[] args){
        ArrayStack stack = new ArrayStack();
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.pop();
        stack.printAll();
    }
}

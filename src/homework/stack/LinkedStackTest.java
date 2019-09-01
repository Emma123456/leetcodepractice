package homework.stack;

public class LinkedStackTest {
    public static void main(String[] args){
        LinkedStack stack = new LinkedStack();
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.pop();
        stack.printAll();
    }
}

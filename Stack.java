import javax.xml.transform.Source;
import java.util.Arrays;

public class Stack {
    private int size;
    private int[] stack;
    private int top;

    public Stack(int size){
        this.size = size;
        stack = new int[size];
        top = -1;
    }

    public void push(int c){
        stack[++top] = c;
    }

    public int pop(){
        return stack[top--];
    }

    public int peek(){
        return stack[top];
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public int size(){
        return top + 1;
    }


    public void displayStack(String s){
        System.out.println(s);
        System.out.println("Стэк снизу вверх: ");
        for(int i = 0; i < size(); i++){
            System.out.println(stack[i] + " ");
        }
    }
}

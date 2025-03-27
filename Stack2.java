import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Stack2 {
    private final List<Integer> list = new LinkedList<>();

    public int pop(){
        return list.removeLast();
    }

    public void push(int data){
        list.addLast(data);
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public int peek(){
        return list.getLast();
    }
}

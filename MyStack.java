import java.util.LinkedList;
import java.util.List;

public class MyStack<T>{

    private final List<T> list = new LinkedList<>();

    public T pop(){
        return list.removeLast();
    }

    public void push(T data){
        list.addLast(data);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public T peek(){
        return list.getLast();
    }

    public int size(){
        return list.size();
    }

}

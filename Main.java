import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String input;
        int output;
        while(true) {
            System.out.print("Enter postfix: ");
            System.out.flush();
            input = getString();         // Ввод строки с клавиатуры
            if( input.equals("") )       // Завершение, если нажата клавиша
                break;                    // [Enter]
            // Создание объекта для разбора выражения
            String rpn = RPN.toRPN(input);
            CountRPN aParser = new CountRPN(input);
            output = aParser.count();  // Обработка выражения
            System.out.println("Evaluates to " + output);
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}
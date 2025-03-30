import infix.InfixFacade;
import rpn.RPNFacade;
import spn.SPNFacade;
import utils.SafeScanner;

import java.util.Scanner;

import static utils.EvaluateVars.evaluate;

public abstract class Menu {
    public static void printMenu(){
        System.out.println("Выберите действие:");
        System.out.println("1. Преобразовать введенное простое выражение в прямую польскую нотацию");
        System.out.println("2. Преобразовать введенное простое выражение в обратную польскую нотацию");
        System.out.println("3. Проверка корректности простого выражения");
        System.out.println("4. Проверка корректности выражения в прямой польской нотации");
        System.out.println("5. Проверка корректности выражения в обратной польской нотации");
        System.out.println("6. Вычисление простого выражения");
        System.out.println("7. Вычисление выражения в прямой польской нотации");
        System.out.println("8. Вычисление выражения в обратной польской нотации");
        System.out.println("9. Выход");
    }
    public static void doMenu(Scanner scanner) {
        InfixFacade infix = new InfixFacade();
        RPNFacade rpn = new RPNFacade();
        SPNFacade spn = new SPNFacade();

        int choice;
        String expression;
        boolean isCorrect;
        double answer;
        do {
            System.out.print("Выберите действие: ");
            choice = SafeScanner.scanInt(scanner);
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите выражение: ");
                    //expression = scanner.nextLine();
                    expression = evaluate(scanner.nextLine(), scanner);
                    while (!infix.validate(expression)){
                        System.out.print("Пожалуйста введите корректное выражение: ");
                        expression = scanner.nextLine();
                    }
                    System.out.print("Ваше выражение в прямой польской нотации: ");
                    System.out.println(spn.transform(expression));
                    break;
                case 2:
                    System.out.print("Введите выражение:");
                    expression = scanner.nextLine();
                    while (!infix.validate(expression)){
                        System.out.print("Пожалуйста введите корректное выражение: ");
                        expression = scanner.nextLine();
                    }
                    System.out.print("Ваше выражение в обратной польской нотации: ");
                    System.out.println(rpn.transform(expression));
                    break;
                case 3:
                    System.out.print("Введите выражение: ");
                    expression = scanner.nextLine();
                    isCorrect = infix.validate(expression);
                    if (isCorrect){
                        System.out.println("Ваше выражение корректно");
                    }
                    else{
                        System.out.println("Ваше выражение содержит ошибку");
                    }
                    break;
                case 4:
                    System.out.print("Введите выражение: ");
                    expression = scanner.nextLine();
                    isCorrect = spn.validate(expression);
                    if (isCorrect){
                        System.out.println("Ваше выражение корректно");
                    }
                    else{
                        System.out.println("Ваше выражение содержит ошибку");
                    }
                    break;
                case 5:
                    System.out.print("Введите выражение: ");
                    expression = scanner.nextLine();
                    isCorrect = rpn.validate(expression);
                    if (isCorrect){
                        System.out.println("Ваше выражение корректно");
                    }
                    else{
                        System.out.println("Ваше выражение содержит ошибку");
                    }
                    break;
                case 6:
                    System.out.print("Введите выражение: ");
                    expression = scanner.nextLine();
                    while (!infix.validate(expression)){
                        System.out.print("Пожалуйста введите корректное выражение: ");
                        expression = scanner.nextLine();
                    }
                    answer = infix.calculate(expression);
                    System.out.println("Значение выражения: " + answer);
                    break;
                case 7:
                    System.out.print("Введите выражение: ");
                    expression = scanner.nextLine();
                    while (!spn.validate(expression)){
                        System.out.print("Пожалуйста введите корректное выражение: ");
                        expression = scanner.nextLine();
                    }
                    answer = spn.calculate(expression);
                    System.out.println("Значение выражения: " + answer);
                    break;
                case 8:
                    System.out.print("Введите выражение: ");
                    expression = scanner.nextLine();
                    while (!rpn.validate(expression)){
                        System.out.print("Пожалуйста введите корректное выражение: ");
                        expression = scanner.nextLine();
                    }
                    answer = rpn.calculate(expression);
                    System.out.println("Значение выражения: " + answer);
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Пожалуйста выберите число из предложенных вариантов.");
            }
        } while (choice != 9);
    }
}


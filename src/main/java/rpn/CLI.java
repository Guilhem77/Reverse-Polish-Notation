package rpn;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));

        System.out.println("About to evaluate '" + expression + "'");
        long result = evaluate(expression);
        System.out.println("> " + result);
    }

    static long evaluate(String expression) throws DivideException {
        long result = 0;
        int value = 0;
        Stack<Integer> numbers = new Stack<>();
        String [] symbols = expression.split(" ");

        for (String str : symbols) {
            if (!(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"))) {
                int number = Integer.parseInt(str);
                numbers.push(number);
            }
            else {
                int number1 = numbers.pop();
                int number2 = numbers.pop();
                if (str.equals("+")) {
                    value = number2 + number1;
                }
                else if (str.equals("-")) {
                    value = number2 - number1;
                }
                else if (str.equals("*")) {
                    value = number2 * number1;
                }
                else if (str.equals("/")) {
                    if (number1 != 0) {
                        value = number2 / number1;
                    }
                    else {
                        throw new DivideException("Division par 0 impossible.");
                    }
                }
                numbers.push(value);
            }
        }
        result = numbers.pop();
        return result;
    }

}

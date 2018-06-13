package rpn;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{
    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));



        System.out.println("About to evaluate '" + expression + "'");

        Evaluator evaluator = new Evaluator();
        long result = evaluator.evaluate(expression);

        System.out.println("> " + result);
    }


}

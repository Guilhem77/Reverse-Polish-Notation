package rpn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class Evaluator
{
    int number1;
    int number2;
    Stack<Integer> numbers = new Stack<>();

    private final Collection<ResultListener> resultListeners = new ArrayList<ResultListener>();

    public void addResultListener(ResultListener resultListener)
    {
        resultListeners.add(resultListener);
    }

    public Evaluator()
    {
        ResultListener resultListener = new ResultListener()
        {
            @Override
            public Integer onResultReadyEvent(Integer newResult)
            {
                System.out.println("OKONAUNCHANGEMENT!!!");
                return newResult;
            }
        };

        this.addResultListener(resultListener);
    }

    public boolean operatorDetect(String str)
    {
        return (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"));
    }

    long evaluate(String expression) throws DivideException
    {
        String[] symbols = expression.split(" ");

        for (String str : symbols)
        {
            if (!operatorDetect(str))
            {
                int number = Integer.parseInt(str);
                numbers.push(number);
            }
            else
            {
                numbers.push(calul(str));
            }
        }

        return getResutl();
    }

    public Integer calul(String operator)
    {
        number1 = numbers.pop();
        number2 = numbers.pop();
        if (operator.equals("+"))
        {
            return (number2 + number1);
        }
        else if (operator.equals("-"))
        {
            return (number2 - number1);
        }
        else if (operator.equals("*"))
        {
            return (number2 * number1);
        }
        else if (operator.equals("/"))
        {
            if (number1 != 0)
            {
                return (number2 / number1);
            }
            else
            {
                throw new DivideException("Division par 0 impossible.");
            }
        }
        return null;
    }

    public Integer getResutl()
    {
        Integer retult = numbers.pop();
        fireResultCharged(4,retult);
        return retult;
    }

    protected void fireResultCharged(Integer oldResult, Integer newResult)
    {
        if(!newResult.equals(oldResult))
        {
            for(ResultListener listener : resultListeners)
            {
                listener.onResultReadyEvent(newResult);
            }
        }
    }
}

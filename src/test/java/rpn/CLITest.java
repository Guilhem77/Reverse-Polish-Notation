package rpn;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import rpn.Evaluator;

public class CLITest {

    Evaluator evaluator = new Evaluator();

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluator.evaluate("5")).isEqualTo(5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluator.evaluate("17")).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluator.evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluator.evaluate("2 3 5 + +")).isEqualTo(10);
    }

    @Test
    public void should_evaluate_complex_multiplication() {
        assertThat(evaluator.evaluate("3 5 8 * 7 + *")).isEqualTo(141);
    }

    @Test(expected = DivideException.class)
    public void should_throw_DivideException() { evaluator.evaluate("5 0 /");}

    @Test
    public void should_evaluate_addition_and_subtract() { assertThat(evaluator.evaluate("4 2 + 3 -")).isEqualTo(3); }

    @Test
    public void should_evaluate_negative_numbers() { assertThat(evaluator.evaluate("-8")).isEqualTo(-8); }

    @Test
    public void should_evaluate_addition_and_subtract_alternate() { assertThat(evaluator.evaluate("5 2 3 + -")).isEqualTo(0); }

    @Test(expected = EmptyStackException.class)
    public void should_throw_EmptyStackException() { evaluator.evaluate("8 5 - -");}

    @Test(expected = NumberFormatException.class)
    public void should_throw_NumberFormatException() { evaluator.evaluate("8 5 @");}
}
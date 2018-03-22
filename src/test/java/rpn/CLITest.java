package rpn;

import org.junit.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;

public class CLITest {

    @Test
    public void should_evaluate_single_digit_constant() {
        assertThat(evaluate("5")).isEqualTo(5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    @Test
    public void should_evaluate_simple_addition() {
        assertThat(evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        assertThat(evaluate("2 3 5 + +")).isEqualTo(10);
    }

    @Test
    public void should_evaluate_complex_multiplication() {
        assertThat(evaluate("3 5 8 * 7 + *")).isEqualTo(141);
    }

    @Test(expected = DivideException.class)
    public void should_throw_DivideException() { evaluate("5 0 /");}

    @Test
    public void should_evaluate_addition_and_subtract() { assertThat(evaluate("4 2 + 3 -")).isEqualTo(3); }

    @Test
    public void should_evaluate_negative_numbers() { assertThat(evaluate("-8")).isEqualTo(-8); }

    @Test
    public void should_evaluate_addition_and_subtract_alternate() { assertThat(evaluate("5 2 3 + -")).isEqualTo(0); }

    @Test(expected = EmptyStackException.class)
    public void should_throw_EmptyStackException() { evaluate("8 5 - -");}

    @Test(expected = NumberFormatException.class)
    public void should_throw_NumberFormatException() { evaluate("8 5 @");}
}
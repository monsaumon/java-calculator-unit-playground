package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CalculatorTest {
    static Calculator calculator = new Calculator();

    @DisplayName("두 개의 정수를 더한 결과를 반환할 수 있다.")
    @Test
    void testAdd() {
        // given

        // when
        int actual = calculator.add(3, 6);

        // then
        assertThat(actual).isEqualTo(9);
    }

    @DisplayName("두 개의 정수를 뺀 결과를 반환할 수 있다.")
    @Test
    void testSubtract() {
        // given

        // when
        int actual = calculator.subtract(6, 3);

        // then
        assertThat(actual).isEqualTo(3);
    }

    @DisplayName("두 개의 정수를 곱한 결과를 반환할 수 있다.")
    @Test
    void testMultiply() {
        // given

        // when
        int actual = calculator.multiply(3, 6);

        // then
        assertThat(actual).isEqualTo(18);
    }

    @DisplayName("두 개의 정수를 나눈 결과를 반환할 수 있다.")
    @Test
    void testDivide() {
        // given

        // when
        int actual = calculator.divide(6, 3);

        // then
        assertThat(actual).isEqualTo(2);
    }
}
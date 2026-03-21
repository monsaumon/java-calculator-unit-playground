package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class StringCalculatorTest {
    static StringCalculator stringCalculator = new StringCalculator();

    @DisplayName("구분자를 기준으로 분리한 숫자의 합을 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1,22,3,44,5", "1:22:3:44:5", "1,22,3:44:5", "//;\n1,22;3:44;5", "//a\n1a22a3a44a5", "//a[|\n1[22|3a44,5"})
    void testSumString(String value) {
        // given

        // when
        int actual = stringCalculator.sumString(value);

        // then
        assertThat(actual).isEqualTo(75);
    }

    @DisplayName("잘못된 형식이나 음수를 입력했을 때 RuntimeException을 throw한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1a2,3", "//a1a2a3", "//a\n-1a2a3", "//a\n1a-2a3", "//a\n1a2b3", "1[2[3"})
    void testSumStringException(String value) {
        // given

        // when & then
        assertThatThrownBy(() -> stringCalculator.sumString(value)).isInstanceOf(RuntimeException.class);
    }
}

package org.unan.week1;


import com.sun.jdi.IntegerType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private static final Integer NULL_NUMBER = null;
    private static final Integer INVALID_NEGATIVE_NUMBER = -1;
    private static final Integer ONE = 1;

    @Nested
    public class AddTest {
        @Test
        @DisplayName("입력 값을 넣으면 더한 값을 출력해준다.")
        void add() {
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            EngineeringCalculator engineeringCalculator = new EngineeringCalculator();
            int result = digitalCalculator.add(ONE,1);
            int result2 = engineeringCalculator.add(ONE,1);

            Assertions.assertThat(result).isEqualTo(2);
            Assertions.assertThat(result2).isEqualTo(2);
        }

        @Test
        @DisplayName("입력 값 중에 Null이 있으면, 계산이 이루어지지 않는다.")
        void addWithNullInput() {
            DigitalCalculator calculator = new DigitalCalculator();

            Assertions.assertThatThrownBy(
                            () -> {calculator.add(NULL_NUMBER, ONE);}
                    ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("NULL");
        }

        @Test
        @DisplayName("입력 값을 더했을 때 10만이 넘으면 예외가 발생한다.")
        void addResultExceed100000() {
            DigitalCalculator calculator = new DigitalCalculator();
            Assertions.assertThatThrownBy(
                    () -> {calculator.add(50000, 50001);}
            ).isInstanceOf(IllegalArgumentException.class);

        }
    }

    @Nested
    public class SubtractTest {

        @Test
        @DisplayName("입력 값을 넣으면 뺀 값을 출력해준다.")
        void subtract() {
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            EngineeringCalculator engineeringCalculator = new EngineeringCalculator();
            int result = digitalCalculator.subtract(ONE,1);
            int result2 = engineeringCalculator.subtract(ONE,1);

            Assertions.assertThat(result).isEqualTo(0);
            Assertions.assertThat(result2).isEqualTo(0);
        }

        @Test
        @DisplayName("입력한 값 중 null이 있으면 빼기 연산이 되지 않는다.")
        void subtractWithNull() {
          // given
            Integer x = 1;
            Integer y = null;
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            Assertions.assertThatThrownBy(
                    () -> {
                        digitalCalculator.subtract(x, y);
                    }
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("NULL");

        }

        @Test
        @DisplayName("빼기 결과는 최댓값을 넘으면 안된다.")
        void subtractWithMaxResult() {
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            int max = 100000;
            Assertions.assertThatThrownBy(
                    () -> {
                        digitalCalculator.subtract(-1, -100002);
                    }
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("LIMIT");

        }

    }

    @Nested
    public class MultiplyTest {

        @Test
        @DisplayName("빈 값을 입력시에, 곱하기 연산이 이루어지지 않는다.")
        void multiplyWithNullParameter() {
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            Assertions.assertThatThrownBy(
                    () -> {
                        digitalCalculator.multiply(2, null);
                    }
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("NULL");

        }

    }

    @Nested
    public class DivideTest {

        @Test
        @DisplayName("빈 값을 입력시에, 나누기 연산이 이루어지지 않는다.")
        void divideWithNullValue() {
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            Assertions.assertThatThrownBy(
                    () -> {
                        digitalCalculator.divide(1, null);
                    }
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("NULL");
        }

        @Test
        @DisplayName("Divisor가 0인 경우 나누기 연산이 이루어지지 않는다.")
        void divideWithZeroDivisor() {
            DigitalCalculator digitalCalculator = new DigitalCalculator();
            Assertions.assertThatThrownBy(
                    () -> {
                        digitalCalculator.divide(1 ,0);
                    }
            ).isInstanceOf(IllegalArgumentException.class);
        }

    }

    @Nested
    public class EngineerCalculatorTest {

        @Test
        @DisplayName("직각 삼각형의 넓이는 정수여야 한다.")
        void areaWithFloatResult() {
            final Integer x = 2;
            final Integer y = 3;

            EngineeringCalculator engineeringCalculator = new EngineeringCalculator();
            Assertions.assertThatThrownBy(
                    () -> {engineeringCalculator.areaOfVerticalTriangle(x,y);}
            ).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("NOTINT");
        }


    }

}
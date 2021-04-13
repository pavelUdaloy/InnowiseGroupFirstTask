package by.entity.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarAdDtoValidatorTest {

    private CarAdDtoValidator validator;

    @BeforeAll
    void init() {
        validator = new CarAdDtoValidator();
    }

    @ParameterizedTest
    @MethodSource("validatorParams")
    void simplePositiveNumber(Integer number, boolean expected) {
        Assertions.assertEquals(validator.isValid(number, null), expected);
    }

    private Stream<Arguments> validatorParams() {
        return Stream.of(
                Arguments.of(0, false),
                Arguments.of(1, true),
                Arguments.of(-1, false),
                Arguments.of(null, false)
        );
    }
}
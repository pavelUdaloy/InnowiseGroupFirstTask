package by.entity.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarAdDtoValidatorTest {

    private CarAdDtoValidator validator;

    @BeforeAll
    void init() {
        validator = new CarAdDtoValidator();
    }

    @ParameterizedTest
    @ValueSource(ints = {1121212, 312121212, 1, 3, 111111115, Integer.MAX_VALUE})
    void simplePositiveNumber(Integer number) {
        Assertions.assertTrue(validator.isValid(number, null));
    }

    @ParameterizedTest
    @NullSource
    void simpleNegativeNumber(Integer number) {
        Assertions.assertFalse(validator.isValid(number, null));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1121212, -312121212, -1, -3, -111111115, Integer.MIN_VALUE})
    void simpleNull(Integer number) {
        Assertions.assertFalse(validator.isValid(number, null));
    }
}
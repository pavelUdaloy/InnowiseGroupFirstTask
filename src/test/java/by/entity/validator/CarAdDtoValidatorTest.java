package by.entity.validator;

import by.entity.dto.CarAdDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarAdDtoValidatorTest {
    @Test
    void shouldValidForNullValue() {
        CarAdDtoValidator validator = new CarAdDtoValidator();

        CarAdDto carAdDto = new CarAdDto();
        carAdDto.setAge(1);

        Assertions.assertTrue(validator.isValid(11, null));
        Assertions.assertFalse(validator.isValid(null, null));
        Assertions.assertFalse(validator.isValid(-1, null));
    }
}
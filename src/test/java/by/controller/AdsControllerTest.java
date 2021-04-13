package by.controller;

import by.entity.dto.CarAdDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdsControllerTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validation() {
        CarAdDto carAdDto = new CarAdDto();
        carAdDto.setAge(1);
//        carAdDto.setId(1);

        Set<ConstraintViolation<CarAdDto>> violations = validator.validate(carAdDto);
        assertTrue(violations.isEmpty());
    }
}
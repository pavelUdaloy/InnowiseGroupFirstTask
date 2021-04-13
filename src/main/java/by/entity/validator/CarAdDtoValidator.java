package by.entity.validator;

import lombok.NoArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor
public class CarAdDtoValidator implements ConstraintValidator<IdConstraint, Integer> {
    @Override
    public void initialize(IdConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return id != null && id > 0;
    }
}

package org.ly.controller.function;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] strValues;
    private int[] intValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        this.strValues = constraintAnnotation.strValues();
        this.intValues = constraintAnnotation.intValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)){
            return true;
        }
        if (value instanceof String){
            return Arrays.asList(strValues).contains(value);
        }
        if (value instanceof Integer){
            return Arrays.stream(intValues).anyMatch(i -> i == (Integer) value);
        }

        return false;
    }
}

package org.ly.controller.function;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Only supports int and string types
 */
@Documented
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
@Constraint(validatedBy = EnumValueValidator.class)
@Repeatable(EnumValue.List.class)
public @interface EnumValue {

    String message() default "Invalid enum value.";

    String[] strValues() default {};

    int[] intValues() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

    @Target({METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        EnumValue[] value();
    }
}

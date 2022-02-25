package se.iths.constraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Mark a String as representing a well formed category
 */
@Documented
@Constraint(validatedBy = OnlyLowerCase.LowerCaseValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface OnlyLowerCase {

    String message() default "{must not contain uppercase}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class LowerCaseValidator implements ConstraintValidator<OnlyLowerCase, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value.equals(value.toLowerCase());
        }
    }
}

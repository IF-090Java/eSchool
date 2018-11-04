package academy.softserve.eschool.constraint.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import academy.softserve.eschool.constraint.validator.NameValidator;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String message() default "Input must match [А-ЯІЇЄ][а-яіїє']+";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

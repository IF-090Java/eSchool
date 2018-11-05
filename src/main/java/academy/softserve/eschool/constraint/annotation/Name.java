package academy.softserve.eschool.constraint.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import academy.softserve.eschool.constraint.validator.NameValidator;

//todo bk merge all tree annotation within current package into one
@Documented
@Constraint(validatedBy = NameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    //todo bk this message should not be hardcoded here. Pass it as a parameter of the annotation
    String message() default "Input must match [А-ЯІЇЄ][а-яіїє']+";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

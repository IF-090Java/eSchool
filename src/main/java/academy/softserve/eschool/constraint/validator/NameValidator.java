package academy.softserve.eschool.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import academy.softserve.eschool.constraint.annotation.Name;

public class NameValidator implements ConstraintValidator<Name, String> {
	private final static String PATTERN = "[А-ЯІЇЄҐ][а-яіїєґ']+";
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return name != null && name.matches(PATTERN);
	}

}

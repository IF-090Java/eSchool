package academy.softserve.eschool.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import academy.softserve.eschool.constraint.annotation.ClassName;

public class ClassNameValidator implements ConstraintValidator<ClassName, String> {
	//todo bk ++ this pattern should not be hardcoded here. You must have somethig like @ClassName(pattern="\d{1,2}-?[А-ЯІЇЄа-яіїє]?", message="some text")
	private final static String PATTERN = "\\d{1,2}-?[А-ЯІЇЄа-яіїє]?";
	@Override
	public boolean isValid(String className, ConstraintValidatorContext context) {
		return className != null && className.matches(PATTERN);
	}

}
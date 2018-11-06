package academy.softserve.eschool.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import academy.softserve.eschool.constraint.annotation.SubjectName;

public class SubjectNameValidator implements ConstraintValidator<SubjectName, String>{
	private final static String PATTERN = "[А-ЯІЇЄ]([А-ЯІЇЄ]*[а-яіїє]*[' -]?)+";
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return name != null && name.matches(PATTERN);
	}
}

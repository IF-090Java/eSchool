package academy.softserve.eschool.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import academy.softserve.eschool.constraint.annotation.SubjectName;

public class SubjectNameValidator implements ConstraintValidator<SubjectName, String>{

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		return name != null && name.matches("[А-ЯІЇЄ]([а-яіїє']+[ -]?)+");
	}

}

package academy.softserve.eschool.controlleradvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;

@RestControllerAdvice
public class RestExceptionHandler {
	private final static String VALIDATION_FAILED = "Validation failed";
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public GeneralResponseWrapper<Object> handleValidationException (ConstraintViolationException ex){
		List<String> errors = ex.getConstraintViolations()
				.stream()
				.map((violation) -> {
					String className = violation.getRootBeanClass().getName();
					String propertyPath = violation.getPropertyPath().toString();
					String message = violation.getMessage();
					return  String.format("%s %s: %s", className, propertyPath, message);
				})
				.collect(Collectors.toList());
		
		Status status = new Status(HttpStatus.BAD_REQUEST.value(), VALIDATION_FAILED);
		GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
				.status(status)
				.data(errors)
				.build();
		return response;
	}
}

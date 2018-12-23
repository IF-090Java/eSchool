package academy.softserve.eschool.controlleradvice;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import academy.softserve.eschool.security.exceptions.TokenGlobalTimeExpiredException;
import academy.softserve.eschool.wrapper.GeneralResponseWrapper;
import academy.softserve.eschool.wrapper.Status;
import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class RestExceptionHandler {
    private final static String VALIDATION_FAILED = "Validation failed";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public GeneralResponseWrapper<Object> handleValidationException (ConstraintViolationException ex){
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map((violation) -> {
                    String className = violation.getRootBeanClass().getSimpleName();
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
    
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(TransactionSystemException.class)
    public GeneralResponseWrapper<Object> handleTransactionException(TransactionSystemException ex){
        if (ex.getRootCause() instanceof ConstraintViolationException) {
            return handleValidationException((ConstraintViolationException)ex.getRootCause());
        }
        Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }
    
    @ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GeneralResponseWrapper<Object> handleAll(Exception ex) {
        Status status = new Status(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage());
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public GeneralResponseWrapper<Object> badCreds(BadCredentialsException ex) {
        logger.info("User entered bad creds");
        Status status = new Status(HttpStatus.BAD_REQUEST.value(), "Bad Credentials");
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedJwtException.class)
    public GeneralResponseWrapper<Object> malformedToken(MalformedJwtException  ex) {
        logger.warn("Error occured during validating token", ex);
        Status status = new Status(HttpStatus.BAD_REQUEST.value(), "Bad token");
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }



    @ResponseStatus(code=HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public GeneralResponseWrapper<Object> noPrivilegies(AccessDeniedException ex) {
        Status status = new Status(HttpStatus.FORBIDDEN.value(), "No privilegies");
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }
    
    @ResponseStatus(code=HttpStatus.FORBIDDEN)
    @ExceptionHandler(TokenGlobalTimeExpiredException.class)
    public GeneralResponseWrapper<Object> globalTimeExpired(TokenGlobalTimeExpiredException ex) {
        Status status = new Status(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }

    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public GeneralResponseWrapper<Object> badRequestParams(DataIntegrityViolationException ex) {
        Status status = new Status().of(HttpStatus.BAD_REQUEST);
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .build();
        return response;
    }

    @ResponseStatus(code=HttpStatus.FORBIDDEN)
    @ExceptionHandler(DisabledException.class)
    public GeneralResponseWrapper<Object> accountDisabled(DisabledException ex) {
        Status status = new Status().of(HttpStatus.FORBIDDEN);
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .data(ex.getMessage())
                .build();
        return response;
    }

    @ResponseStatus(code=HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public GeneralResponseWrapper<Object> UsernameNotFound(UsernameNotFoundException ex) {
        Status status = new Status().of(HttpStatus.NOT_FOUND);
        GeneralResponseWrapper<Object> response = GeneralResponseWrapper.builder()
                .status(status)
                .data(ex.getMessage())
                .build();
        return response;
    }
}

package cl.mella.usuarios.exception;

import lombok.extern.log4j.Log4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Date;

@RestControllerAdvice
@Log4j
public class ExceptionHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorRequest NotFoundException(NoHandlerFoundException ex, WebRequest request) {
        log.info("resourceNotFoundException");

        ErrorRequest errorRequest = new ErrorRequest(String.valueOf(HttpStatus.NOT_FOUND.value()), ex.getMessage());

        return errorRequest;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorRequest globalException(Exception ex, WebRequest request) {
        log.info("globalExceptionHandler" + ex.toString());
        ErrorRequest errorRequest = new ErrorRequest(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());

        return errorRequest;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorRequest BadRequestException(Exception ex, WebRequest request) {
        log.info("Bad Request");
        ErrorRequest errorRequest = new ErrorRequest(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());

        return errorRequest;
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorRequest UnauthorizedException(Exception ex, WebRequest request) {
        log.info("UNAUTHORIZED");
        ErrorRequest errorRequest = new ErrorRequest(String.valueOf(HttpStatus.UNAUTHORIZED.value()), ex.getMessage());
        return errorRequest;
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorRequest DataIntegrityViolationException(Exception ex, WebRequest request) {
        log.info("DataIntegrityViolationException: " + ex.toString());

        Throwable cause = ((DataIntegrityViolationException) ex).getRootCause();

        if (cause.getMessage().contains("email"))
            return new ErrorRequest(String.valueOf(HttpStatus.CONFLICT.value()), "El correo ya registrado");

        return new ErrorRequest(String.valueOf(HttpStatus.CONFLICT.value()), ex.getMessage());

    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorRequest ConstraintViolationException(Exception ex, WebRequest request) {
        log.info("ConstraintViolationException: " + ex.toString());
        ErrorRequest errorRequest = new ErrorRequest(String.valueOf(HttpStatus.CONFLICT.value()), "El correo ya registrado");
        return errorRequest;
    }

    //
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorRequest ForBiddenException(Exception ex, WebRequest request) {
        log.info("ConstraintViolationException: " + ex.toString());
        ErrorRequest errorRequest = new ErrorRequest(String.valueOf(HttpStatus.FORBIDDEN.value()), "Recurso no autorizado para el usuario, No cuenta con el rol Admin");
        return errorRequest;
    }

}

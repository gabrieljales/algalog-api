package com.algaworks.algalog.api.exceptionhandler;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource; // Interface para resolver mensagens

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StandardError error = new StandardError();
        List<StandardError.Field> fields = new ArrayList<>();

        for (ObjectError err : ex.getBindingResult().getAllErrors()) {
            String name = ((FieldError) err).getField(); // Casting
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            fields.add(new StandardError.Field(name, message));
        }

        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setMessage("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        error.setFields(fields);

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class) // Caso EntityNotFoundException seja lançada em qualquer parte da aplicação, esse é o método responsável por tratá-la
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.NOT_FOUND;

        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DomainException.class) // Caso DomainException seja lançada em qualquer parte da aplicação, esse é o método responsável por tratá-la
    public ResponseEntity<Object> handleDomainException(DomainException ex, WebRequest request) {
        StandardError error = new StandardError();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setStatus(status.value());
        error.setDateTime(OffsetDateTime.now());
        error.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}

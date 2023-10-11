package com.digitalmenu.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ExceptionDetails details = new ExceptionDetails();

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ExceptionDetails> itemNotFoundExceptionHandler(ItemNotFoundException ex,
                                                                         HttpServletRequest request) {
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setError(HttpStatus.NOT_FOUND.name());
        details.setMessage(ex.getMessage());
        details.setPath(request.getRequestURI());

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ExceptionDetails> categoryNotFoundExceptionHandler(CategoryNotFoundException ex,
                                                                             HttpServletRequest request) {
        details.setTimestamp(LocalDateTime.now());
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setError(HttpStatus.NOT_FOUND.name());
        details.setMessage(ex.getMessage());
        details.setPath(request.getRequestURI());

        return ResponseEntity.status(details.getStatus()).body(details);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        var validationDetails = new ValidationExceptionDetails();
        validationDetails.setTimestamp(LocalDateTime.now());
        validationDetails.setStatus(ex.getStatusCode().value());
        validationDetails.setError(HttpStatus.valueOf(ex.getStatusCode().value()).name());
        validationDetails.setMessage("Campos não preenchidos corretamente.");
        validationDetails.setPath(request.getRequestURI());
        validationDetails.setFields(fields);
        validationDetails.setFieldsMessage(fieldsMessage);

        return ResponseEntity.status(validationDetails.getStatus()).body(validationDetails);
    }
}

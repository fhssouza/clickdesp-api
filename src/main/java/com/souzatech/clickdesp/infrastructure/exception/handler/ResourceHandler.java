package com.souzatech.clickdesp.infrastructure.exception.handler;

import com.souzatech.clickdesp.domain.dto.error.StandardError;
import com.souzatech.clickdesp.domain.dto.error.ValidationError;
import com.souzatech.clickdesp.infrastructure.exception.BadRequestException;
import com.souzatech.clickdesp.infrastructure.exception.DataIntegrityViolationException;
import com.souzatech.clickdesp.infrastructure.exception.NotFoundException;
import com.souzatech.clickdesp.infrastructure.exception.StatusOrdemServicoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFoundException(NotFoundException n,
                                                           HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StandardError.builder()
                        .timestamp(Instant.now())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .error("Resource not found")
                        .message(n.getMessage())
                        .path(request.getRequestURI())
                        .build());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(BadRequestException b, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(StandardError.builder()
                .timestamp(Instant.now())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error("Database Exception")
                .message(b.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException d,
                                                                         HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(StandardError.builder()
                .timestamp(Instant.now())
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .error("Data Integrity Violation Exception")
                .message(d.getMessage())
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException v,
                                                                           HttpServletRequest request) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

            ValidationError err = new ValidationError();
            err.setTimestamp(Instant.now());
            err.setHttpStatus(status);
            err.setStatusCode(status.value());
            err.setError("Validation Exception");
            err.setMessage("Campos com erros de Validação");
            err.setPath(request.getRequestURI());

            for (FieldError f : v.getBindingResult().getFieldErrors()) {
                err.addError(f.getField(), f.getDefaultMessage());
            }

            return ResponseEntity.status(status).body(err);

    }

    @ExceptionHandler(StatusOrdemServicoException.class)
    public ResponseEntity<StandardError> statusOrdemServicoException(RuntimeException s,
                                                       HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(StandardError.builder()
                .timestamp(Instant.now())
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .error("RuntimeException")
                .message(s.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}

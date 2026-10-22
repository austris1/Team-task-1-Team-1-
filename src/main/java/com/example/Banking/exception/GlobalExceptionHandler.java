package com.example.Banking.exception;

import com.example.Banking.dto.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(new ValidationErrorResponse("Validation failed", errors));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ValidationErrorResponse> handleInsufficientFundsException(
            InsufficientFundsException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ValidationErrorResponse(
                        ex.getMessage(),
                        Map.of()
                ));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ValidationErrorResponse> handleAccountNotFoundException(
            AccountNotFoundException ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ValidationErrorResponse(
                        ex.getMessage(),
                        Map.of()
                ));
    }

    @ExceptionHandler(InvalidTransferAmountException.class)
    public ResponseEntity<ValidationErrorResponse> handleInvalidTransferAmountException(
            InvalidTransferAmountException ex
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorResponse(
                        ex.getMessage(),
                        Map.of()
                ));
    }

    @ExceptionHandler(SameAccountTransferException.class)
    public ResponseEntity<ValidationErrorResponse> handleSameAccountTransferException(
            SameAccountTransferException ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ValidationErrorResponse(
                        ex.getMessage(),
                        Map.of()
                ));
    }

}

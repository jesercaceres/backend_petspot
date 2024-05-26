package com.petspot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Método para tratar exceções de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    // Método para tratar exceções de pet duplicado
    @ExceptionHandler(DuplicatePetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
<<<<<<< HEAD
    public ResponseEntity<MessageResponse> handleDuplicatePetException(DuplicatePetException ex) {
        MessageResponse response = new MessageResponse(ex.getMessage(), true);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<MessageResponse> handleGenericException(Exception ex) {
        MessageResponse response = new MessageResponse("Ocorreu um erro interno no servidor.", true);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
=======
    public ResponseEntity<String> handleDuplicatePetException(DuplicatePetException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Método para tratar exceções genéricas
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro interno no servidor.");
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84
    }

    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
<<<<<<< HEAD
    public ResponseEntity<MessageResponse> handleDuplicateEmailException(DuplicateEmailException ex) {
        MessageResponse response = new MessageResponse(ex.getMessage(), true);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
=======
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84
    }

    @ExceptionHandler(PasswordsNotMatchingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
<<<<<<< HEAD
    public ResponseEntity<MessageResponse> handlePasswordsNotMatchingException(PasswordsNotMatchingException ex) {
        MessageResponse response = new MessageResponse(ex.getMessage(), true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
=======
    public ResponseEntity<String> handlePasswordsNotMatchingException(PasswordsNotMatchingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

>>>>>>> 0861db8e17ca107accefe0fd0ce002247b49ad84
}
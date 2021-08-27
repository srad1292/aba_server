package com.radford.aba.shared.exception;

import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(1)
@ControllerAdvice
public class MethodArgumentExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ABAErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException e) {
     	String groupedErrorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ABAErrorMessage errorMessage = new ABAErrorMessage(groupedErrorMessage); 
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);	
    }
}

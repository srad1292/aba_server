package com.radford.aba.shared.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(1)
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ABAErrorMessage> missingBody(HttpMessageNotReadableException e) {
        ABAErrorMessage errorMessage = new ABAErrorMessage("Missing request body"); 
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);	
    }
}
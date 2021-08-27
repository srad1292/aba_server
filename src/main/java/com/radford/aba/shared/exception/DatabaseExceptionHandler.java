package com.radford.aba.shared.exception;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(1)
@ControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ABAErrorMessage> conflict(DataIntegrityViolationException e) {
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        ABAErrorMessage errorMessage = new ABAErrorMessage(message); 
        if(message.contains("not-null constraint")) {
        	return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);	
        } else {
        	return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
        
    }
}



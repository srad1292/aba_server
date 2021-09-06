package com.radford.aba.shared.exception;

import org.springframework.core.NestedExceptionUtils;
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
    	
    	String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
    	e.printStackTrace();
    	
    	if(message.contains("java.util.Date")) {
    		errorMessage = new ABAErrorMessage("Date format should be yyyy-mm-dd");
    	}
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);	
    }
}
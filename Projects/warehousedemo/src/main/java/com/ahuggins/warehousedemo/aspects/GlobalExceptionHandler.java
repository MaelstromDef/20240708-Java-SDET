package com.ahuggins.warehousedemo.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

// This class handles all common exceptions.
@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleStatusException(ResponseStatusException e){
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Object> handleIllegalAccess(IllegalAccessException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<Object> handleGeneralException(Exception e){
    //     String str = "";
    //     for(StackTraceElement elem : e.getStackTrace()) str += elem.toString() + "\n";
    //     logger.error(e.getMessage() + "\n" + e.getCause() + "\n" + str);
    //     e.printStackTrace();

    //     return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    // }
}